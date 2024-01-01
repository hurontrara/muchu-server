package com.server.muchu.security.oauth.service;

import com.server.muchu.security.filter.FilterService;
import com.server.muchu.security.oauth.infoResponse.OAuthInfoResponse;
import com.server.muchu.security.oauth.params.OAuthLoginParams;
import com.server.muchu.security.repository.SecurityRepository;
import com.server.muchu.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.server.muchu.user.entity.UserGrade.*;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {

    private final SecurityRepository securityRepository;
    private final RequestOAuthInfoService requestOAuthInfoService;
    private final FilterService filterService;

    public void login(OAuthLoginParams params, HttpServletResponse response) {

        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long userId = findOrCreateMember(oAuthInfoResponse);
        tokenIssuance(userId, response);

    }

    private void tokenIssuance(Long id, HttpServletResponse response) {

        String[] accessAndRefreshTokenList = filterService.getAccessTokenAndRefreshTokenById(id);
        String accessToken = accessAndRefreshTokenList[0];
        String refreshToken = accessAndRefreshTokenList[1];

        filterService.saveRefreshTokenByTokenAndId(refreshToken, id);

        filterService.saveTokensToCookie(accessToken, refreshToken, response);

    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {

        Optional<User> optionalUser = securityRepository.findByEmail(oAuthInfoResponse.getEmail());

        return optionalUser.map(User::getId).orElseGet(() -> newMember(oAuthInfoResponse));


    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {

        User user = User.builder()
                .name(oAuthInfoResponse.getName())
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .social(oAuthInfoResponse.getOAuthProvider())
                .grade(USER)
                .build();

        securityRepository.save(user);

        return user.getId();

    }
}
