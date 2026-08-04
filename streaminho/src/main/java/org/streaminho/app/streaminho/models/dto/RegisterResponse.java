package org.streaminho.app.streaminho.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.streaminho.app.streaminho.models.dao.Role;

@Schema(description = "Respuesta de registro de usuario")
public record RegisterResponse(
        String token,
        Long userId,
        String email,
        String username,
        Role userRole
) {
}
