package com.tech.show.dto;

import java.math.BigDecimal;

public class SeatPricing {
    private String category;
    private BigDecimal price;

    public SeatPricing() {
    }

    public SeatPricing(String category, BigDecimal price) {
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
