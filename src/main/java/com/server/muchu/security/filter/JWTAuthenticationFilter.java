package com.server.muchu.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.server.muchu.security.error.SecurityErrorMessage.*;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final FilterService filterService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        // 1. 액세스 토큰 존재하는 경우 (쿠키의 유효기간 = 액세스 토큰 유효 기간으로 설정해놓았으므로, 액세스 토큰 만료 시(expiredJWTException) 재발급은 고려하지 않음
        // 2. 액세스 토큰 존재하지 않고 리프레시 토큰만 존재하는 경우 -> 액세스 토큰 재발급
        // 3. 둘다 없는 경우

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (filterService.accessTokenCheck(cookies, request, response, filterChain)) return;
        else if (filterService.refreshTokenCheck(cookies, request, response, filterChain)) return;
        else throw new SecurityException(LOGIN_NEED.getMessage());

    }

    // 해당하는 경로만 토큰 체크하도록
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String[] excludePath = {"/tokenCheck"};
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).noneMatch(path::startsWith);

    }

}
