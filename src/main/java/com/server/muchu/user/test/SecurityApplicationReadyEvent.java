package com.server.muchu.user.test;

import com.server.muchu.user.entity.UserGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import com.server.muchu.user.entity.User;

@Component
@RequiredArgsConstructor
public class SecurityApplicationReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {


    }

}
