package com.server.muchu.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


import java.util.List;

import static com.server.muchu.user.entity.UserAuthority.*;

@Getter
@RequiredArgsConstructor
public enum UserGrade {

    USER("User", List.of(CREATE.getAuthority(), READ.getAuthority(), UPDATE.getAuthority(), DELETE.getAuthority())),
    ADMIN("Admin", List.of(CREATE.getAuthority(), READ.getAuthority(), UPDATE.getAuthority(), DELETE.getAuthority(), MANAGER.getAuthority()));


    private final String name;
    private final List<? extends GrantedAuthority> authorities;


}
