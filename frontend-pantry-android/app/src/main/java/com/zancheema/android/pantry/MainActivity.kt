package com.zancheema.android.pantry

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zancheema.android.pantry.container.AuthenticationProvider.AuthenticationState.AUTHENTICATED
import com.zancheema.android.pantry.container.AuthenticationProvider.AuthenticationState.UNAUTHENTICATED
import com.zancheema.android.pantry.ui.home.HomeActivity
import com.zancheema.android.pantry.ui.login.LoginActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val authState =
            (application as PantryApplication).appContainer.authenticationProvider.authState
        GlobalScope.launch {
            authState.collect { state ->
                if (state is UNAUTHENTICATED) {
                    startLoginActivity()
                } else if (state is AUTHENTICATED) {
                    startHomeActivity()
                }
            }
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent)
    }

    private fun startHomeActivity() {
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent)
    }
}