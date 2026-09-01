package org.streaminho.app.streaminho.catalog.movies.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.streaminho.app.streaminho.catalog.movies.models.Movie;
import org.streaminho.app.streaminho.catalog.movies.models.Trailer;
import org.streaminho.app.streaminho.catalog.movies.models.dto.MovieDto;
import org.streaminho.app.streaminho.catalog.movies.models.dto.MovieReproductionDto;
import org.streaminho.app.streaminho.catalog.movies.models.dto.TrailerDto;
import org.streaminho.app.streaminho.catalog.movies.repository.MovieRepository;
import org.streaminho.app.streaminho.catalog.movies.repository.TrailerRepository;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final TrailerRepository trailerRepository;

    public MovieService(MovieRepository movieRepository, TrailerRepository trailerRepository) {
        this.movieRepository = movieRepository;
        this.trailerRepository = trailerRepository;
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

    public MovieReproductionDto getMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));

        return new MovieReproductionDto(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getDuration(), movie.getPosterUrl(), movie.getPopularity());
    }

    public List<TrailerDto> getTrailersById(Long id) {
        List<Trailer> trailer = trailerRepository.findByMovieId(id);

        return trailer.stream()
                .map(trailer1 -> new TrailerDto(
                        trailer1.getId(),
                        trailer1.getYoutubeKey(),
                        trailer1.getType(),
                        trailer1.getLanguage(),
                        trailer1.isOfficial(),
                        trailer1.getMovie().getId()
                ))
                .toList();
    }
}
