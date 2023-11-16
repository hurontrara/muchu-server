package com.server.muchu.security.config;

import com.server.muchu.security.filter.FilterService;
import com.server.muchu.security.filter.JWTAuthenticationFilter;
import com.server.muchu.security.filter.LoginAuthenticationFilter;
import com.server.muchu.security.filter.MyPageAuthenticationFilter;
import com.server.muchu.security.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final RedisRepository redisRepository;
    private final AuthenticationManager authenticationManager;

    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter() {
        return new LoginAuthenticationFilter(filterService());
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter(filterService());
    }

    @Bean
    public MyPageAuthenticationFilter myPageAuthenticationFilter() {
        return new MyPageAuthenticationFilter(filterService());
    }


    @Bean
    public FilterService filterService() {
        return new FilterService(authenticationManager, redisRepository);
    }

}
