package com.tech.show.repository;

import java.util.List;

import com.tech.show.model.ShowSeatPrice;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ShowSeatPriceRepository extends JpaRepository<ShowSeatPrice, Long> {
    List<ShowSeatPrice> findByShowId(Long showId);
}

