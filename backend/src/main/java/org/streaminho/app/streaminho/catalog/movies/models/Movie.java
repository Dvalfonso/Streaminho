package org.streaminho.app.streaminho.catalog.movies.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.streaminho.app.streaminho.catalog.providers.models.MovieAvailability;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tmdbId;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDate releaseDate;
    private String duration;
    private String posterUrl;
    private double popularity;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trailer> trailers = new ArrayList<>();
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieAvailability> availabilities = new ArrayList<>();

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
