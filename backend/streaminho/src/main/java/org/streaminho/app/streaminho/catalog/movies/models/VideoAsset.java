package org.streaminho.app.streaminho.catalog.movies.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "video_assets")
@NoArgsConstructor
public class VideoAsset {
    @Id
    @GeneratedValue
    private Long id;
    private String storageKey;
    private String format;
    private String size;
    private String duration;
}
