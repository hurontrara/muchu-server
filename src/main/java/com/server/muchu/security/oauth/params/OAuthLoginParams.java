package com.server.muchu.security.oauth.params;

import com.server.muchu.user.entity.OAuthProvider;
import org.springframework.util.MultiValueMap;

public interface OAuthLoginParams {
    OAuthProvider oAuthProvider();
    MultiValueMap<String, String> makeBody();

}