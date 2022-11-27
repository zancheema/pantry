package com.zancheema.android.pantry.ui.signup

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zancheema.android.pantry.PantryApplication
import com.zancheema.android.pantry.R
import com.zancheema.android.pantry.common.anyBlank
import com.zancheema.android.pantry.common.showSnackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnSignup: Button
    private lateinit var tvLogin: TextView

    private lateinit var signUpViewController: SignUpViewController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etUsername = findViewById(R.id.editTextUsername)
        etPassword = findViewById(R.id.editTextPassword)
        etConfirmPassword = findViewById(R.id.editTextConfirmPassword)
        btnSignup = findViewById(R.id.buttonSignup)
        tvLogin = findViewById(R.id.textViewLogin)

        val container = (application as PantryApplication).appContainer
        signUpViewController = SignUpViewController(container.retrofit)

        btnSignup.setOnClickListener(this::onSignup);
        tvLogin.setOnClickListener(this::onLogin);
    }

    private fun onSignup(view: View) {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        val cpassword = etConfirmPassword.text.toString()

        if (anyBlank(username, password, cpassword)) {
            view.showSnackbar("No field should be empty")
        } else if (!password.equals(cpassword)) {
            view.showSnackbar("Passwords don't match")
        } else {
            GlobalScope.launch {
                try {
                    signUpViewController.signup(username, password)
                    view.showSnackbar("Signup successful!")
                } catch (e: Exception) {
                    view.showSnackbar("Signup failed")
                }
            }
        }
    }

    private fun onLogin(view: View) {
        finish()
    }
}