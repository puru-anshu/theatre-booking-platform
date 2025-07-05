package com.tech.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.booking.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
