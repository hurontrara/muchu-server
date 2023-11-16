package com.server.muchu.security.config;

import com.server.muchu.security.provider.JWTAuthenticationProvider;
import com.server.muchu.security.provider.LoginAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

@Configuration
@RequiredArgsConstructor
public class ManagerConfig {

    private final LoginAuthenticationProvider loginAuthenticationProvider;
    private final JWTAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager() {

        return new ProviderManager(loginAuthenticationProvider, jwtAuthenticationProvider);
    }

}
