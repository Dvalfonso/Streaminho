package org.streaminho.app.streaminho.services;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.streaminho.app.streaminho.models.dao.AuthProvider;
import org.streaminho.app.streaminho.models.dao.Role;
import org.streaminho.app.streaminho.models.dao.User;
import org.streaminho.app.streaminho.models.dto.login.LoginRequest;
import org.streaminho.app.streaminho.models.dto.login.LoginResponse;
import org.streaminho.app.streaminho.models.dto.register.RegisterResponse;
import org.streaminho.app.streaminho.models.dto.register.RegisterUserDto;
import org.streaminho.app.streaminho.repositories.UserRepository;
import org.streaminho.app.streaminho.security.JwtService;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public RegisterResponse registerUser(RegisterUserDto registerUserDto) {
        if (userRepository.existsByEmail(registerUserDto.email())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setUsername(registerUserDto.username());
        user.setEmail(registerUserDto.email());
        user.setPassword(passwordEncoder.encode(registerUserDto.password()));
        user.setAuthProvider(AuthProvider.LOCAL);
        user.setRole(Role.USER);
        user.setLocked(false);

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return new RegisterResponse(token, user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public LoginResponse login(LoginRequest loginRequest) {
        return new LoginResponse("Asdf");
    }
}
