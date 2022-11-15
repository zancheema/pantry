package com.zancheema.pantry.auth;

import com.zancheema.pantry.auth.dto.AuthToken;
import com.zancheema.pantry.auth.dto.SignupPayload;
import com.zancheema.pantry.user.dto.UserInfo;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AuthService {
    Optional<UserInfo> signup(SignupPayload payload);

    AuthToken generateToken(Authentication authentication);
}
