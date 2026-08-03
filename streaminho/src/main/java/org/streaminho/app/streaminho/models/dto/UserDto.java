package org.streaminho.app.streaminho.models.dto;

import org.streaminho.app.streaminho.models.dao.Role;

import java.time.LocalDateTime;

public record UserDto(
    String username,
    String email,
    Role role,
    LocalDateTime createdAt
) {
}
