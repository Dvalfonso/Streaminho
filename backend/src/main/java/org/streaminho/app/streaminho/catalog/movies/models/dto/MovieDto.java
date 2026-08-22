package org.streaminho.app.streaminho.catalog.movies.models.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.streaminho.app.streaminho.catalog.movies.models.VideoAsset;

import java.time.LocalDate;

public record MovieDto(
        Long id,
        String title,
        String description,
        LocalDate releaseDate,
        String duration
) {
}
