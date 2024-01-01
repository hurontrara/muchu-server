package com.server.muchu.security.repository;

import com.server.muchu.security.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RefreshToken, Long> {
}
