package com.zancheema.android.pantry.ui.signup

import com.zancheema.android.pantry.ui.signup.dto.SignUpPayload
import com.zancheema.android.pantry.ui.signup.dto.SignUpResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("auth/signup")
    suspend fun signup(@Body payload: SignUpPayload): Response<SignUpResult>
}