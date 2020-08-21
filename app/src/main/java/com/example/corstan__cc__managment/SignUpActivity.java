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
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity  {

    private EditText UserEmail, UserPassword, UserConfirmPassword;
    private Button CreateAccountButton;
    private ProgressBar progressBar;

    private FirebaseDatabase databaseUsers;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //set variables
        progressBar = new ProgressBar(this);

        UserEmail = findViewById(R.id.register_email);
        UserPassword = findViewById(R.id.register_password);
        UserConfirmPassword = findViewById(R.id.register_confirm_password);

        mAuth = FirebaseAuth.getInstance();
        //FirebaseUser user = mAuth.getCurrentUser();

        CreateAccountButton = findViewById(R.id.register_create_account);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateNewAccount();

            }
        });



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
    private void SendUserToMainActivity() {
        Intent signUpProfileIntent = new Intent(SignUpActivity.this, MainActivity.class);
        signUpProfileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signUpProfileIntent);
        finish();
    }

    private void CreateNewAccount()
    {
        String email = UserEmail.getText().toString().trim();
        String password = UserPassword.getText().toString().trim();
        String confirmPassword = UserConfirmPassword.getText().toString().trim();

        if (email.isEmpty()) {
            UserEmail.setError("Email is required");
            UserEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            UserPassword.setError("Minimum length of password should be 8 characters");
            UserPassword.requestFocus();
            return;
        }
        if (!password.equals(confirmPassword)) {
            UserConfirmPassword.setError("The passwords do not match");
            UserConfirmPassword.requestFocus();
        }

        else  {

//            progressBar.setTag("Creating new account");
//            progressBar.show()
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "You authentication has been successful. Upper level access granted", Toast.LENGTH_SHORT).show();
                                SendUserToSignUpProfileActivity();

                            }
                            else {
                                String message = task.getException().getMessage();
                                Toast.makeText(SignUpActivity.this, "Critical Error: " + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void SendUserToSignUpProfileActivity() {
        Intent signUpProfileIntent = new Intent(SignUpActivity.this, MainActivity.class);
        signUpProfileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signUpProfileIntent);
        finish();
    }


    //Alerts about email and password
//    public void registerUser() {
//
//
//        String email = editTextEmail.getText().toString().trim();
//        String password = editTextPassword.getText().toString().trim();
//
//
//        if (email.isEmpty()) {
//            editTextEmail.setError("Email is required");
//            editTextEmail.requestFocus();
//            return;
//        }
//        if (password.isEmpty()) {
//            editTextPassword.setError("Minuim length of password should be 8 charturs");
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        //Goes to home
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            finish();
//                            Intent intent = new Intent(SignUpActivity.this, SignUpProfileActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                        }
//                    }
//                });
//
//    }
//
//    //Create Account button action
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.createB:
//                registerUser();
//                break;
//        }
//    }
//


}
