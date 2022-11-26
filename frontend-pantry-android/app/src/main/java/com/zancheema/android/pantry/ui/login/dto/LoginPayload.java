package com.zancheema.android.pantry.ui.login.dto;

public class LoginPayload {
    private String username;
    private String password;

    public LoginPayload() {
    }

    public LoginPayload(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
