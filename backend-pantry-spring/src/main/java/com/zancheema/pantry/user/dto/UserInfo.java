package com.zancheema.pantry.user.dto;

public record UserInfo(
        String username,
        String password,
        boolean enabled
) {
}
