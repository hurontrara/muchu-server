package com.server.muchu.security.config;

import lombok.Getter;

@Getter
public class TokenConfig {

    // AccessToken : a hour
    // RefreshToken : 7 days

    public static final long ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 * 1000L;
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;

    public static final long REFRESH_TOKEN_EXPIRE_TIME_FOR_REDIS = 7 * 24 * 60 * 60;

    public static final int ACCESS_TOKEN_EXPIRE_TIME_FOR_COOKIE = 60 * 60;
    public static final int REFRESH_TOKEN_EXPIRE_TIME_FOR_COOKIE = 7 * 24 * 60 * 60;


}
