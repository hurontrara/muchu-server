package com.server.muchu.common.method;

import com.server.muchu.security.entity.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class GlobalMethod {

    public static Optional<SecurityUser> getOptionalSecurityUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) return Optional.empty();

        SecurityUser securityUser = (SecurityUser) authentication.getDetails();

        return Optional.ofNullable(securityUser);
    }

    public static SecurityUser getSecurityUser() {

        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getDetails();

    }

}
