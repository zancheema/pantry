package com.zancheema.android.pantry.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.zancheema.android.pantry.PantryApplication
import com.zancheema.android.pantry.R
import com.zancheema.android.pantry.container.AuthenticationProvider

class HomeActivity : AppCompatActivity() {
    private lateinit var btnLogout: Button
    private lateinit var authenticationProvider: AuthenticationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnLogout = findViewById(R.id.buttonLogout)
        btnLogout.setOnClickListener(this::onLogout)

        authenticationProvider =
            (application as PantryApplication).appContainer.authenticationProvider
    }

    private fun onLogout(view: View) {
        authenticationProvider.onLogout()
    }
}