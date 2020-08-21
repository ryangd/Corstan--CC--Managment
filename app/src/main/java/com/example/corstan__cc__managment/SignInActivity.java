/*
 * Property of:				GreenDot Management Systems Inc.
 * 		                                               A Division Of
 *                                                 Corstan Systems Inc.
 *
 * Written By: Ryan Stander
 * ryanstander@gmail.com
 * June 17, 2020
 *
 * Copyright (c)     TradeMark Reserved (tm)
 *
 */

package com.example.corstan__cc__managment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInActivity extends AppCompatActivity  {


    //AppCompat
    private String uid;
    private EditText userEmail, userPassword;
    private Button loginButton;
    private TextView signupHere;


    private FirebaseAuth mAuth ;


    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//Set Variables
//        findViewById(R.id.signUpB).setOnClickListener(this);
//        findViewById(R.id.loginB).setOnClickListener(this);
//
        userEmail = findViewById(R.id.login_email);
        userPassword = findViewById(R.id.login_password);
        signupHere = findViewById(R.id.register_account_link);
        loginButton = findViewById(R.id.login_button);

        signupHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToSignUpActivity();
            }


        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowUserToLogIn();
            }
        });
//
//
//
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            SendUserToMainActivity();
        }
        else {
        }
    }

    private void AllowUserToLogIn() {
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if (email.isEmpty()) {
            userEmail.setError("Email is required");
            userEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            userPassword.setError("Minimum length of password should be 8 characters");
            userPassword.requestFocus();
            return;
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SignInActivity.this, "You authentication has been successful. Upper level access granted...", Toast.LENGTH_SHORT).show();
                                SendUserToMainActivity();

                            }
                            else {
                                String message = task.getException().getMessage();
                                Toast.makeText(SignInActivity.this, "Critical Error: " + message, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }

    private void SendUserToSignUpActivity()
    {
        Intent signUpIntent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(signUpIntent);
        finish();
    }


//    public void userLogin() {
////Alert for
//        String email = editTextEmail.getText().toString().trim();
//        String password = editTextPassword.getText().toString().trim();
//
//        if (email.isEmpty()) {
//            editTextEmail.setError("Email is required");
//            editTextEmail.requestFocus();
//            return; }
//
//        if (password.isEmpty()) {
//            editTextPassword.setError("Minimum length of password should be 8 charturs");
//            editTextPassword.requestFocus();
//            return; }
//
//
//
////Starts Home Activity if successful log in
//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()) {
//                    finish();
//                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//
//    }


    private void sendUserToLoginActivity()
    {
        Intent loginIntent = new Intent( );
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();

    }

    private void SendUserToMainActivity() {
        Intent signUpProfileIntent = new Intent(SignInActivity.this, MainActivity.class);
        signUpProfileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signUpProfileIntent);
        finish();
    }
//
//
//
//    //Button Void (Sign up/Login/)
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.signUpB:
//                finish();
//                startActivity(new Intent(this, SignUpActivity.class));
//                break;
//
//            case R.id.loginB:
//                userLogin();
//                break;
//        }
//
//    }
}
