package com.tech.booking.dto;

import java.math.BigDecimal;

public class PaymentRequest {

    private Long bookingId;
    private BigDecimal amount;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
