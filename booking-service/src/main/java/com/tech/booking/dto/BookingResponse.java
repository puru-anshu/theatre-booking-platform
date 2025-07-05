package com.tech.booking.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponse {
    private Long bookingId;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private String status;
}
