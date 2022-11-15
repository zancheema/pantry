package com.zancheema.pantry.auth;

import com.zancheema.pantry.auth.dto.AuthToken;
import com.zancheema.pantry.auth.dto.SignupPayload;
import com.zancheema.pantry.security.TokenService;
import com.zancheema.pantry.user.User;
import com.zancheema.pantry.user.UserMapper;
import com.zancheema.pantry.user.UserRepository;
import com.zancheema.pantry.user.dto.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserInfo> signup(SignupPayload payload) {
        if (userRepository.existsById(payload.getUsername())) {
            return Optional.empty();
        }

        User user = userMapper.toUser(payload);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        return Optional.of(
                userMapper.toUserInfo(savedUser)
        );
    }

    @Override
    public AuthToken generateToken(Authentication authentication) {
        String accessToken = tokenService.generateToken(authentication);
        return new AuthToken(accessToken);
    }
}
