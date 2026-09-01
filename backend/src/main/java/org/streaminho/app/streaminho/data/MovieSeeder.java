package org.streaminho.app.streaminho.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.streaminho.app.streaminho.catalog.movies.models.Movie;
import org.streaminho.app.streaminho.catalog.movies.models.Trailer;
import org.streaminho.app.streaminho.catalog.movies.repository.MovieRepository;
import org.streaminho.app.streaminho.catalog.movies.repository.TrailerRepository;
import org.streaminho.app.streaminho.data.models.dto.TmdbMovie;
import org.streaminho.app.streaminho.data.models.dto.TmdbMoviePopularResponse;
import org.streaminho.app.streaminho.data.models.dto.TmdbVideo;
import org.streaminho.app.streaminho.data.models.dto.TmdbVideoResponse;


@Component
public class MovieSeeder implements CommandLineRunner {
    private final RestClient restClient;
    private final MovieRepository movieRepository;
    private final TrailerRepository trailerRepository;

    @Value("${tmdb.api-token}")
    private String tmdbToken;

    public MovieSeeder(MovieRepository movieRepository, TrailerRepository trailerRepository) {
        this.movieRepository = movieRepository;
        this.trailerRepository = trailerRepository;

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

            movieRepository.save(movie);

            fetchAndSaveTrailers(movie);

            System.out.println( movie.getTitle()+ " saved");
        }

    }

    private void fetchAndSaveTrailers(Movie movie) {
        TmdbVideoResponse videoResponse = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{id}/videos")
                        .queryParam("language", "en-US")
                        .build(movie.getTmdbId()))
                .header("Authorization", "Bearer " + tmdbToken)
                .retrieve()
                .body(TmdbVideoResponse.class);

        if (videoResponse == null || videoResponse.results() == null) {
            return;
        }

        for (TmdbVideo video : videoResponse.results()) {
            if (!"YouTube".equalsIgnoreCase(video.site())) {
                continue;
            }

            Trailer trailer = new Trailer();
            trailer.setYoutubeKey(video.key());
            trailer.setType(video.type());
            trailer.setLanguage(video.iso_639_1());
            trailer.setOfficial(video.official());
            trailer.setMovie(movie);

            trailerRepository.save(trailer);
        }
    }
}
