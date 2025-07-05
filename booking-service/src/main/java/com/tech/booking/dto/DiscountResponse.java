package com.tech.booking.dto;

import java.math.BigDecimal;

/**
 * DTO for discount response.
 * Contains the discount amount and a description.
 */
public class DiscountResponse {

    private BigDecimal discountAmount;
    private String description;

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
