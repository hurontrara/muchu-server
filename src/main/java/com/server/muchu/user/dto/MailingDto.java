package com.server.muchu.user.dto;


import lombok.Getter;


@Getter
public class MailingDto {

    private final String to;
    private final String uuid;
    private final String message;

    private final String subject = "[무추] 비밀번호 변경 링크입니다.";
    private final String baseMessage = "<h1>비밀번호 변경 링크를 보내드립니다.</h1><br>"
            + "<h2>링크에 들어가셔서 비밀번호 변경해주세요.</h2><br>";

    public MailingDto(String domain, String to, String uuid) {
        this.to = to;
        this.uuid = uuid;

        this.message = baseMessage + String.format("<a href='%s'>비밀번호 변경 링크</a>", "http://" + domain + "/change-password?uuid=" + uuid);

    }

}
