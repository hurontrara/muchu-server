package com.server.muchu.security.oauth.controller;


import com.server.muchu.security.oauth.params.KakaoLoginParams;
import com.server.muchu.security.oauth.params.NaverLoginParams;
import com.server.muchu.security.oauth.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/oauth2/code")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthLoginService oAuthLoginService;

    @GetMapping("/naver")
    public void naverCodeResponse(NaverLoginParams params, HttpServletResponse response) throws IOException {

        oAuthLoginService.login(params, response);

        response.sendRedirect("/");


    }

    @GetMapping("/kakao")
    public void kakaoCodeResponse(KakaoLoginParams params, HttpServletResponse response) throws IOException {

        oAuthLoginService.login(params, response);

        response.sendRedirect("/");


    }

}
