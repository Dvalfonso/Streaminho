package org.streaminho.app.streaminho.catalog.providers.models.dtos;

import org.streaminho.app.streaminho.catalog.providers.models.StreamingProvider;

public record StreamingProviderDto(
        String name,
        String logoUrl
) {
    public static StreamingProviderDto from(StreamingProvider provider) {
        return new StreamingProviderDto(
                provider.getName(),
                "https://image.tmdb.org/t/p/w92" + provider.getLogoPath()
        );
    }
}