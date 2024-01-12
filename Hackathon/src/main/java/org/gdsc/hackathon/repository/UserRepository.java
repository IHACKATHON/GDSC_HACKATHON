package org.gdsc.hackathon.repository;

import org.gdsc.hackathon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    Optional<User> findUserById(Long id);

    boolean existsByEmail(String email);
}
