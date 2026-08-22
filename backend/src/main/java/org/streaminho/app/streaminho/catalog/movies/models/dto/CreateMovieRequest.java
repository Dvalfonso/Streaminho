package org.streaminho.app.streaminho.catalog.movies.models.dto;

import java.time.LocalDate;

public record CreateMovieRequest(
        String title,
        String description,
        LocalDate releaseDate,
        String duration,
        String poster
) {
}
