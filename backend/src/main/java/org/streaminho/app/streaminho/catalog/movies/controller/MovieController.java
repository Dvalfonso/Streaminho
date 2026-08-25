package org.streaminho.app.streaminho.catalog.movies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.streaminho.app.streaminho.data.DataInitializerTest;
import org.streaminho.app.streaminho.catalog.movies.models.dto.CreateMovieRequest;
import org.streaminho.app.streaminho.catalog.movies.models.dto.MovieDto;
import org.streaminho.app.streaminho.catalog.movies.service.MovieService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    private final DataInitializerTest dataInitializerTest;

    public MovieController(MovieService movieService, DataInitializerTest dataInitializerTest) {
        this.movieService = movieService;
        this.dataInitializerTest = dataInitializerTest;
    }

    @GetMapping("/popular")
    public ResponseEntity<List<MovieDto>> getPopularMovies(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(movieService.getPopularMovies(page));
    }

    /**
     *
    //@PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody CreateMovieRequest createMovieRequest) {
        MovieDto response = movieService.createMovie(createMovieRequest);
        return ResponseEntity.created(URI.create("/api/movies/{id}")).body(response);
    }*/

    @GetMapping("/testApi")
    public void testApi() {
        dataInitializerTest.test();
    }
}
