package org.streaminho.app.streaminho.catalog.providers.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.streaminho.app.streaminho.catalog.providers.models.MovieAvailability;

public interface MovieAvailabilityRepository extends JpaRepository<MovieAvailability, Long> {
    boolean existsByMovieIdAndProviderId(Long movieId, Long providerId);
}