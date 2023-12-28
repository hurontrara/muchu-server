package com.server.muchu.main.controller;

import com.server.muchu.security.entity.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static com.server.muchu.common.method.GlobalMethod.getOptionalSecurityUser;


@Slf4j
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model) {

        // 널 값이 가능함을 명시화
        Optional<SecurityUser> optionalSecurityUser = getOptionalSecurityUser();

        optionalSecurityUser.ifPresent(securityUser -> model.addAttribute("user", securityUser));

        return "main/main";
    }

}
