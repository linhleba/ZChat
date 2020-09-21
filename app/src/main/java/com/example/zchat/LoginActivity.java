package com.example.zchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout emailText;
    private TextInputLayout passwordText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set id for properties
        emailText = (TextInputLayout) findViewById(R.id.login_email_txt);
        passwordText = (TextInputLayout) findViewById(R.id.login_password_txt);
        loginButton = (Button) findViewById(R.id.main_logout_btn);

    }
}