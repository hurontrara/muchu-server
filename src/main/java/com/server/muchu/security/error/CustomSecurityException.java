package com.server.muchu.security.error;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class CustomSecurityException extends AuthenticationException {

    public CustomSecurityException(String msg) {
        super(msg);
    }

}
