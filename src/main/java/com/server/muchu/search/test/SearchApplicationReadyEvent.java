package com.server.muchu.search.test;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class SearchApplicationReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final EntityManager em;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {

    }


}
