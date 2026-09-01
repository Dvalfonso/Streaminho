package org.streaminho.app.streaminho.catalog.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.streaminho.app.streaminho.catalog.movies.models.Trailer;

public interface TrailerRepository extends JpaRepository<Trailer, Long> {
    boolean existsByMovieIdAndYoutubeKey(Long movieId, String youtubeKey);
}
