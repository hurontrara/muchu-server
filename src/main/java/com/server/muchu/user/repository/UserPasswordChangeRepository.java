package com.server.muchu.user.repository;

import com.server.muchu.user.entity.UserPasswordChange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPasswordChangeRepository extends JpaRepository<UserPasswordChange, Long> {

    Optional<UserPasswordChange> findByUuid(String uuid);

}
