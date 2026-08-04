package org.streaminho.app.streaminho.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.streaminho.app.streaminho.models.dto.RegisterResponse;
import org.streaminho.app.streaminho.models.dto.RegisterUserDto;
import org.streaminho.app.streaminho.services.AuthService;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Registrar un usuario")
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        RegisterResponse response = authService.registerUser(registerUserDto);
        URI location = URI.create("/api/users/" + response.userId());
        return ResponseEntity.created(location).body(response);
    }
}
