package org.streaminho.app.streaminho.catalog.movies.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String duration;
    private byte[] poster;
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
