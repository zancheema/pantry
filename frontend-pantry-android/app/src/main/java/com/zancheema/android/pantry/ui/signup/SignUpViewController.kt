package com.zancheema.android.pantry.ui.signup

import com.zancheema.android.pantry.remote.RetrofitClient
import com.zancheema.android.pantry.ui.signup.dto.SignUpPayload
import com.zancheema.android.pantry.ui.signup.dto.SignUpResult

class SignUpViewController {
    private val signUpService = RetrofitClient.instance.create(SignUpService::class.java)

    suspend fun signup(username: String, password: String): SignUpResult? {
        val payload = SignUpPayload(username, password)
        val result = signUpService.signup(payload)
        return result.body()
    }
}
