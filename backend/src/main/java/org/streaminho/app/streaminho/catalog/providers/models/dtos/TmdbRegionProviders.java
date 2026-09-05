package org.streaminho.app.streaminho.catalog.providers.models.dtos;

import java.util.List;

public record TmdbRegionProviders(
        String link,
        List<TmdbProvider> flatrate,
        List<TmdbProvider> rent,
        List<TmdbProvider> buy
) {}
