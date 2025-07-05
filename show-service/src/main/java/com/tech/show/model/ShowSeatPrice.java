package com.tech.show.model;

import java.math.BigDecimal;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class ShowSeatPrice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long showId;

    @Enumerated(EnumType.STRING)
    private SeatCategory category;

    private BigDecimal price;
}
