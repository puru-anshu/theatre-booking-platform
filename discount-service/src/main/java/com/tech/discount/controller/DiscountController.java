package com.tech.discount.controller;

import com.tech.discount.dto.DiscountRequest;
import com.tech.discount.dto.DiscountResponse;
import com.tech.discount.service.DiscountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping
    public ResponseEntity<DiscountResponse> calculate(@RequestBody DiscountRequest request) {
        return ResponseEntity.ok(discountService.calculateDiscount(request));
    }
}
