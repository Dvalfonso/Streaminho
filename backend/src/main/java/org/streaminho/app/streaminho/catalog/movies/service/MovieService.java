package org.streaminho.app.streaminho.catalog.movies.service;

import org.springframework.stereotype.Service;
import org.streaminho.app.streaminho.catalog.movies.models.Movie;
import org.streaminho.app.streaminho.catalog.movies.models.VideoAsset;
import org.streaminho.app.streaminho.catalog.movies.models.dto.CreateMovieRequest;
import org.streaminho.app.streaminho.catalog.movies.models.dto.MovieDto;
import org.streaminho.app.streaminho.catalog.movies.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<String> getAllMovies() {
        return movieRepository.findAll().stream().map(Movie::getTitle).toList();
    }

    public MovieDto createMovie(CreateMovieRequest createMovieRequest) {
        if (movieRepository.existsByTitle(createMovieRequest.title())) {
            throw new IllegalArgumentException("Movie already exist");
        }

        Movie movie = new Movie();
        movie.setTitle(createMovieRequest.title());
        movie.setDescription(createMovieRequest.description());
        movie.setDuration(createMovieRequest.duration());
        movie.setReleaseDate(createMovieRequest.releaseDate());
        movie.setPosterUrl(createMovieRequest.poster());
        movie.setVideoAsset(new VideoAsset());

        movieRepository.save(movie);

        return toMovieDto(movie);
    }

    private MovieDto toMovieDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getDuration(), movie.getPosterUrl());
    }
}
