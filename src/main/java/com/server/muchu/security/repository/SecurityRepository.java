package com.server.muchu.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.server.muchu.user.entity.User;

public interface SecurityRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

}
