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

    private final EntityManager em;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {

        User user1 = User.builder()
                .username("someID1")
                .password(passwordEncoder.encode("1q2w3e4r!"))
                .grade(UserGrade.USER)
                .name("name1")
                .nickname("nickname1")
                .email("someID1@naver.com")
                .build();

        User user2 = User.builder()
                .username("someID2")
                .password(passwordEncoder.encode("1q2w3e4r!"))
                .grade(UserGrade.USER)
                .name("name2")
                .nickname("nickname2")
                .email("someID2@naver.com")
                .build();

        User user3 = User.builder()
                .username("someID3")
                .password(passwordEncoder.encode("1q2w3e4r!"))
                .grade(UserGrade.USER)
                .name("name3")
                .nickname("nickname3")
                .email("someID3@naver.com")
                .build();

        User user4 = User.builder()
                .username("someID4")
                .password(passwordEncoder.encode("1q2w3e4r!"))
                .grade(UserGrade.USER)
                .name("name4")
                .nickname("nickname4")
                .email("sorkdksl7566@naver.com")
                .build();


        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);


    }

}
