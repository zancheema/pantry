package com.zancheema.android.pantry;

import static com.zancheema.android.pantry.common.StringUtils.anyBlank;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.zancheema.android.pantry.remote.AuthService;
import com.zancheema.android.pantry.remote.RetrofitClient;
import com.zancheema.android.pantry.remote.dto.SignUpPayload;
import com.zancheema.android.pantry.remote.dto.SignUpResponseBody;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnSignup;

    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        etConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        btnSignup = findViewById(R.id.buttonSignup);

        authService = RetrofitClient.getAuthService();

        btnSignup.setOnClickListener(this::onSignup);
    }

    private void onSignup(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String cpassword = etConfirmPassword.getText().toString();

        if (anyBlank(username, password, cpassword)) {
            showSnackbar(view, "No field should be empty");
        } else if (!password.equals(cpassword)) {
            showSnackbar(view, "Passwords don't match");
        } else {
            authService
                    .signup(new SignUpPayload(username, password))
                    .enqueue(new Callback<SignUpResponseBody>() {
                        @Override
                        public void onResponse(Call<SignUpResponseBody> call, Response<SignUpResponseBody> response) {
                            showSnackbar(view, "Sign up successful");
                            System.out.println("Retrofit success: " + response.body());
                        }

                        @Override
                        public void onFailure(Call<SignUpResponseBody> call, Throwable t) {
                            System.out.println("Retrofit failure: " + t.getLocalizedMessage());
                        }
                    });
        }
    }

    private void showSnackbar(View view, String s) {
        Snackbar
                .make(view, s, BaseTransientBottomBar.LENGTH_LONG)
                .show();
    }
}