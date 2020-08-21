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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestActivity extends AppCompatActivity {

    private DatabaseReference CCSMainDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_v1);

        CCSMainDB = FirebaseDatabase.getInstance().getReference();
//
//        Button button = findViewById(R.id.manageLocationsB);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {SendUserToAddCreateLocation();
//
//
//            }
//        });

    }

    private void SendUserToAddCreateLocation() {

        Intent addLocationIntent = new Intent(TestActivity.this, AddLocationActivity.class);
        addLocationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //ddLocationIntent.putExtra("UserCarryDayDate", onBindUserSelectedDayDateSend );
        startActivity(addLocationIntent);

    }

}
