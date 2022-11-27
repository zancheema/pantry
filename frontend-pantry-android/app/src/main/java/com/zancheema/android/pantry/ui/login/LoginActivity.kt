package com.zancheema.android.pantry.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zancheema.android.pantry.R
import com.zancheema.android.pantry.common.anyBlank
import com.zancheema.android.pantry.common.showSnackbar
import com.zancheema.android.pantry.ui.signup.SignUpActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignup: TextView

    private lateinit var loginViewController: LoginViewController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.editTextUsername)
        etPassword = findViewById(R.id.editTextPassword)
        btnLogin = findViewById(R.id.buttonLogin)
        tvSignup = findViewById(R.id.textViewSignup)

        loginViewController = LoginViewController(this)

        btnLogin.setOnClickListener(this::onLogin);
        tvSignup.setOnClickListener(this::onSignup);
    }

    private fun onLogin(view: View) {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        if (anyBlank(username, password)) {
            view.showSnackbar("No field should be empty")
        } else {
            GlobalScope.launch {
                try {
                    val response = loginViewController.login(username, password)
                    view.showSnackbar("token: ${response?.accessToken}")
                } catch (e: Exception) {
                    view.showSnackbar("Failed to login")
                }
            }
        }
    }

    private fun onSignup(view: View) {
        val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(intent)
    }
}