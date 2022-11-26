package com.zancheema.android.pantry.ui.login;

import static com.zancheema.android.pantry.common.StringUtils.anyBlank;
import static com.zancheema.android.pantry.common.ViewUtils.showSnackbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zancheema.android.pantry.R;
import com.zancheema.android.pantry.ui.login.dto.AuthToken;
import com.zancheema.android.pantry.ui.signup.SignUpActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvSignup;

    private LoginViewController loginViewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        tvSignup = findViewById(R.id.textViewSignup);

        loginViewController = new LoginViewController(this);

        btnLogin.setOnClickListener(this::onLogin);
        tvSignup.setOnClickListener(this::onSignup);
    }

    private void onSignup(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void onLogin(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (anyBlank(username, password)) {
            showSnackbar(view, "No field should be empty");
        } else {
            loginViewController.login(username, password, getLoginCallback(view));
        }
    }

    private Callback<AuthToken> getLoginCallback(View view) {
        return new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                // temp
                showSnackbar(view, "token: " + response.body().getAccessToken());

                // refresh conditional navigation
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                showSnackbar(view, "Failed to Login");
            }
        };
    }
}