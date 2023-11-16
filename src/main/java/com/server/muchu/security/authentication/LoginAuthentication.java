package com.server.muchu.security.authentication;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class LoginAuthentication implements Authentication {

    private Long id;
    private final String username;
    private final String password;

    public LoginAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginAuthentication(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public boolean isAuthenticated() {
        return id != null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

        if (!isAuthenticated)
            id = null;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }


}
