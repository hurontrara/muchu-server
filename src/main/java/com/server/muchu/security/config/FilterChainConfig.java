package com.server.muchu.security.config;

import com.server.muchu.security.filter.JWTAuthenticationFilter;
import com.server.muchu.security.filter.LoginAuthenticationFilter;
import com.server.muchu.security.filter.MyPageAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class FilterChainConfig {

    private final AuthenticationEntryPoint entryPoint;
    private final LoginAuthenticationFilter loginAuthenticationFilter;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final MyPageAuthenticationFilter myPageAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and().authorizeRequests().anyRequest().permitAll()

                .and().addFilterAfter(loginAuthenticationFilter, ExceptionTranslationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, LoginAuthenticationFilter.class)
                .addFilterAfter(myPageAuthenticationFilter, JWTAuthenticationFilter.class)

                .exceptionHandling(handler -> handler.authenticationEntryPoint(entryPoint))

                .logout()
                .logoutUrl("/logout")
                .deleteCookies("accessToken")
                .deleteCookies("refreshToken")
                .logoutSuccessUrl("/");

        return http.build();
    }

}
