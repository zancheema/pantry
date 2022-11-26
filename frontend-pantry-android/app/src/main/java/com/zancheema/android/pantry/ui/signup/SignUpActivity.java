package com.zancheema.android.pantry.ui.signup;

import static com.zancheema.android.pantry.common.StringUtils.anyBlank;
import static com.zancheema.android.pantry.common.ViewUtils.showSnackbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zancheema.android.pantry.R;
import com.zancheema.android.pantry.ui.signup.dto.SignUpResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnSignup;
    private TextView tvLogin;

    private SignUpViewController signUpViewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        etConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        btnSignup = findViewById(R.id.buttonSignup);
        tvLogin = findViewById(R.id.textViewLogin);

        signUpViewController = new SignUpViewController();

        btnSignup.setOnClickListener(this::onSignup);
        tvLogin.setOnClickListener(this::onLogin);
    }

    private void onLogin(View view) {
        finish();
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
            signUpViewController.signup(username, password, getSignupCallback(view));
        }
    }

    private Callback<SignUpResponseBody> getSignupCallback(View view) {
        return new Callback<SignUpResponseBody>() {
            @Override
            public void onResponse(Call<SignUpResponseBody> call, Response<SignUpResponseBody> response) {
                showSnackbar(view, "Sign up successful");
                System.out.println("Retrofit success: " + response.body());
            }

            @Override
            public void onFailure(Call<SignUpResponseBody> call, Throwable t) {
                System.out.println("Retrofit failure: " + t.getLocalizedMessage());
            }
        };
    }
}