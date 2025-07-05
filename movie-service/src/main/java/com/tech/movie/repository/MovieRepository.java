package com.tech.movie.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tech.movie.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenre(String genre);
    List<Movie> findByCity(String city);
    List<Movie> findByLanguage(String language);
}


