package com.tech.show.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShowRequest {
    private Long movieId;
    private Long theatreId;
    private LocalDate date;
    private LocalTime time;
    private int totalSeats;
    private BigDecimal pricePerTicket;
    private List<SeatPricing> seatPricing;
}
