package com.tech.booking.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

/**
 * DTO for discount request.
 * Contains the number of tickets and the show time.
 */
@Getter
@Setter
public class DiscountRequest {

    private int numberOfTickets;
    private LocalTime showTime;
    private List<String> seatCategories;


}
