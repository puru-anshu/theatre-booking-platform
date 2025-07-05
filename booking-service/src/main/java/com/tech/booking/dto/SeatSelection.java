package com.tech.booking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatSelection {
    private String seatNumber;       // e.g., A1
    private String category;         // e.g., VIP, STANDARD
}
