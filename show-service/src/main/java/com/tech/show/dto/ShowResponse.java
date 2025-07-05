package com.tech.show.dto;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowResponse {
    private Long id;
    private Long movieId;
    private Long theatreId;
    private LocalDate date;
    private LocalTime time;
    private int availableSeats;
    private List<SeatPricing> seatPricing;
}