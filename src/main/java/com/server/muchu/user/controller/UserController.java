package com.server.muchu.user.controller;

import com.server.muchu.security.entity.SecurityUser;
import com.server.muchu.user.dto.UserChangePasswordDto;
import com.server.muchu.user.dto.UserFindIdDto;
import com.server.muchu.user.dto.UserFindPasswordDto;
import com.server.muchu.user.dto.UserSignUpDto;
import com.server.muchu.user.repository.UserRepository;
import com.server.muchu.user.service.UserService;
import com.server.muchu.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.server.muchu.common.method.GlobalMethod.*;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    // 로그인
    @GetMapping("/login")
    public String loginPage() {

        return "user/login";
    }

    @PostMapping("/login")
    public void login(HttpServletResponse response) throws IOException {

        response.sendRedirect("");

    }

    //회원가입
    @GetMapping("/signup")
    public String signUpPage(@ModelAttribute UserSignUpDto userSignUpDto) {
        return "user/signUp";
    }

    @PostMapping(value = "/signup")
    public String signUp(@Validated @ModelAttribute UserSignUpDto userSignUpDto, BindingResult bindingResult) {

        // 형식 오류 + 중복 체크
        boolean checkValue = userService.signUpValueCheckByDto(userSignUpDto, bindingResult);
        if (checkValue) {
            return "user/signUp";
        }

        // 계정 생성
        userService.signUpByDto(userSignUpDto);

        return "user/signUpSuccessAndToMain";
    }

    // 아이디 찾기
    @GetMapping(value = "/find-id")
    public String findIdPage(@ModelAttribute UserFindIdDto userFindIdDto) {
        return "user/findId";
    }

    @PostMapping(value = "/find-id")
    public String findId(@Validated @ModelAttribute UserFindIdDto userFindIdDto, BindingResult bindingResult, Model model) {

        // 형식 오류
        if (bindingResult.hasErrors()) {
            return "user/findId";
        }

        Optional<User> optionalUser = userRepository.findByEmailAndSocialIsFalse(userFindIdDto.getEmail());
        optionalUser.ifPresent(user -> model.addAttribute("username", optionalUser.get().getUsername()));

        return "user/findIdResult";

    }

    // 비밀번호 찾기 -> 1. 변경 링크 보내는 방법, 2. 임시 비밀번호 발급 방법  (1번 채택)
    @GetMapping(value = "/find-password")
    public String findPasswordPage(@ModelAttribute UserFindPasswordDto userFindPasswordDto) {
        return "user/findPassword";
    }

    @PostMapping(value = "/find-password")
    public String findPassword(@Validated @ModelAttribute UserFindPasswordDto userFindPasswordDto, BindingResult bindingResult, Model model) {

        // 형식 오류
        if (bindingResult.hasErrors()) {
            return "user/findPassword";
        }

        userService.findPasswordByDto(userFindPasswordDto, model);

        return "user/findPasswordResult";
    }

    @GetMapping("/change-password")
    public String changePasswordPage(@ModelAttribute UserChangePasswordDto userChangePasswordDto) {
        return "user/changePasswordPage";
    }

    @PostMapping("/change-password")
    public String changePassword(@Validated @ModelAttribute UserChangePasswordDto userChangePasswordDto,
                                 BindingResult bindingResult,
                                 Model model,
                                 @RequestParam String uuid) {

        if (bindingResult.hasErrors()) {
            return "user/changePasswordPage";
        }

        userService.changePasswordByDtoAndUUID(userChangePasswordDto, uuid, model);

        return "user/changePasswordResult";
    }


    // 마이페이지
    @GetMapping("/mypage")
    public String myPage(Model model) {

        SecurityUser securityUser = getSecurityUser();
        User user = securityUser.getUser();

        model.addAttribute("user", user);

        return "user/myPage";

    }
}
