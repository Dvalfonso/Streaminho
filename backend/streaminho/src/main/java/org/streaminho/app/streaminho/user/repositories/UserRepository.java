package org.streaminho.app.streaminho.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.streaminho.app.streaminho.user.models.dao.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
