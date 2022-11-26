package com.zancheema.android.pantry.remote;

import com.zancheema.android.pantry.remote.dto.SignUpPayload;
import com.zancheema.android.pantry.remote.dto.SignUpResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/signup")
    Call<SignUpResponseBody> signup(@Body SignUpPayload payload);
}
