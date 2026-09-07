package org.streaminho.app.streaminho.catalog.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.streaminho.app.streaminho.catalog.movies.models.Movie;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(String title);
    boolean existsByTmdbId(Long tmdbId);

    @Query("""
        SELECT DISTINCT m FROM Movie m
        LEFT JOIN FETCH m.availabilities a
        LEFT JOIN FETCH a.provider
        WHERE m.id = :id
    """)
    Optional<Movie> findByIdWithAvailability(@Param("id") Long id);
}
