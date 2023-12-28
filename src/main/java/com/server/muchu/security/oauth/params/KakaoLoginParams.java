package com.server.muchu.security.oauth.params;

import com.server.muchu.user.entity.OAuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@Setter  // 컨트롤러 계층에서의 객체 바인딩을 위함
@NoArgsConstructor
public class KakaoLoginParams implements OAuthLoginParams {

    private String code;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.KAKAO;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        return body;
    }
}

