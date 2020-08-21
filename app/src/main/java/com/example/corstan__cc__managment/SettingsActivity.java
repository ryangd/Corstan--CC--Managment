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

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity implements android.view.View.OnClickListener{
    Button signOutB, permissionsB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Assigning Variables
        signOutB = findViewById(R.id.signOutB);
        permissionsB = findViewById(R.id.permissionsB);

        //Assisnging the button clicks
        signOutB.setOnClickListener(this);
        permissionsB.setOnClickListener(this);
    }

    @Override
    public void onClick (View view) {
        switch (view.getId()){
            case R.id.signOutB:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.permissionsB:
                break;

        }

    }}