package org.streaminho.app.streaminho.auth.service;

import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.streaminho.app.streaminho.user.models.AuthProvider;
import org.streaminho.app.streaminho.user.models.Role;
import org.streaminho.app.streaminho.user.models.dao.User;
import org.streaminho.app.streaminho.auth.models.dto.login.LoginRequest;
import org.streaminho.app.streaminho.auth.models.dto.login.LoginResponse;
import org.streaminho.app.streaminho.auth.models.dto.register.RegisterResponse;
import org.streaminho.app.streaminho.auth.models.dto.register.RegisterUserDto;
import org.streaminho.app.streaminho.user.repositories.UserRepository;
import org.streaminho.app.streaminho.security.JwtService;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

        User user = userRepository.findByEmail(loginRequest.email()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}
