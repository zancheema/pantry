package com.zancheema.android.pantry.ui.login

import com.zancheema.android.pantry.ui.login.dto.AuthToken
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {
    @POST("auth/token")
    suspend fun getToken(@Header("Authorization") authHeader: String?): Response<AuthToken>
}