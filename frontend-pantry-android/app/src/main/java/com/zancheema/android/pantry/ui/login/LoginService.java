package com.zancheema.android.pantry.ui.login;

import com.zancheema.android.pantry.ui.login.dto.AuthToken;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {
    @POST("auth/token")
    Call<AuthToken> getToken(@Header("Authorization") String authHeader);
}
