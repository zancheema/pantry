package com.zancheema.pantry.auth;

import com.zancheema.pantry.auth.dto.AuthToken;
import com.zancheema.pantry.auth.dto.SignupPayload;
import com.zancheema.pantry.user.dto.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserInfo> signup(@RequestBody @Valid SignupPayload payload) throws URISyntaxException {
        Optional<UserInfo> optionalUser = authService.signup(payload);
        if (optionalUser.isEmpty()) return ResponseEntity.badRequest().build();

        URI location = new URI("/api/users/profile");
        return ResponseEntity.created(location)
                .body(optionalUser.get());
    }

    @PostMapping("/token")
    public ResponseEntity<AuthToken> token(Authentication authentication) {
        AuthToken token = authService.generateToken(authentication);
        return ResponseEntity.ok(token);
    }
}
