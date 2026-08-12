package org.streaminho.app.streaminho.catalog.movies.service;

import org.springframework.stereotype.Service;
import org.streaminho.app.streaminho.catalog.movies.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<String> getAllMovies() {
        return movieRepository.findAll().stream().map(Object::toString).toList();
    }
}
