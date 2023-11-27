package com.server.muchu.security.oauth.infoResponse;

import com.server.muchu.user.entity.OAuthProvider;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    String getName();
    OAuthProvider getOAuthProvider();
}
