package com.zancheema.pantry.user;

import com.zancheema.pantry.user.dto.UserProfile;

import java.security.Principal;

public interface UserService {
    UserProfile getProfile(Principal principal);
}
