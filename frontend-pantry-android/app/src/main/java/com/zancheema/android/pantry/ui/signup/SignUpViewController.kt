package com.zancheema.android.pantry.ui.signup

import com.zancheema.android.pantry.ui.signup.dto.SignUpPayload
import com.zancheema.android.pantry.ui.signup.dto.SignUpResult
import retrofit2.Retrofit

class SignUpViewController(
    retrofit: Retrofit
) {
    private val signUpService = retrofit.create(SignUpService::class.java)

    suspend fun signup(username: String, password: String): SignUpResult? {
        val payload = SignUpPayload(username, password)
        val result = signUpService.signup(payload)
        return result.body()
    }
}
