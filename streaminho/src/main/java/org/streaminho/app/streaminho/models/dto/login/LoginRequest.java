package org.streaminho.app.streaminho.models.dto.login;

public record LoginRequest(
        String email,
        String password
) {
}
