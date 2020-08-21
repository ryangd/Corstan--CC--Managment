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
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corstan__cc__managment.Model.GDUserTool;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ManageSelectDayActivity extends AppCompatActivity implements View.OnClickListener{

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar mToolbar;

    private CircleImageView NavProfileImage;
    private TextView NavProfileFullName, NavActiveShow, NavUserPosition;

    String activeShow, UserCarryDayDate, UserCarryLocationName, UserCarryActiveShow, UserCarryDepartment, currentUserId, onBindUserSelectedDayDate;

    private EditText editTextCreateDayDate;

    public RecyclerView dayList;

     TextView showTextView, datDateValidationTextView, textViewShowName, textViewShowNameCurrentDate, textViewSelectedDateWeekDay, textViewSelectedDate;

    private FirebaseAuth mAuth;
    DatabaseReference CCSMainDB;

    DatabaseReference dayRef, locationRef, currentUserDepartmentDbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_days);

        GDUserTool gdUserTool = new GDUserTool();

        CCSMainDB = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        textViewSelectedDateWeekDay = findViewById(R.id.textViewSelectedDateWeekDay);



        if ((getIntent().getExtras()).getString("UserCarryActiveShow") != null) {
            UserCarryActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");

        }  else {
            //UserCarryActiveShow = textViewShowName.getText().toString().trim();
        }
        if (getIntent().getExtras().getString("UserCarryDayDate") != null) {
            onBindUserSelectedDayDate = getIntent().getExtras().getString("UserCarryDayDate");
        }
        if (getIntent().getExtras().getString("UserCarryDepartment") != null) {
            UserCarryDepartment = getIntent().getExtras().getString("UserCarryDepartment");
        }

        textViewSelectedDate = findViewById(R.id.textViewSelectedDate);

        textViewShowName = findViewById(R.id.textViewShowName);

        editTextCreateDayDate = findViewById(R.id.editTextCreateDayDate);
        editTextCreateDayDate.setText(onBindUserSelectedDayDate.trim());


        dayList = findViewById(R.id.dayList);
        dayList.setHasFixedSize(true);
        dayList.setLayoutManager(new LinearLayoutManager(this));

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Edit Day");


        drawerLayout = findViewById(R.id.drawable_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_closed);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView =  findViewById(R.id.navigation_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        NavProfileImage = navView.findViewById(R.id.nav_circleProfilePhoto);
        NavProfileFullName = navView.findViewById(R.id.nav_user_full_name);
        NavActiveShow = navView.findViewById(R.id.nav_user_active_show);
        NavUserPosition = navView.findViewById(R.id.nav_user_position);


        CCSMainDB.child("Users")
                .child("Management")
                .child(currentUserId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        GDUserTool gdUserTool = new GDUserTool();

                        if (dataSnapshot.exists()) {


//                            if (dataSnapshot.child("activeShow").equals(null)) {
//
//                            }


                            if (dataSnapshot.hasChild("firstName")) {
                                String fullName = dataSnapshot.child("firstName")
                                        .getValue()
                                        .toString()
                                        + " " + dataSnapshot
                                        .child("lastName")
                                        .getValue();
                                NavProfileFullName.setText(fullName);
                            }
                            if (dataSnapshot.hasChild("activeShow")) {
                                //activeShow = dataSnapshot.child("activeShow").getValue().toString();
                                gdUserTool.setActiveShow(dataSnapshot.child("activeShow").getValue().toString());
                                //String activeShowNavView = dataSnapshot.child("activeShow").getValue().toString();
                                //dataSnapshot.getValue(gdUserTool.getClass());

                                textViewShowName.setText(gdUserTool.getActiveShow());

                                //final String UserCarryActiveShow = activeShow;

                                //UserCarryActiveShow = UserCarryActiveShow;

                                //NavActiveShow.setText(activeShowNavView);
                                //gdUserTool.getActiveShow();


                            }
                            if (dataSnapshot.hasChild("userPrimaryPosition")) {
                                String navUserPosition = dataSnapshot.child("userPrimaryPosition").getValue().toString();
                                NavUserPosition.setText(dataSnapshot.child("userPrimaryDepartment").getValue().toString() + " - " + navUserPosition);

                            }
                            if (dataSnapshot.hasChild("Profile Photo")) {
                                String profileimage = dataSnapshot.child("Profile Photo")
                                        .getValue()
                                        .toString();
                                Picasso.get().load(profileimage).placeholder(R.drawable.profile).into(NavProfileImage);
                            }
//                            else {
//
//                                Toast.makeText(MainActivity.this, "Profile Name Doesnt Exist", Toast.LENGTH_SHORT).show();
//                            }
                            else {
//                                SendUserToCreateEditShow();
                            }

                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                UserMenuSelector(item);

                return false;
            }
        });
        UserCarryActiveShow.equals(textViewShowName.getText().toString().trim());
        textViewSelectedDate.setText(UserCarryActiveShow);







    }
//???????????????????????????????????????????????????????????????????????????????????????????????????????????
private void CheckUserExistence () {
    final String current_user_id = mAuth.getCurrentUser().getUid();

    CCSMainDB.child("Users")
            .child("Management")
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (!dataSnapshot.hasChild(current_user_id)) {
                        SendUserToSignUpProfileActivity();
                        finish();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
}
//??????????????????????????????????????????????????????????????????????????????????????????????????
//                     Send to Intents
//??????????????????????????????????????????????????????????????????????????????????????????????????

    private void SendUserToCreateDayActivity () {

        Intent createDayIntent = new Intent(ManageSelectDayActivity.this, CreateDayActivity.class);
        startActivity(createDayIntent);


    }
    //    @Override
//    public void onClick (View view) {
//        switch (view.getId()){
//            case R.id.profileB:
//                startActivity(new Intent(this, ProfileActivity.class));
//                break;
//
//            case R.id.availabilityB:
//                startActivity(new Intent(this, AvailabilityActivity.class));
//                break;
//            case R.id.paperworkB:
//                startActivity(new Intent (this, PaperworkActivity.class));
//                break;
//            case R.id.scheduleB:
//                startActivity(new Intent(this, ScheduleActivity.class));
//                break;
//            case R.id.settingsB:
//                startActivity(new Intent(this, SettingsActivity.class));
//                break;
//        }
//
//    }
    private void SendUserToSignUpProfileActivity () {

        Intent signUpProfileIntent = new Intent(ManageSelectDayActivity.this, SignUpProfileActivity.class);
        signUpProfileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signUpProfileIntent);
        finish();

    }

    private void SendUserToSignInActivity ()
    {
        Intent loginIntent = new Intent(ManageSelectDayActivity.this, SignInActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();


    }
    private void SendUserToMainActivity () {
        Intent intent = new Intent(ManageSelectDayActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void SendUserToCreateJoinShowActivity () {

        Intent joinCreateShowIntent = new Intent(ManageSelectDayActivity.this, CreateJoinShowActivity.class);
        joinCreateShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //editDayIntent.putExtra("UserCarryDayDate", );
        startActivity(joinCreateShowIntent);
        finish();


    }
    private void SendUserToEditDayActivity () {

//        //onBindActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");
//
//        onBindUserSelectedDayDate = editTextCreateDayDate.getText().toString().trim();
//
//
//        Intent editDayIntent = new Intent(ManageDaysActivity.this, EditDayActivity.class);
//        editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        //editDayIntent.putExtra("UserCarryDayDateManageDay", onBindUserSelectedDayDate);
//        //editDayIntent.putExtra("UserCarryActiveShowManageDay", textViewShowName.getText().toString().trim());
//        editDayIntent.putExtra("UserCarryDayDate", editTextCreateDayDate.getText().toString().trim());
//        editDayIntent.putExtra("UserCarryActiveShow", textViewShowName.getText().toString().trim());
////          editDayIntent.putExtra("UserCarryLocationName", UserCarryLocationName.trim());
//        editDayIntent.putExtra("UserCarryDepartment", UserCarryDepartment.trim());
//
//        startActivity(editDayIntent);
    }


    //Menu Buttins
    private void UserMenuSelector(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_create_day:
                Toast.makeText(this, "Create Day", Toast.LENGTH_SHORT).show();
                SendUserToCreateDayActivity();
                break;
            case R.id.nav_profile:
                SendUserToSignUpProfileActivity();
                break;
            case R.id.nav_home:
                SendUserToMainActivity();
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_friends:
                SendUserToCreateJoinShowActivity();
                Toast.makeText(this, "Friends", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_find_friends:
                Toast.makeText(this, "Find Friends", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_messages:
                Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                mAuth.signOut();
                SendUserToSignInActivity();
                break;

        }
    }

    @Override
    public void onClick(View v) {

    }
}
