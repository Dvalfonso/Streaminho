package org.streaminho.app.streaminho.catalog.movies.data;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DataInitializerTest {

    private final String tmdbToken;

    private final RestClient restClient;

    public DataInitializerTest(@Value("${tmdb.api-token}") String tmdbToken) {
        this.tmdbToken = tmdbToken;
        this.restClient = RestClient.builder()
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public void test() {

        String responseBody = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("query", "Interstellar")
                        .queryParam("language", "en-US")
                        .build())
                .header("Authorization", "Bearer " + tmdbToken)
                .retrieve()
                .body(String.class);

        
        System.out.println(responseBody);
    }
}
