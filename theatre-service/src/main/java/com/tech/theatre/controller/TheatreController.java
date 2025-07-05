package com.tech.theatre.controller;

import com.tech.theatre.dto.CreateTheatreRequest;
import com.tech.theatre.dto.TheatreResponse;
import com.tech.theatre.service.TheatreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping
    public ResponseEntity<TheatreResponse> create(@RequestBody CreateTheatreRequest req) {
        return ResponseEntity.ok(theatreService.create(req));
    }

    @GetMapping
    public List<TheatreResponse> getByCity(@RequestParam String city) {
        return theatreService.getByCity(city);
    }
}
