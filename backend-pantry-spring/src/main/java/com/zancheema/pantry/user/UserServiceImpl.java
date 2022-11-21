package com.zancheema.pantry.user;

import com.zancheema.pantry.user.dto.UserProfile;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserProfile getProfile(Principal principal) {
        User user = userRepository.findById(principal.getName()).get();
        return userMapper.toUserProfile(user);
    }
}
