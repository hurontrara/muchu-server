package com.server.muchu.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum UserAuthority {

    CREATE(new SimpleGrantedAuthority("CREATE")),
    READ(new SimpleGrantedAuthority("READ")),
    UPDATE(new SimpleGrantedAuthority("UPDATE")),
    DELETE(new SimpleGrantedAuthority("DELETE")),
    MANAGER(new SimpleGrantedAuthority("MANAGER"));

    private final GrantedAuthority authority;

}
