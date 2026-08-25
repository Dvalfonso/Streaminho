package org.streaminho.app.streaminho.catalog.movies.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "videoAsset_id", referencedColumnName = "id")
    private VideoAsset videoAsset;

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
