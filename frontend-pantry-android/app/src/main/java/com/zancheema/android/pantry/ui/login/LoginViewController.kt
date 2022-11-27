package com.zancheema.android.pantry.ui.login

import android.content.Context
import com.zancheema.android.pantry.R
import com.zancheema.android.pantry.common.EncoderUtils.getBasicAuthHeader
import com.zancheema.android.pantry.common.SharedPreferencesAuthTokenProvider
import com.zancheema.android.pantry.remote.RetrofitClient
import com.zancheema.android.pantry.ui.login.dto.AuthToken

class LoginViewController(context: Context) {
    private val loginService = RetrofitClient.instance.create(LoginService::class.java)
    private val authTokenProvider = SharedPreferencesAuthTokenProvider(
        context, context.getString(R.string.preference_file_key)
    )

    suspend fun login(username: String, password: String): AuthToken? {
        val authHeader = getBasicAuthHeader(username, password)
        val response = loginService.getToken(authHeader)
        val token = response.body();
        token?.apply {
            authTokenProvider.saveAccessToken(token.accessToken)
        }
        return response.body()
    }
}