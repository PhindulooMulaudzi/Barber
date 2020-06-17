package com.phindulo.barber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private Button createAccount;
    private Button guest;
    private final int AUTHUI_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_login_button);
        createAccount = findViewById(R.id.login_create_acct_button);
        guest = findViewById(R.id.login_guest_button);

        login.setOnClickListener(this);
        createAccount.setOnClickListener(this);
        guest.setOnClickListener(this);
    }

    private void loginExistingAccount(View view) {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build()
        );

        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setAlwaysShowSignInMethodScreen(true)
                .setIsSmartLockEnabled(false)
                .build();

        startActivityForResult(intent, AUTHUI_REQUEST_CODE);

       // startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
    }

    private void loginGuestAccount() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTHUI_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // We have signed in the user or we have a new user
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d("Sign in", "onActivityResult: " + user.toString());
                //Checking for User (New/Old)
                if (user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()) {
                    //This is a New User
                } else {
                    //This is a returning user
                }

                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                finish();

            } else {
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_login_button:
                loginExistingAccount(view);
                break;

            case R.id.login_create_acct_button:
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
                break;

            case R.id.login_guest_button:
                loginGuestAccount();
                break;
        }
    }
}
