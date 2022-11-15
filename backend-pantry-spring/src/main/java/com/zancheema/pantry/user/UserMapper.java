package com.zancheema.pantry.user;

import com.zancheema.pantry.auth.dto.SignupPayload;
import com.zancheema.pantry.user.dto.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(SignupPayload payload) {
        return new User(payload.getUsername(), payload.getPassword(), true, true, true, true);
    }

    public UserInfo toUserInfo(User user) {
        return new UserInfo(user.getUsername(), user.getPassword(), user.isEnabled());
    }
}
