package com.zancheema.android.pantry.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.43.149:8080";
    private static Retrofit retrofit;
    private static AuthService authService;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static AuthService getAuthService() {
        if (authService == null) {
            authService = getInstance().create(AuthService.class);
        }
        return authService;
    }
}
