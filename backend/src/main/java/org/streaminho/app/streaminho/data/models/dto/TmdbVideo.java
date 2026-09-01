package org.streaminho.app.streaminho.data.models.dto;

public record TmdbVideo(
        String id,
        String key,
        String name,
        String site,
        String type,
        String iso_639_1,
        boolean official
) {
}
