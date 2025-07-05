package com.tech.show.service;

import com.tech.show.dto.CreateShowRequest;
import com.tech.show.dto.ShowResponse;
import com.tech.show.dto.TheatreResponse;
import com.tech.show.model.SeatCategory;
import com.tech.show.model.ShowSeatPrice;
import com.tech.show.model.Show;
import com.tech.show.repository.ShowRepository;
import com.tech.show.repository.ShowSeatPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatPriceRepository seatPriceRepo;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public ShowResponse createShow(CreateShowRequest req) {
        Show show = new Show();
        show.setMovieId(req.getMovieId());
        show.setTheatreId(req.getTheatreId());
        show.setDate(req.getDate());
        show.setTime(req.getTime());
        show.setTotalSeats(req.getTotalSeats());
        show.setAvailableSeats(req.getTotalSeats());
        show.setPricePerTicket(req.getPricePerTicket());
        // Save seat-level pricing
        req.getSeatPricing().forEach(p -> {
            ShowSeatPrice sp = new ShowSeatPrice();
            sp.setShowId(show.getId());
            sp.setCategory(SeatCategory.valueOf(p.getCategory()));
            sp.setPrice(p.getPrice());
            seatPriceRepo.save(sp);
        });

        Show saved = showRepository.save(show);
        return mapToResponse(saved);
    }

    public List<ShowResponse> getShowsByMovie(Long movieId, String date) {
        return showRepository.findByMovieIdAndDate(movieId, LocalDate.parse(date)).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ShowResponse mapToResponse(Show show) {
        ShowResponse res = new ShowResponse();
        res.setId(show.getId());
        res.setMovieId(show.getMovieId());
        res.setTheatreId(show.getTheatreId());
        res.setDate(show.getDate());
        res.setTime(show.getTime());
        res.setAvailableSeats(show.getAvailableSeats());
        return res;
    }

    public List<ShowResponse> findByMovieAndCityAndDate(Long movieId, String city, LocalDate date) {
       
        // Call theatre-service to get theatres in the city
        List<TheatreResponse> theatres = webClientBuilder.build()
                .get()
                .uri("http://theatre-service/api/theatres/city/" + city)
                .retrieve()
                .bodyToFlux(TheatreResponse.class)
                .collectList()
                .block();

        if (theatres == null || theatres.isEmpty())
            return List.of(); // No theatres found in the city

        return showRepository.findByMovieIdAndDate(movieId, date).stream()
                .map(this::mapToResponse)
                .filter(show -> theatres.stream().anyMatch(t -> t.getId().equals(show.getTheatreId())))
                .collect(Collectors.toList());

    }
}
