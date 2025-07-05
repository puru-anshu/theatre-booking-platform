package com.tech.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    private Long id;
    private String name;
    private String genre;
    private String language;
    private String city;

  
    
}
