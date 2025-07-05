package com.tech.booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long showId;
    private int numberOfTickets;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private String seatNumbers;
    private LocalDateTime bookingTime;
    private String status;
}