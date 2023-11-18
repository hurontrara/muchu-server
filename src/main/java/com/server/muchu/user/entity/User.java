package com.server.muchu.user.entity;

import com.server.muchu.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


// 엔티티에는, NoArgsConstructor 가 필수적
// Builder 패턴에서, 생성자를 정의하게 되면 전체 생성자 또한 정의해야함
// '다른 생성자가 존재하지 않을 때' 전체 생성자를 자동으로 만들어주므로


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_TB")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_USERNAME", nullable = false)
    private String username;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_GRADE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGrade grade;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "USER_NICKNAME", nullable = false)
    private String nickname;

    @Column(name = "USER_EMAIL", nullable = false)
    private String email;

    @Column(name = "USER_SOCIAL")
    private String social;

    @Column(name = "USER_UUID")
    private String uuid;

    // 비밀번호 변경을 위한 uuid -> 비밀번호 변경 요청 시에 발급
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void changePassword(String password) {
        this.password = password;
    }


}
