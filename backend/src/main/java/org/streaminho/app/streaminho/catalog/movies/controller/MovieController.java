package org.streaminho.app.streaminho.catalog.movies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.streaminho.app.streaminho.catalog.movies.models.dto.MovieReproductionDto;
import org.streaminho.app.streaminho.catalog.movies.models.dto.TrailerDto;
import org.streaminho.app.streaminho.data.DataInitializerTest;
import org.streaminho.app.streaminho.catalog.movies.models.dto.CreateMovieRequest;
import org.streaminho.app.streaminho.catalog.movies.models.dto.MovieDto;
import org.streaminho.app.streaminho.catalog.movies.service.MovieService;
import org.streaminho.app.streaminho.data.models.dto.TmdbVideo;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/popular")
    public ResponseEntity<List<MovieDto>> getPopularMovies(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(movieService.getPopularMovies(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieReproductionDto> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovie(id));
    }

    @GetMapping("/{id}/trailers")
    public ResponseEntity<List<TrailerDto>> getTrailersById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getTrailersById(id));
    }
}
