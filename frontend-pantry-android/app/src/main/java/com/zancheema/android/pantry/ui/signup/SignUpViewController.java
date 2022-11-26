package com.zancheema.android.pantry.ui.signup;

import com.zancheema.android.pantry.remote.RetrofitClient;
import com.zancheema.android.pantry.ui.signup.dto.SignUpPayload;
import com.zancheema.android.pantry.ui.signup.dto.SignUpResponseBody;

import retrofit2.Callback;

public class SignUpViewController {
    private final SignUpService signUpService;

    public SignUpViewController() {
        this.signUpService = RetrofitClient.getInstance().create(SignUpService.class);
    }

    public void signup(String username, String password, Callback<SignUpResponseBody> callback) {
        signUpService
                .signup(new SignUpPayload(username, password))
                .enqueue(callback);
    }
}
