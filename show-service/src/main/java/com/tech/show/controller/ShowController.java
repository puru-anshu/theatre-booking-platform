package com.tech.show.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tech.show.dto.CreateShowRequest;
import com.tech.show.dto.ShowResponse;
import com.tech.show.service.ShowService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    public ResponseEntity<ShowResponse> create(@RequestBody CreateShowRequest request) {
        return ResponseEntity.ok(showService.createShow(request));
    }

    @GetMapping
    public ResponseEntity<List<ShowResponse>> getByMovie(
            @RequestParam Long movieId,
            @RequestParam String date) {
        return ResponseEntity.ok(showService.getShowsByMovie(movieId, date));
    }

    @GetMapping("/search")
    public List<ShowResponse> searchShows(
            @RequestParam Long movieId,
            @RequestParam String city,
            @RequestParam LocalDate date
    ) {
        return showService.findByMovieAndCityAndDate(movieId, city, date);
    }
}