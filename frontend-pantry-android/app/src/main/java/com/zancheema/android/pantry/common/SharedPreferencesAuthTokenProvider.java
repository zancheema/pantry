package com.zancheema.android.pantry.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesAuthTokenProvider implements AuthTokenProvider {
    private static final String KEY_ACCESS_TOKEN = "access-token";

    private final SharedPreferences sharedPreferences;

    public SharedPreferencesAuthTokenProvider(Context context, String filename) {
        sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
    }

    @Override
    public void saveAccessToken(String accessToken) {
        sharedPreferences
                .edit()
                .putString(KEY_ACCESS_TOKEN, accessToken)
                .apply();
    }

    @Override
    public String getAccessToken() {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, "");
    }
}
