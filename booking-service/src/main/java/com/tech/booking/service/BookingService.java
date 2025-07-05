package com.tech.booking.service;

import com.tech.booking.dto.*;
import com.tech.booking.exception.BookingException;
import com.tech.booking.model.BookedSeat;
import com.tech.booking.model.Booking;
import com.tech.booking.repository.BookedSeatRepository;
import com.tech.booking.repository.BookingRepository;

import io.github.resilience4j.retry.annotation.Retry;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookedSeatRepository bookedSeatRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Retry(name = "discountRetry", fallbackMethod = "discountFallback")
    public DiscountResponse getDiscount(int count,BookTicketRequest request) {
        DiscountRequest discountReq = new DiscountRequest();
        discountReq.setNumberOfTickets(count);
        discountReq.setShowTime(LocalTime.of(14, 0)); // mock time
        discountReq.setSeatCategories(
                request.getSeats().stream()
                        .map(s -> s.getCategory().toUpperCase())
                        .collect(Collectors.toList())
        );
        return webClientBuilder
                .build()
                .post()
                .uri("http://discount-service:8086/api/discounts")
                .bodyValue(discountReq)
                .retrieve()
                .bodyToMono(DiscountResponse.class)
                .block();
    }

    public DiscountResponse discountFallback(int count, Throwable t) {
        DiscountResponse fallback = new DiscountResponse();
        fallback.setDiscountAmount(BigDecimal.ZERO);
        fallback.setDescription(
                "Discount service unavailable: fallback applied"
        );
        return fallback;
    }

    @Retry(name = "paymentRetry", fallbackMethod = "paymentFallback")
    public PaymentResponse pay(PaymentRequest request) {
        return webClientBuilder
                .build()
                .post()
                .uri("http://payment-service:8087/api/payments")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block();
    }

    public PaymentResponse paymentFallback(
            PaymentRequest request,
            Throwable t
    ) {
        PaymentResponse fallback = new PaymentResponse();
        fallback.setTransactionId("N/A");
        fallback.setStatus("FAILED");
        return fallback;
    }

    public BookingResponse bookTickets(BookTicketRequest request) {
        int count = request.getSeats().size();
        BigDecimal pricePerTicket = new BigDecimal("200");
        BigDecimal total = pricePerTicket.multiply(BigDecimal.valueOf(count));

        //Validate if seat is not already booked
        for (SeatSelection seat : request.getSeats()) {
            if (bookedSeatRepository.existsByShowIdAndSeatNumber(request.getShowId(), seat.getSeatNumber())) {
                throw new BookingException("Seat already booked: " + seat.getSeatNumber());
            }
        }

        DiscountResponse discountRes = getDiscount(count,request);
        BigDecimal discount = discountRes.getDiscountAmount();

        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setShowId(request.getShowId());
        booking.setNumberOfTickets(count);
        booking.setSeatNumbers(request.getSeats().
                stream().map(SeatSelection::getSeatNumber).
                collect(Collectors.joining(", "    )));
        booking.setBookingTime(LocalDateTime.now());
        booking.setTotalAmount(total.subtract(discount));
        booking.setDiscountAmount(discount);
        booking.setStatus("CONFIRMED");

        bookingRepository.save(booking);

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setBookingId(booking.getId());
        paymentRequest.setAmount(booking.getTotalAmount());

        PaymentResponse paymentResponse = pay(paymentRequest);

        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getId());
        response.setTotalAmount(booking.getTotalAmount());
        response.setDiscountAmount(discount);
        response.setStatus(paymentResponse.getStatus());

        request.getSeats().forEach(seat -> {
            BookedSeat bs = new BookedSeat();
            bs.setShowId(request.getShowId());
            bs.setSeatNumber(seat.getSeatNumber());
            bookedSeatRepository.save(bs);
        });

        return response;
    }

    public Map<String, BigDecimal> getSeatPrices(Long showId) {
        ShowResponse show = webClientBuilder.build()
                .get()
                .uri("http://show-service:8082/api/shows/" + showId)
                .retrieve()
                .bodyToMono(ShowResponse.class)
                .block();

        return show.getSeatPricing().stream()
                .collect(Collectors.toMap(
                        p -> p.getCategory().toUpperCase(),
                        SeatPricing::getPrice
                ));
    }


    public List<String> getAvailableSeats(Long showId, String category) {
        // 1. Call show-service to get total seats by category
        ShowResponse show = webClientBuilder.build()
                .get()
                .uri("http://show-service:8082/api/shows/" + showId)
                .retrieve()
                .bodyToMono(ShowResponse.class)
                .block();

        int totalSeatsForCategory = getSeatCountByCategory(show, category);
        List<String> allSeats = generateSeatNumbers(category, totalSeatsForCategory);

        // 2. Get already booked seat numbers for the show
        List<BookedSeat> booked = bookedSeatRepository.findByShowId(showId);
        Set<String> bookedSeatNumbers = booked.stream()
                .map(BookedSeat::getSeatNumber)
                .collect(Collectors.toSet());

        // 3. Filter available seats
        return allSeats.stream()
                .filter(seat -> !bookedSeatNumbers.contains(seat))
                .collect(Collectors.toList());
    }

    private int getSeatCountByCategory(ShowResponse show, String category) {
        return show.getSeatPricing().stream()
                .filter(p -> category.equalsIgnoreCase(p.getCategory()))
                .findFirst()
                .map(p -> 50) // You can later replace this with dynamic count per category
                .orElse(0);
    }

    private List<String> generateSeatNumbers(String category, int count) {
        // For example: STANDARD => S1, S2, ..., VIP => V1, V2, ...
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> category.substring(0, 1).toUpperCase() + i)
                .collect(Collectors.toList());
    }
}
