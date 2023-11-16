package com.server.muchu.security.filter;

import com.server.muchu.security.authentication.JWTAuthentication;
import com.server.muchu.security.authentication.LoginAuthentication;
import com.server.muchu.security.entity.RefreshToken;
import com.server.muchu.security.entity.SecurityUser;
import com.server.muchu.security.repository.RedisRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.server.muchu.security.config.TokenConfig.*;

@Slf4j
@RequiredArgsConstructor
public class FilterService {

    private final AuthenticationManager authenticationManager;
    private final RedisRepository redisRepository;

    @Value("${jwt.signing.key}")
    private String signingKey;


    public boolean accessTokenCheck(Cookie[] cookies, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        assert cookies != null;

        // 인증 매니저에게 인증 책임 위임
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("accessToken")) {

                String jwt = cookie.getValue();

                Authentication authentication = new JWTAuthentication(jwt);

                Authentication jwtAuthentication = authenticationManager.authenticate(authentication);

                SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);

                filterChain.doFilter(request, response);

                return true;

            }
        }

        return false;

    }

    // 액세스 토큰은 존재하지 않는 상황 보장되어 있음
    public boolean refreshTokenCheck(Cookie[] cookies, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refreshToken")) {

                String jwt = cookie.getValue();

                Authentication authentication = new JWTAuthentication(jwt);

                Authentication jwtAuthentication = authenticationManager.authenticate(authentication);

                SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);

                // 추가로, 액세스 토큰 발급
                SecurityUser securityUser = (SecurityUser) jwtAuthentication.getDetails();
                Long id = securityUser.getId();

                String accessToken = getAccessTokenById(id, new Date());
                Cookie accessCookie = new Cookie("accessToken", accessToken);
                accessCookie.setMaxAge(ACCESS_TOKEN_EXPIRE_TIME_FOR_COOKIE);

                response.addCookie(accessCookie);

                filterChain.doFilter(request, response);

                return true;

            }
        }

        return false;
    }

    public LoginAuthentication authenticateLogin(HttpServletRequest request) {

        String username = request.getParameter("username"); // 타임리프를 사용하므로, getHeader 등이 아닌 getParameter 지정

        String password = request.getParameter("password");

        Authentication authentication = new LoginAuthentication(username, password);

        LoginAuthentication loginAuthentication = (LoginAuthentication) authenticationManager.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(loginAuthentication);

        return loginAuthentication;
    }

    public String[] getAccessTokenAndRefreshTokenById(Long id) {

        Date now = new Date();

        String accessToken = getAccessTokenById(id, now);
        String refreshToken = getRefreshTokenById(id, now);

        return new String[]{accessToken, refreshToken};

    }

    private String getAccessTokenById(Long id, Date now) {

        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();

    }

    private String getRefreshTokenById(Long id, Date now) {
        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }


    public void saveRefreshTokenByTokenAndId(String refreshToken, Long id) {
        RefreshToken refreshTokenEntity = RefreshToken.builder().id(id).refreshToken(refreshToken).build();
        redisRepository.save(refreshTokenEntity);
    }

    public void saveTokensToCookie(String accessToken, String refreshToken, HttpServletResponse response) {

        Cookie accessCookie = new Cookie("accessToken", accessToken);
        accessCookie.setMaxAge(ACCESS_TOKEN_EXPIRE_TIME_FOR_COOKIE);
        Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
        refreshCookie.setMaxAge(REFRESH_TOKEN_EXPIRE_TIME_FOR_COOKIE);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

    }
}