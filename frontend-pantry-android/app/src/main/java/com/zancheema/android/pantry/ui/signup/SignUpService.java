package com.zancheema.android.pantry.ui.signup;

import com.zancheema.android.pantry.ui.signup.dto.SignUpPayload;
import com.zancheema.android.pantry.ui.signup.dto.SignUpResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpService {
    @POST("auth/signup")
    Call<SignUpResponseBody> signup(@Body SignUpPayload payload);
}
