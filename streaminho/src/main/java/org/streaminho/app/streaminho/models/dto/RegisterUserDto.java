package org.streaminho.app.streaminho.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud de registro de usuario")
public record RegisterUserDto(
    String username,
    String email,
    String password
) {
}
