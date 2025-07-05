package com.tech.booking.repository;

import com.tech.booking.model.BookedSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookedSeatRepository extends JpaRepository<BookedSeat, Long> {
    boolean existsByShowIdAndSeatNumber(Long showId, String seatNumber);

    List<BookedSeat> findByShowId(Long showId);
}

