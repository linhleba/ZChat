package com.example.zchat;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

// import appcompat.widget.Toolbar to case, toolbar cant cast to android.widget.Toolbar
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
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

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout mDisplayName;
    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private Button mCreateBtn;
    private Toolbar mToolbar;

    // Create dialog when a user submit for registering
    private ProgressDialog mRegisterDialog;

    // Firebase Authentication
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // declare progress Dialog
        mRegisterDialog = new ProgressDialog(this);


        // Set toolbar with tittle
        mToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        // Create return action to main home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //mToolbar.setTitle("Create Account");
        getSupportActionBar().setTitle("Create Account");

        mAuth = FirebaseAuth.getInstance();
        // Declare display, email and password
        mDisplayName = (TextInputLayout) findViewById(R.id.res_name_text);
        mEmail = (TextInputLayout) findViewById(R.id.res_email_text);
        mPassword = (TextInputLayout) findViewById(R.id.res_password_text);
        mCreateBtn = (Button) findViewById(R.id.res_btn);

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String display_name = mDisplayName.getEditText().getText().toString();
                String email = mEmail.getEditText().getText().toString();
                String password = mPassword.getEditText().getText().toString();

                // Check if field is null or not
                if (TextUtils.isEmpty(display_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                {

                }
                else {

                    mRegisterDialog.setTitle("Create account successfully!");
                    mRegisterDialog.setMessage("Loading, please wait!");

                    // User can't touch outside when dialog is loading.
                    mRegisterDialog.setCanceledOnTouchOutside(false);
                    mRegisterDialog.show();



                }

                register_user(display_name, email, password);
            }
        });




    }

    private void register_user(String display_name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mRegisterDialog.dismiss();
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            mRegisterDialog.hide();
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Create failed, please check again!",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}