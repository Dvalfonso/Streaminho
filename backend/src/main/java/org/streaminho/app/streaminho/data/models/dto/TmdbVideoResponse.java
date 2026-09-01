package org.streaminho.app.streaminho.data.models.dto;

import java.util.List;

public record TmdbVideoResponse(
        List<TmdbVideo> results
) {
}
