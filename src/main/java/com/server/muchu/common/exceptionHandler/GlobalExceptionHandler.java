package com.server.muchu.common.exceptionHandler;

import com.server.muchu.security.error.CustomSecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final TemplateEngine springTemplateEngine;

    @ExceptionHandler(CustomSecurityException.class)
    public ResponseEntity<String> authException(Exception e) {

        Context context = new Context(Locale.KOREA);
        context.setVariable("exceptionMessage", e.getMessage());

        context.setVariable("loginPage", "/login");

        String html = springTemplateEngine.process("error/authException", context);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(html);

    }

    @ExceptionHandler(Exception.class)
    public String unknownException(Exception e) {

        return "error/unknownException";
    }


}
