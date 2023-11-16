package com.server.muchu.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserFindIdDto {

    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "적절한 이메일 형식으로 입력해주세요.")
    private String email;

}
