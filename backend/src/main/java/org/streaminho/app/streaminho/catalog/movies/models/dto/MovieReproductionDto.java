package org.streaminho.app.streaminho.catalog.movies.models.dto;

import java.time.LocalDate;

public record MovieReproductionDto(
        Long id,
        String title,
        String description,
        LocalDate releaseDate,
        String duration,
        String posterPath,
        double popularity
) {
}
