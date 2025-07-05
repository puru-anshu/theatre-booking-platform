package com.tech.theatre.service;

import com.tech.theatre.dto.CreateTheatreRequest;
import com.tech.theatre.dto.TheatreResponse;
import com.tech.theatre.model.Theatre;
import com.tech.theatre.repository.TheatreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public TheatreResponse create(CreateTheatreRequest req) {
        Theatre theatre = new Theatre();
        theatre.setName(req.getName());
        theatre.setCity(req.getCity());
        theatre.setAddress(req.getAddress());
        theatre.setTotalScreens(req.getTotalScreens());
        theatre.setSeatsPerScreen(req.getSeatsPerScreen());
        return mapToResponse(theatreRepository.save(theatre));
    }

    public List<TheatreResponse> getByCity(String city) {
        return theatreRepository.findByCity(city).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private TheatreResponse mapToResponse(Theatre t) {
        TheatreResponse res = new TheatreResponse();
        res.setId(t.getId());
        res.setName(t.getName());
        res.setCity(t.getCity());
        res.setAddress(t.getAddress());
        res.setTotalScreens(t.getTotalScreens());
        res.setSeatsPerScreen(t.getSeatsPerScreen());
        return res;
    }
}
