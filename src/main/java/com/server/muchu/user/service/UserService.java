package com.server.muchu.user.service;

import com.server.muchu.user.dto.MailingDto;
import com.server.muchu.user.dto.UserChangePasswordDto;
import com.server.muchu.user.dto.UserFindPasswordDto;
import com.server.muchu.user.dto.UserSignUpDto;
import com.server.muchu.user.entity.UserGrade;
import com.server.muchu.user.entity.UserPasswordChange;
import com.server.muchu.user.repository.UserPasswordChangeRepository;
import com.server.muchu.user.repository.UserRepository;
import com.server.muchu.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Value("${domain}")
    private String domain;
    private final UserRepository userRepository;
    private final UserPasswordChangeRepository userPasswordChangeRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    public boolean signUpValueCheckByDto(UserSignUpDto userSignUpDto, BindingResult bindingResult) {

        // 형식 오류
        if (bindingResult.hasErrors()) {
            return true;
        }

        // 중복 체크(아이디, 닉네임, 이메일)
        if (userRepository.findByUsername(userSignUpDto.getUsername()).isPresent()) {
            bindingResult.addError(new FieldError("userSignUpDto", "username", "이미 존재하는 아이디입니다."));
        } else if (userRepository.findByNickname(userSignUpDto.getNickname()).isPresent()) {
            bindingResult.addError(new FieldError("userSignUpDto", "nickname", "이미 존재하는 닉네임입니다."));
        } else if (userRepository.findByEmailAndSocialIsFalse(userSignUpDto.getEmail()).isPresent()) {
            bindingResult.addError(new FieldError("userSignUpDto", "email", "이미 존재하는 이메일입니다."));
        }

        return bindingResult.hasErrors();

    }

    public void signUpByDto(UserSignUpDto userSignUpDto) {

        User user = User.builder()
                .username(userSignUpDto.getUsername())
                .password(passwordEncoder.encode(userSignUpDto.getPassword()))
                .grade(UserGrade.USER)
                .nickname(userSignUpDto.getNickname())
                .email(userSignUpDto.getEmail())
                .build();

        userRepository.save(user);

    }

    public void findPasswordByDto(UserFindPasswordDto userFindPasswordDto, Model model) {

        Optional<User> optionalUser = userRepository.findByEmailAndSocialIsFalse(userFindPasswordDto.getEmail());

        optionalUser.ifPresent(user -> mailingProcess(user, model));

    }

    public void changePasswordByDtoAndUUID(UserChangePasswordDto userChangePasswordDto, String uuid, Model model) {

        Optional<UserPasswordChange> optionalUserPasswordChange = userPasswordChangeRepository.findByUuid(uuid);

        optionalUserPasswordChange.ifPresent(userPasswordChange -> passwordChangeProcess(userPasswordChange, userChangePasswordDto.getPassword(), model));

    }


    private void mailingProcess(User user, Model model) {

        // dto 객체 생성
        String uuid = UUID.randomUUID().toString();
        MailingDto mailingDto = new MailingDto(user.getEmail(), domain, uuid);

        // 엔티티 저장 후 이메일 보내기 (이메일 보내기 실패하면, 엔티티는 저장되지 않음(트랜잭션))
        UserPasswordChange userPasswordChange = UserPasswordChange.builder().user(user).uuid(uuid).build();
        userPasswordChangeRepository.save(userPasswordChange);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailingDto.getTo());
        message.setSubject(mailingDto.getSubject());
        message.setText(mailingDto.getMessage());
        mailSender.send(message);

        model.addAttribute("mailingSuccess", true);

    }



    private void passwordChangeProcess(UserPasswordChange UserPasswordChange, String password, Model model) {

        User user = UserPasswordChange.getUser();

        user.ChangePassword(passwordEncoder.encode(password));

        model.addAttribute("changeSuccess", true);

    }
}
