package org.streaminho.app.streaminho.models.dto;

public record RegisterUserDto(
    String username,
    String email,
    String password
) {
}
