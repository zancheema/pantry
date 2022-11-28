package com.zancheema.android.pantry.container

import android.app.Application
import com.zancheema.android.pantry.R
import com.zancheema.android.pantry.common.SharedPreferencesAuthTokenProvider
import com.zancheema.android.pantry.remote.RetrofitClient

class AppContainer(
    application: Application
) {
    private val authTokenProvider = SharedPreferencesAuthTokenProvider(
        application, application.getString(R.string.preference_file_key)
    )

    val retrofit = RetrofitClient(authTokenProvider).instance

    val authenticationProvider = AuthenticationProvider(authTokenProvider)
}