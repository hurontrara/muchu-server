package com.server.muchu.security.filter;

import com.server.muchu.security.authentication.LoginAuthentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class LoginAuthenticationFilter extends OncePerRequestFilter {

    private final FilterService filterService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 인증
        LoginAuthentication loginAuthentication = filterService.authenticateLogin(request);

        // 액세스 토큰, 리프레시 토큰 발급
        String[] accessAndRefreshTokenList = filterService.getAccessTokenAndRefreshTokenById(loginAuthentication.getId());
        String accessToken = accessAndRefreshTokenList[0];
        String refreshToken = accessAndRefreshTokenList[1];

        // 리프레시 토큰 레디스 저장
        // 트랜잭션 미지원 -> 추후 보완 요망
        filterService.saveRefreshTokenByTokenAndId(refreshToken, loginAuthentication.getId());

        // https://simsimjae.tistory.com/m/482 (토큰을 어디에 저장해야 안전할까)
        // thymeleaf 를 사용하므로, 여기에서는 그냥 둘 다 쿠키에 저장
        filterService.saveTokensToCookie(accessToken, refreshToken, response);

        // 필터 체인
        filterChain.doFilter(request, response);

    }

    // POST method 이고, /login 일때만 적용
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String[] excludePath = {"/login"};
        String path = request.getRequestURI();
        return !(Arrays.stream(excludePath).anyMatch(path::startsWith) && request.getMethod().equals("POST"));

    }



}
