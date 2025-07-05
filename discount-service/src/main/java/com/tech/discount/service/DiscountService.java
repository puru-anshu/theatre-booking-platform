package com.tech.discount.service;

import com.tech.discount.dto.DiscountRequest;
import com.tech.discount.dto.DiscountResponse;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;

@Service
public class DiscountService {

    public DiscountResponse calculateDiscount(DiscountRequest request) {
        int count = request.getNumberOfTickets();
        LocalTime time = request.getShowTime();

        BigDecimal ticketPrice = new BigDecimal("200");
        BigDecimal total = ticketPrice.multiply(BigDecimal.valueOf(count));
        BigDecimal discount = BigDecimal.ZERO;
        String reason = "No discount";

        if (count == 3) {
            discount = total.multiply(new BigDecimal("0.5"));
            reason = "50% off for 3rd ticket rule";
        } else if (time.isAfter(LocalTime.of(12, 0)) && time.isBefore(LocalTime.of(16, 0))) {
            discount = total.multiply(new BigDecimal("0.2"));
            reason = "20% off for afternoon show";
        }

        DiscountResponse res = new DiscountResponse();
        res.setDiscountAmount(discount);
        res.setDescription(reason);
        return res;
    }
}
