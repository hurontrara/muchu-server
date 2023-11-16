package com.server.muchu.security.authentication;

import com.server.muchu.security.entity.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthentication implements Authentication {

    private String jwt;
    private SecurityUser securityUser;


    public JWTAuthentication(String jwt) {
        this.jwt = jwt;
    }

    public JWTAuthentication(SecurityUser securityUser) {
        this.securityUser = securityUser;
    }

    public String getToken() {
        return jwt;
    }

    @Override
    public String getPrincipal() {
        return securityUser.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return securityUser.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return securityUser.getPassword();
    }

    @Override
    public SecurityUser getDetails() {
        return securityUser;
    }

    @Override
    public boolean isAuthenticated() {
        return securityUser != null;
    }

    @Override
    public String getName() {
        return securityUser.getName();
    }

    // 사용하지 않음

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

        if (!isAuthenticated)
            securityUser = null;

    }



}
