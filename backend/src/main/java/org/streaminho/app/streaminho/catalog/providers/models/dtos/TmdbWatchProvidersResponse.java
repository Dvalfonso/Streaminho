package org.streaminho.app.streaminho.catalog.providers.models.dtos;

import java.util.Map;

public record TmdbWatchProvidersResponse(
        Map<String, TmdbRegionProviders> results
) {}
