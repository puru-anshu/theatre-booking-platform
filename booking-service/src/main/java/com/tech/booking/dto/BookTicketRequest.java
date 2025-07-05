package com.tech.booking.dto;


import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketRequest {
    private Long userId;
    private Long showId;
    private List<SeatSelection> seats;

    // Getters and Setters
}
