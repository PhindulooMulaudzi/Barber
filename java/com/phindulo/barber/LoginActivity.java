package com.phindulo.barber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity implements View.OnClickListener {
    private Button login;
    private Button createAccount;
    private Button guest;

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

    private void loginExistingAccount() {

    }

    private void loginGuestAccount() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_login_button:
                loginExistingAccount();
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
