package com.zancheema.android.pantry.ui.login;

import static com.zancheema.android.pantry.common.EncoderUtils.getBasicAuthHeader;

import android.annotation.SuppressLint;
import android.content.Context;

import com.zancheema.android.pantry.R;
import com.zancheema.android.pantry.common.AuthTokenProvider;
import com.zancheema.android.pantry.common.SharedPreferencesAuthTokenProvider;
import com.zancheema.android.pantry.remote.RetrofitClient;
import com.zancheema.android.pantry.ui.login.dto.AuthToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewController {
    private final LoginService loginService;
    private final AuthTokenProvider authTokenProvider;

    public LoginViewController(Context context) {
        this.loginService = RetrofitClient.getInstance().create(LoginService.class);

        @SuppressLint("ResourceType")
        String filename = context.getString(R.string.preference_file_key);
        authTokenProvider = new SharedPreferencesAuthTokenProvider(context, filename);
    }

    public void login(String username, String password, Callback<AuthToken> callback) {
        String authHeader = getBasicAuthHeader(username, password);

        loginService.getToken(authHeader)
                .enqueue(new Callback<AuthToken>() {
                    @Override
                    public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                        AuthToken token = response.body();
                        authTokenProvider.saveAccessToken(token.getAccessToken());

                        callback.onResponse(call, response);
                    }

                    @Override
                    public void onFailure(Call<AuthToken> call, Throwable t) {
                        callback.onFailure(call, t);
                    }
                });
    }
}
