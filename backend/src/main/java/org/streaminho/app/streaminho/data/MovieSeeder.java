package org.streaminho.app.streaminho.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.streaminho.app.streaminho.catalog.movies.models.Movie;
import org.streaminho.app.streaminho.catalog.movies.models.VideoAsset;
import org.streaminho.app.streaminho.catalog.movies.repository.MovieRepository;
import org.streaminho.app.streaminho.data.models.dto.TmdbMovie;
import org.streaminho.app.streaminho.data.models.dto.TmdbMoviePopularResponse;


@Component
public class MovieSeeder implements CommandLineRunner {
    private final RestClient restClient;
    private final MovieRepository movieRepository;

    @Value("${tmdb.api-token}")
    private String tmdbToken;

    public MovieSeeder(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;

        this.restClient = RestClient.builder()
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public void run(String... args) throws Exception {
        TmdbMoviePopularResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/popular")
                        .queryParam("language", "en-US")
                        .queryParam("page", "1")
                        .build())
                .header("Authorization", "Bearer " + tmdbToken)
                .retrieve()
                .body(TmdbMoviePopularResponse.class);

        if (response == null || response.results() == null) {
            return;
        }

        for (TmdbMovie tmdbMovie : response.results()) {
            if (movieRepository.existsByTmdbId(tmdbMovie.id())) {
                continue;
            }

            Movie movie = new Movie();
            movie.setTmdbId(tmdbMovie.id());
            movie.setTitle(tmdbMovie.title());
            movie.setDescription(tmdbMovie.overview());
            movie.setReleaseDate(tmdbMovie.release_date());
            movie.setDuration("120 min");
            movie.setPosterUrl(tmdbMovie.poster_path());
            movie.setVideoAsset(new VideoAsset());

            movieRepository.save(movie);

            System.out.println( movie.getTitle()+ " saved");
        }
    }
}
