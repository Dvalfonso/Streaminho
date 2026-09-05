package org.streaminho.app.streaminho.catalog.providers.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.streaminho.app.streaminho.catalog.providers.models.StreamingProvider;

import java.util.Optional;

public interface StreamingProviderRepository extends JpaRepository<StreamingProvider, Long> {
    Optional<StreamingProvider> findByTmdbProviderId(Integer tmdbProviderId);
}
