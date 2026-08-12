package org.streaminho.app.streaminho.catalog.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.streaminho.app.streaminho.catalog.movies.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
