package com.tech.booking.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tech.booking.dto.BookTicketRequest;
import com.tech.booking.dto.BookingResponse;
import com.tech.booking.model.Booking;
import com.tech.booking.repository.BookingRepository;
import com.tech.booking.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired private BookingService bookingService;
    @Autowired private BookingRepository bookingRepository;

    @PostMapping
    public ResponseEntity<BookingResponse> book(@RequestBody BookTicketRequest request) {
        return ResponseEntity.ok(bookingService.bookTickets(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        return bookingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/available-seats")
    public ResponseEntity<List<String>> getAvailableSeats(
            @RequestParam Long showId,
            @RequestParam String category) {
        return ResponseEntity.ok(bookingService.getAvailableSeats(showId, category));
    }
}