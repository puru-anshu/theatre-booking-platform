package com.tech.payment.service;

import com.tech.payment.dto.PaymentRequest;
import com.tech.payment.dto.PaymentResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    public PaymentResponse processPayment(PaymentRequest request) {
        PaymentResponse response = new PaymentResponse();
        response.setTransactionId(UUID.randomUUID().toString());
        response.setStatus("SUCCESS"); // Always success for now
        return response;
    }
}
