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
import org.streaminho.app.streaminho.catalog.providers.models.MovieAvailability;
import org.streaminho.app.streaminho.catalog.providers.models.StreamingProvider;
import org.streaminho.app.streaminho.catalog.providers.models.dtos.TmdbProvider;
import org.streaminho.app.streaminho.catalog.providers.models.dtos.TmdbRegionProviders;
import org.streaminho.app.streaminho.catalog.providers.models.dtos.TmdbWatchProvidersResponse;
import org.streaminho.app.streaminho.catalog.providers.repo.MovieAvailabilityRepository;
import org.streaminho.app.streaminho.catalog.providers.repo.StreamingProviderRepository;
import org.streaminho.app.streaminho.data.models.dto.TmdbMovie;
import org.streaminho.app.streaminho.data.models.dto.TmdbMoviePopularResponse;
import org.streaminho.app.streaminho.data.models.dto.TmdbVideo;
import org.streaminho.app.streaminho.data.models.dto.TmdbVideoResponse;

import java.util.HashMap;
import java.util.Map;

@Component
public class MovieSeeder implements CommandLineRunner {
    private final RestClient restClient;
    private final MovieRepository movieRepository;
    private final TrailerRepository trailerRepository;
    private final MovieAvailabilityRepository movieAvailabilityRepository;
    private final StreamingProviderRepository streamingProviderRepository;


    public MovieSeeder(MovieRepository movieRepository,
                       TrailerRepository trailerRepository,
                       MovieAvailabilityRepository movieAvailabilityRepository,
                       StreamingProviderRepository streamingProviderRepository,
                       @Value("${tmdb.api-token}") String tmdbToken) {
        this.movieRepository = movieRepository;
        this.trailerRepository = trailerRepository;
        this.movieAvailabilityRepository = movieAvailabilityRepository;
        this.streamingProviderRepository = streamingProviderRepository;

        this.restClient = RestClient.builder()
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("accept", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization", "Bearer " + tmdbToken)
                .build();
    }

    @Override
    public void run(String... args) throws Exception {
         if (movieRepository.count() > 0) {
         return;
         }

        TmdbMoviePopularResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/popular")
                        .queryParam("language", "en-US")
                        .queryParam("page", "1")
                        .build())
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
            fetchAndSaveAvailability(movie);

            System.out.println(movie.getTitle() + " saved");
        }
    }

    private void fetchAndSaveTrailers(Movie movie) {
        TmdbVideoResponse videoResponse = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{id}/videos")
                        .queryParam("language", "en-US")
                        .build(movie.getTmdbId()))
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

    private void fetchAndSaveAvailability(Movie movie) {
        TmdbWatchProvidersResponse response = restClient.get()
                .uri("/movie/{id}/watch/providers", movie.getTmdbId())
                .retrieve()
                .body(TmdbWatchProvidersResponse.class);

        if (response == null || response.results() == null) {
            return;
        }

        TmdbRegionProviders arData = response.results().get("AR");
        if (arData == null) {
            return;
        }

        Map<Integer, TmdbProvider> allProviders = new HashMap<>();
        if (arData.flatrate() != null) arData.flatrate().forEach(p -> allProviders.put(p.provider_id(), p));
        if (arData.rent() != null) arData.rent().forEach(p -> allProviders.put(p.provider_id(), p));
        if (arData.buy() != null) arData.buy().forEach(p -> allProviders.put(p.provider_id(), p));

        for (TmdbProvider p : allProviders.values()) {
            StreamingProvider provider = streamingProviderRepository.findByTmdbProviderId(p.provider_id())
                    .orElseGet(() -> {
                        StreamingProvider newProvider = new StreamingProvider();
                        newProvider.setTmdbProviderId(p.provider_id());
                        newProvider.setName(p.provider_name());
                        newProvider.setLogoPath(p.logo_path());
                        return streamingProviderRepository.save(newProvider);
                    });

            MovieAvailability availability = new MovieAvailability();
            availability.setMovie(movie);
            availability.setProvider(provider);

            movieAvailabilityRepository.save(availability);
        }
    }
}