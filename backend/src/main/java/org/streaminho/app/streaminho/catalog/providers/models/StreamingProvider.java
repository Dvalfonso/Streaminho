package org.streaminho.app.streaminho.catalog.providers.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "streaming_providers")
@Getter
@Setter
@NoArgsConstructor
public class StreamingProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer tmdbProviderId;

    @Column(nullable = false)
    private String name; // "Netflix", "Max", "Disney Plus"

    private String logoPath; // logo_path, para armar la URL de imagen igual que el poster
}
