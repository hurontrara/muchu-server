package com.server.muchu.security.config;

import com.server.muchu.security.error.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@RequiredArgsConstructor
public class EntryPointConfig {

    private final HandlerExceptionResolver handlerExceptionResolver; // @Qualifier 생략에 따른 resolver -> handlerExceptionResolver

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint(handlerExceptionResolver);
    }


}
