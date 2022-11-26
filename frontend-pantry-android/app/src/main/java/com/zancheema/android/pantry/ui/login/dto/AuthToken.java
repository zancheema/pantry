package com.zancheema.android.pantry.ui.login.dto;

public class AuthToken {
    private String accessToken;

    public AuthToken() {
    }

    public AuthToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
