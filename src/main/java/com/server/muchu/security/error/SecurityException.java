package com.server.muchu.security.error;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class SecurityException extends AuthenticationException {

    public SecurityException(String msg) {
        super(msg);
    }

}
