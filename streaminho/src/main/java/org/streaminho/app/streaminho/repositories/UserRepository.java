package org.streaminho.app.streaminho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.streaminho.app.streaminho.models.dao.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> existsByEmail(String email);
}
