package com.server.muchu.user.dto;


import lombok.Getter;


@Getter
public class MailingDto {

    private final String subject = "[무추] 비밀번호 변경 링크입니다.";
    private final String explainAnnouncement = "비밀번호 변경 링크를 보내드립니다.\n링크에 들어가셔서 비밀번호 변경해주세요.\n";

    private final String to;
    private final String message;

    public MailingDto(String to, String domain, String uuid) {
        this.to = to;
        this.message = explainAnnouncement + domain + "/change-password?uuid=" + uuid;
    }

}
