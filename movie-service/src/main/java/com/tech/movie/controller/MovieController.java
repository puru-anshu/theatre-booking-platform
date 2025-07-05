package com.tech.movie.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tech.movie.dto.CreateMovieRequest;
import com.tech.movie.dto.MovieResponse;
import com.tech.movie.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> create(@RequestBody CreateMovieRequest req) {
        return ResponseEntity.ok(movieService.create(req));
    }

    @GetMapping("/genre")
    public List<MovieResponse> byGenre(@RequestParam String genre) {
        return movieService.listByGenre(genre);
    }

    @GetMapping("/city")
    public List<MovieResponse> byCity(@RequestParam String city) {
        return movieService.listByCity(city);
    }

    @GetMapping("/language")
    public List<MovieResponse> byLanguage(@RequestParam String language) {
        return movieService.listByLanguage(language);
    }
}

