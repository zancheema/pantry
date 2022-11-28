package com.zancheema.android.pantry.container

import com.zancheema.android.pantry.common.AuthTokenProvider
import com.zancheema.android.pantry.ui.login.dto.AuthToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AuthenticationProvider(private val authTokenProvider: AuthTokenProvider) {

    private fun getInitialAuthenticationState(): AuthenticationState {
        return if (authTokenProvider.getAccessToken().isBlank())
            AuthenticationState.UNAUTHENTICATED
        else
            AuthenticationState.AUTHENTICATED(authTokenProvider.getAccessToken())
    }

    private val _authState: MutableStateFlow<AuthenticationState> = MutableStateFlow(
        getInitialAuthenticationState()
    )
    val authState: Flow<AuthenticationState> = _authState

    fun onLogin(token: AuthToken) {
        authTokenProvider.saveAccessToken(token.accessToken)
        _authState.value = AuthenticationState.AUTHENTICATED(token.accessToken)
    }

    fun onLogout() {
        authTokenProvider.deleteAccessToken()
        _authState.value = AuthenticationState.UNAUTHENTICATED
    }

    sealed class AuthenticationState(val authenticated: Boolean) {
        class AUTHENTICATED(var token: String) : AuthenticationState(true)
        object UNAUTHENTICATED : AuthenticationState(false)
    }
}