package com.tech.movie.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMovieRequest {

    private String name;
    private String genre;
    private String language;
    private String city;

}
