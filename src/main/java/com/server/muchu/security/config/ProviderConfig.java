package com.server.muchu.security.config;

import com.server.muchu.security.provider.JWTAuthenticationProvider;
import com.server.muchu.security.provider.LoginAuthenticationProvider;
import com.server.muchu.security.service.SecurityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ProviderConfig {

    private final SecurityUserService securityUserService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public LoginAuthenticationProvider logInAuthenticationProvider() {
        return new LoginAuthenticationProvider(securityUserService, passwordEncoder);
    }

    @Bean
    public JWTAuthenticationProvider jwtAuthenticationProvider() {
        return new JWTAuthenticationProvider(securityUserService);
    }

}
