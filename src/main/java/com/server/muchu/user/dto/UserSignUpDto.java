package com.server.muchu.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class UserSignUpDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "5~20글자 영문 및 숫자를 입력해주세요.")
    private String username;

    @Pattern(regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "8~15 글자의 영문, 숫자 및 특수문자를 입력해주세요.")
    private String password;

    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$", message = "2~10 글자의 한글, 영문, 숫자를 입력해주세요.")
    private String nickname;

    @Pattern(regexp = "^[가-힣a-zA-Z]{2,10}$", message = "2~10 글자의 한글, 영문을 입력해주세요.")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "적절한 이메일 형식으로 입력해주세요.")
    private String email;

}
