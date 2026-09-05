package org.streaminho.app.streaminho.catalog.providers.models.dtos;

public record TmdbProvider(
        Integer provider_id,
        String provider_name,
        String logo_path
) {}