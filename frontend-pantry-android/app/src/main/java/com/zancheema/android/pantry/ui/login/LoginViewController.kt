package com.zancheema.android.pantry.ui.login

import com.zancheema.android.pantry.common.EncoderUtils.getBasicAuthHeader
import com.zancheema.android.pantry.container.AuthenticationProvider
import com.zancheema.android.pantry.ui.login.dto.AuthToken
import retrofit2.Retrofit

class LoginViewController(
    retrofit: Retrofit,
    private val authenticationProvider: AuthenticationProvider
) {
    private val loginService = retrofit.create(LoginService::class.java)

    suspend fun login(username: String, password: String): AuthToken? {
        val authHeader = getBasicAuthHeader(username, password)
        val response = loginService.getToken(authHeader)
        val token = response.body();
        token?.apply {
//            authTokenProvider.saveAccessToken(token.accessToken)
            authenticationProvider.onLogin(token)
        }
        return response.body()
    }
}