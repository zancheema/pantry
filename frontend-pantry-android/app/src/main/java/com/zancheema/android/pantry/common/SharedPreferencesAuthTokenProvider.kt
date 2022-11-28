package com.zancheema.android.pantry.common

import android.content.Context

private const val KEY_ACCESS_TOKEN = "access-token"

class SharedPreferencesAuthTokenProvider(context: Context, filename: String) : AuthTokenProvider {

    private val sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE)

    override fun saveAccessToken(accessToken: String) {
        sharedPreferences
            .edit()
            .putString(KEY_ACCESS_TOKEN, accessToken)
            .apply()
    }

    override fun getAccessToken(): String {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, "") ?: ""
    }

    override fun deleteAccessToken() {
        sharedPreferences.edit()
            .remove(KEY_ACCESS_TOKEN)
            .apply()
    }
}