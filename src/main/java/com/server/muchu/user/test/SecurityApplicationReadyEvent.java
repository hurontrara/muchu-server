package com.server.muchu.user.test;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class SecurityApplicationReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {


    }

}
