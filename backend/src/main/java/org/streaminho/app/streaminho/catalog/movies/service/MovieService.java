package org.streaminho.app.streaminho.catalog.movies.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.streaminho.app.streaminho.catalog.movies.models.Movie;
import org.streaminho.app.streaminho.catalog.movies.models.dto.MovieDto;
import org.streaminho.app.streaminho.catalog.movies.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDto> getPopularMovies(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(
                page,
                20,
                Sort.by(Sort.Direction.DESC, "popularity")
        );

        List<Movie> movies = movieRepository.findAll(pageable).getContent();

        return movies.stream().map(this::toMovieDto).toList();
    }


    private MovieDto toMovieDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getDuration(), movie.getPosterUrl(), movie.getPopularity());
    }
}
