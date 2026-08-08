package org.streaminho.app.streaminho.auth.models.dto.login;

public record LoginRequest(
        String email,
        String password
) {
}
