package com.tech.discount.dto;

import java.time.LocalTime;

/**
 * DTO for discount request.
 * Contains the number of tickets and the show time.
 */
public class DiscountRequest {

    private int numberOfTickets;
    private LocalTime showTime;

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }
}
