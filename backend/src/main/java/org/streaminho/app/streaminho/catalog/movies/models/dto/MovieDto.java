package org.streaminho.app.streaminho.catalog.movies.models.dto;

import java.time.LocalDate;

public record MovieDto(
        Long id,
        String title,
        String description,
        LocalDate releaseDate,
        String duration,
        String posterPath,
        double popularity
) {
}
