package org.streaminho.app.streaminho.catalog.movies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.streaminho.app.streaminho.catalog.movies.models.dto.CreateMovieRequest;
import org.streaminho.app.streaminho.catalog.movies.models.dto.MovieDto;
import org.streaminho.app.streaminho.catalog.movies.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody CreateMovieRequest createMovieRequest) {
        MovieDto response = movieService.createMovie(createMovieRequest);
    }
}
