package com.server.muchu.security.service;

import com.server.muchu.security.entity.SecurityUser;
import com.server.muchu.security.repository.SecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.server.muchu.user.entity.User;

import static com.server.muchu.security.error.SecurityErrorMessage.*;

@Service
@Transactional
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    private final SecurityRepository securityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<User> user = securityRepository.findByUsername(username);

        // 아이디가 존재하지 않을 때의 예외 처리
        return user.map(SecurityUser::new).orElseThrow(() -> new SecurityException(LOGIN_FAIL.getMessage()));
    }

    public UserDetails loadUserById(Long id) {

        Optional<User> user = securityRepository.findById(id);

        // id 값이 존재하지 않을 때의 예외 처리
        return user.map(SecurityUser::new).orElseThrow(() -> new SecurityException(LOGIN_FAIL.getMessage()));
    }


}
