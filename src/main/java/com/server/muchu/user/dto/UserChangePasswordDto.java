package com.server.muchu.user.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserChangePasswordDto {

    @Pattern(regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "8~15 글자의 영문, 숫자 및 특수문자를 입력해주세요.")
    private String password;

}
