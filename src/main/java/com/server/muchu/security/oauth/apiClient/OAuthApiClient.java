package com.server.muchu.security.oauth.apiClient;

import com.server.muchu.security.oauth.infoResponse.OAuthInfoResponse;
import com.server.muchu.security.oauth.params.OAuthLoginParams;
import com.server.muchu.user.entity.OAuthProvider;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}
