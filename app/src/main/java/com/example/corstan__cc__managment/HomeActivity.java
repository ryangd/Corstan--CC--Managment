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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity implements android.view.View.OnClickListener{

    Button createShowB, availabilityB, paperworkB, scheduleB, settingsB;

    FirebaseAuth mAuth;

    DatabaseReference CCSMainDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CCSMainDB = FirebaseDatabase.getInstance().getReference();

        //Assigning Variables
//        createShowB = findViewById(R.id.createShowB);
//        availabilityB = (findViewById(R.id.availabilityB));
//        paperworkB = (findViewById(R.id.paperworkB));
//        scheduleB = (findViewById(R.id.scheduleB));
//        settingsB = (findViewById(R.id.settingsB));
//
//
//        //Assisnging the button clicks
//        createShowB.setOnClickListener(this);
//        //availabilityB.setOnClickListener(this);
//        paperworkB.setOnClickListener(this);
//        scheduleB.setOnClickListener(this);
//        settingsB.setOnClickListener(this);
    }

    @Override
    public void onClick (View view) {
        switch (view.getId()){
            case R.id.createShowB:
                 startActivity(new Intent(this, CreateShowActivity.class));
                break;
//
//            case R.id.availabilityB:
//                startActivity(new Intent(this, AvailabilityActivity.class));
//                break;
//            case R.id.paperworkB:
//                startActivity(new Intent (this, PaperworkActivity.class));
//                break;
//            case R.id.scheduleB:
//                startActivity(new Intent(this, ScheduleActivity.class));
//               break;
//            case R.id.settingsB:
//                startActivity(new Intent(this, SettingsActivity.class));
//                break;
        }

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null) {
            SendUserToSignInActivity();
        }
        else {
            CheckUserExistence();
        }
    }

    private void SendUserToSignInActivity()
    {
        Intent loginIntent = new Intent(HomeActivity.this, SignInActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();


    }
    private void CheckUserExistence() {
        final String current_user_id = mAuth.getCurrentUser().getUid();

        CCSMainDB.child("Users")
                .child("Management")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (!dataSnapshot.hasChild(current_user_id))
                        {
                            SendUserToSignInActivity();
                            finish();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
//    public static class DayViewHolderHolder extends RecyclerView.ViewHolder {
//
//        TextView dayDate, dayDay, activeShow;
//
//        Button editDayB;
//
//        public DayViewHolderHolder(@NonNull View itemView) {
//
//            super(itemView);
//
//            dayDate = itemView.findViewById(R.id.dayCardDayDate);
//            dayDay = itemView.findViewById(R.id.dayCardDayDay);
//
//            editDayB = itemView.findViewById(R.id.editDayB);
//            activeShow = itemView.findViewById(R.id.dayCardActiveShow);
//
//        }
//    }

}
