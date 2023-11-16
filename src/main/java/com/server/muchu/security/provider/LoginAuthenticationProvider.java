package com.server.muchu.security.provider;

import com.server.muchu.security.authentication.LoginAuthentication;
import com.server.muchu.security.entity.SecurityUser;
import com.server.muchu.security.service.SecurityUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.server.muchu.security.error.SecurityErrorMessage.*;

@Slf4j
@RequiredArgsConstructor
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final SecurityUserService securityUserService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        SecurityUser securityUser = (SecurityUser) securityUserService.loadUserByUsername(username);

        passwordCheck(password, securityUser.getPassword());

        return new LoginAuthentication(securityUser.getId(), username, password);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return LoginAuthentication.class.isAssignableFrom(authentication);
    }


    private void passwordCheck(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword))
            throw new SecurityException(LOGIN_FAIL.getMessage());
    }

}
