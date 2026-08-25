package org.streaminho.app.streaminho.data.models.dto;

import java.time.LocalDate;
import java.util.List;

public record TmdbMovie(
        Long id,
        String title,
        String original_title,
        String original_language,
        String overview,
        LocalDate release_date,
        String poster_path,
        String backdrop_path,
        Double popularity,
        Double vote_average,
        Integer vote_count,
        List<Integer> genre_ids
) {
}
