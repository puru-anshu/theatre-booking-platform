package com.tech.movie.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.movie.dto.CreateMovieRequest;
import com.tech.movie.dto.MovieResponse;
import com.tech.movie.model.Movie;
import com.tech.movie.repository.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieResponse create(CreateMovieRequest req) {
        Movie movie = new Movie();
        movie.setName(req.getName());
        movie.setGenre(req.getGenre());
        movie.setLanguage(req.getLanguage());
        movie.setCity(req.getCity());
        Movie saved = movieRepository.save(movie);
        return mapToResponse(saved);
    }

    public List<MovieResponse> listByGenre(String genre) {
        return movieRepository.findByGenre(genre).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    public List<MovieResponse> listByCity(String city) {
        return movieRepository.findByCity(city).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    public List<MovieResponse> listByLanguage(String lang) {
        return movieRepository.findByLanguage(lang).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    private MovieResponse mapToResponse(Movie movie) {
        MovieResponse res = new MovieResponse();
        res.setId(movie.getId());
        res.setName(movie.getName());
        res.setGenre(movie.getGenre());
        res.setLanguage(movie.getLanguage());
        res.setCity(movie.getCity());
        return res;
    }
}

