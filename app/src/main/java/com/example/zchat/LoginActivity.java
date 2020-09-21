package com.example.zchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// import appcompat.widget.Toolbar to case, toolbar cant cast to android.widget.Toolbar

import androidx.appcompat.widget.Toolbar;


import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout emailText;
    private TextInputLayout passwordText;
    private Button loginButton;
    private Toolbar logToolbar;
    private ProgressDialog logDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // set id for toolbar
        logToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(logToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Set id for properties
        emailText = (TextInputLayout) findViewById(R.id.login_email_txt);
        passwordText = (TextInputLayout) findViewById(R.id.login_password_txt);
        loginButton = (Button) findViewById(R.id.login_btn);

        // declare progress dialog
        logDialog = new ProgressDialog(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getEditText().getText().toString();
                String password = passwordText.getEditText().getText().toString();

                // Check condition about email and password
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                {
                    login(email,password);
                }
            }

        });

    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            /* Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user); */

                            // If login successful, redirect to main activity
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Log in failed, please check your email and password",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


}