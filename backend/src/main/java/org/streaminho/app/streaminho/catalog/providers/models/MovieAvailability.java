package org.streaminho.app.streaminho.catalog.providers.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.streaminho.app.streaminho.catalog.movies.models.Movie;

@Entity
@Table(name = "movie_availabilities",
        uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "provider_id"}))
@Getter
@Setter
@NoArgsConstructor
public class MovieAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", nullable = false)
    private StreamingProvider provider;
}