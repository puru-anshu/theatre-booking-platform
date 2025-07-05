package com.tech.show.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.show.model.ShowSeatPrice;
import com.tech.show.model.Show;
import org.springframework.data.jpa.repository.Query;

public interface ShowRepository extends JpaRepository<Show, Long> {

   List<Show> findByMovieIdAndDate(Long movieId, LocalDate date);

}
