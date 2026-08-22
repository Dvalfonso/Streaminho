package org.streaminho.app.streaminho.user.models.dto;

import org.streaminho.app.streaminho.user.models.Role;

import java.time.LocalDateTime;

public record UserDto(
    String username,
    String email,
    Role role,
    LocalDateTime createdAt
) {
}
