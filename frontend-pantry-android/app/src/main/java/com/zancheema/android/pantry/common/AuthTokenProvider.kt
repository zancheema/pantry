package com.zancheema.android.pantry.common

interface AuthTokenProvider {
    fun saveAccessToken(accessToken: String)

    fun getAccessToken(): String
}