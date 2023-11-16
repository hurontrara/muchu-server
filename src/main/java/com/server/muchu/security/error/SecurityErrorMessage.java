package com.server.muchu.security.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SecurityErrorMessage { // UPPER_SNAKE_CASE

    LOGIN_FAIL("올바르지 않은 ID 혹은 비밀번호입니다."),
    LOGIN_NEED("로그인이 필요합니다.");


    private final String message;


}
