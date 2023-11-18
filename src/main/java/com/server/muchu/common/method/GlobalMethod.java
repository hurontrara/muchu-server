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

    // 이미 JWT 필터에 등록이 되어있는 페이지에서의 호출 -> 로그인된 사용자임이(토큰 유효) 보장된 상황
    public static SecurityUser getSecurityUser() {

        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getDetails();

    }

}
