package org.streaminho.app.streaminho.data.models.dto;

import java.util.List;

public record TmdbMoviePopularResponse(
        int page,
        List<TmdbMovie> results,
        int total_pages,
        int total_results
) {
}
