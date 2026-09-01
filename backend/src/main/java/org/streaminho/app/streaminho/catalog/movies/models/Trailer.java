package org.streaminho.app.streaminho.catalog.movies.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trailers")
@NoArgsConstructor
@Getter
@Setter
public class Trailer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String youtubeKey;

    private String type; // "Trailer", "Teaser", "Clip"

    private String language; // iso_639_1, ej "en", "es"

    private boolean official;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
