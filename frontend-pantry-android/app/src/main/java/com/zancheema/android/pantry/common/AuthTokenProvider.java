package com.zancheema.android.pantry.common;

public interface AuthTokenProvider {
    void saveAccessToken(String accessToken);

    String getAccessToken();
}
