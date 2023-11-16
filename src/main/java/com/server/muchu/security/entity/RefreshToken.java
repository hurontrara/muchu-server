package com.server.muchu.security.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import static com.server.muchu.security.config.TokenConfig.*;

@Getter
@RedisHash(value = "refreshToken", timeToLive = REFRESH_TOKEN_EXPIRE_TIME_FOR_REDIS)
@Builder
public class RefreshToken {

    @Id
    private Long id;

    private String refreshToken;

}
