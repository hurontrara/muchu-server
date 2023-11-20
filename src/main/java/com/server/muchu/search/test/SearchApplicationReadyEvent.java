package com.server.muchu.search.test;

import com.server.muchu.search.entity.Meme;
import com.server.muchu.user.entity.User;
import com.server.muchu.user.entity.UserGrade;
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

        Meme meme1 = Meme.builder().fileName("file_1.jpg").clusterNum(1).build();
        Meme meme2 = Meme.builder().fileName("file_2.jpg").clusterNum(1).build();
        Meme meme3 = Meme.builder().fileName("file_3.jpg").clusterNum(1).build();
        Meme meme4 = Meme.builder().fileName("file_4.jpg").clusterNum(2).build();
        Meme meme5 = Meme.builder().fileName("file_5.jpg").clusterNum(3).build();

        em.persist(meme1);
        em.persist(meme2);
        em.persist(meme3);
        em.persist(meme4);
        em.persist(meme5);


    }


}
