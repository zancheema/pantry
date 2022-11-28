package com.zancheema.android.pantry.remote

import com.zancheema.android.pantry.common.AuthTokenProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://192.168.43.149:8080"

class RetrofitClient(
    authTokenProvider: AuthTokenProvider
) {
    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${authTokenProvider.getAccessToken()}")
                .build()
            return@addInterceptor chain.proceed(newRequest)
        }
        .build()

    val instance by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
}