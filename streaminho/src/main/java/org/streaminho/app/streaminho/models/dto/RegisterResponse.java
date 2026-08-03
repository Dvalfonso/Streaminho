package org.streaminho.app.streaminho.models.dto;

import org.streaminho.app.streaminho.models.dao.Role;

public record RegisterResponse(
        String token,
        Long userId,
        String email,
        String username,
        Role userRole
) {
}
