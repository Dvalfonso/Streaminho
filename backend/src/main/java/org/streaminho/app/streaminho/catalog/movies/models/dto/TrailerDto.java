package org.streaminho.app.streaminho.catalog.movies.models.dto;

public record TrailerDto(
        Long id,
        String youtubeKey,
        String type,
        String language,
        boolean official,
        Long movieId
) {
}
