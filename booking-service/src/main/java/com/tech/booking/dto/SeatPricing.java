package com.tech.booking.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SeatPricing {
    private String category;
    private BigDecimal price;
}
