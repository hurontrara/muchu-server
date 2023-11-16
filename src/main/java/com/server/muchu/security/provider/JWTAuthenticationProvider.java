package com.server.muchu.security.provider;

import com.server.muchu.security.authentication.JWTAuthentication;
import com.server.muchu.security.entity.SecurityUser;
import com.server.muchu.security.service.SecurityUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final SecurityUserService securityUserService;

    @Value("${jwt.signing.key}")
    private String signingKey;

    // 인증 매니저로부터 인증 책임을 위임 받음
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        JWTAuthentication jwtAuthentication = (JWTAuthentication) authentication;
        String jwt = jwtAuthentication.getToken();

        // 유효성 체크
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt).getBody();

        // 유저 객체 받아오기
        Long id = Long.valueOf(claims.getSubject());
        SecurityUser securityUser = (SecurityUser) securityUserService.loadUserById(id);

        // 인증 객체 생성
        return new JWTAuthentication(securityUser);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthentication.class.isAssignableFrom(authentication);
    }


}
