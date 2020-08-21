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
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corstan__cc__managment.Model.DayOfWeek;
import com.example.corstan__cc__managment.Model.DaysOfWeekAdapter;
import com.example.corstan__cc__managment.Model.GDDayTool;
import com.example.corstan__cc__managment.Model.GDLocationTool;
import com.example.corstan__cc__managment.Model.GDShowTool;
import com.example.corstan__cc__managment.Model.GDUserTool;
import com.example.corstan__cc__managment.Model.UserCarryDate;
import com.example.corstan__cc__managment.ViewHolder.DayViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ManageDaysActivity extends AppCompatActivity implements View.OnClickListener {

    Button profileB, availabilityB, paperworkB, scheduleB, settingsB;

    public TextView showTextView, datDateValidationTextView, textViewShowName, textViewShowNameCurrentDate, textViewSelectedDateWeekDay;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public RecyclerView dayList;
    private Toolbar mToolbar;

    private LinearLayoutManager linearLayoutManager;

    private CircleImageView NavProfileImage;
    TextView NavProfileFullName, NavActiveShow, NavUserPosition, dayDate;
    private Button createShowB, createDayB;

    private FirebaseAuth mAuth;

    private DatabaseReference CCSMainDB, showRef, daysRef;

    private ImageButton CreateDayButton;

    private EditText editTextCreateDayDate;

    //    FirebaseRecyclerOptions<CCSUserTool> userQuery;
//    FirebaseRecyclerOptions<CCSDayTool> dayQuery;
//    FirebaseRecyclerAdapter<CCSUserTool, StaffViewHolder> staffAdapter;
//    FirebaseRecyclerAdapter<CCSDayTool, DayViewHolder> dayAdapter;
    public String activeShow, UserCarryDayDate, UserCarryLocationName, UserCarryActiveShow, UserCarryDepartment;
    private String newUserShow, onBindActiveShow;

    private String onBindUserSelectedDayDate;


    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_days);

        //Date today;
        Date todayDate;
        String outputDate;
        SimpleDateFormat formatterDate;

        formatterDate = new SimpleDateFormat("EEEE - dd MMMM yyyy");
        todayDate = new Date();
        outputDate = formatterDate.format(todayDate);


        textViewShowNameCurrentDate = findViewById(R.id.textViewSelectedDate);
        textViewShowNameCurrentDate.setText(outputDate);

//        Date todayDay;
//        String outputDay;
//        SimpleDateFormat formatterDay;
//
//        formatterDay = new SimpleDateFormat("EEEE");
//        todayDay = new Date();
//        outputDay = formatterDay.format(todayDay);

        textViewSelectedDateWeekDay = findViewById(R.id.textViewSelectedDateWeekDay);
        //extViewSelectedDateWeekDay.setText(outputDay);
        GDDayTool gdDayTool = new GDDayTool();

        final GDUserTool gdUserTool = new GDUserTool();
//????????????????????????????????????????????????????????????????????????????????????????????????????????
//                         Carry Intents                                                                \\
//????????????????????????????????????????????????????????????????????????????????????????????????????????
//        editDayIntent.putExtra("UserCarryDayDate", UserCarryDayDate.trim());
//        editDayIntent.putExtra("UserCarryActiveShow", UserCarryActiveSHow.trim());
//        editDayIntent.putExtra("UserCarryActiveShow", UserCarryActiveSHow.trim());


//????????????????????????????????????????????????????????????????????????????????????????????????????????

        if ((getIntent().getExtras()).getString("UserCarryActiveShow") != null) {
            UserCarryActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");
        }
//        else {
//            UserCarryActiveShow = textViewShowName.getText().toString().trim();
//        }
        if (getIntent().getExtras().getString("UserCarryDayDate") != null) {
            onBindUserSelectedDayDate = getIntent().getExtras().getString("UserCarryDayDate");
        }
        if (getIntent().getExtras().getString("UserCarryDepartment") != null) {
            UserCarryDepartment = getIntent().getExtras().getString("UserCarryDepartment");
        }


        editTextCreateDayDate = findViewById(R.id.editTextCreateDayDate);
        editTextCreateDayDate.setText(onBindUserSelectedDayDate.trim());

//
//
//        }

//      onBindActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");
//      activeShow = onBindActiveShow.trim();

        CCSMainDB = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        textViewShowName = findViewById(R.id.textViewShowName);
        //textViewShowName.setText(UserCarryDepartment.trim());

        navigationView = findViewById(R.id.navigation_view);
        final View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
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
                                String activeShowNavView = dataSnapshot.child("activeShow").getValue().toString();
                                //dataSnapshot.getValue(gdUserTool.getClass());
                                gdUserTool.setActiveShow(dataSnapshot.child("activeShow").getValue().toString().trim());
                                activeShow = gdUserTool.getActiveShow();

                                textViewShowName.setText(activeShow);

                                // UserCarryActiveShow = activeShow;

                                NavActiveShow.setText(activeShowNavView);
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


        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Shooting Schedule");

        createDayB = findViewById(R.id.create_day_b);


        //textViewShowName.setText(gdUserTool.getActiveShow());
        //gdUserTool.setActiveShow(textViewShowName.getText().toString().trim());

        drawerLayout = findViewById(R.id.drawable_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_closed);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dayList = findViewById(R.id.dayList);
        dayList.setHasFixedSize(true);
        dayList.setLayoutManager(new LinearLayoutManager(this));




        createDayB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToManageDaysActivity();

            }
        });

        SetDaysAdapter();

    }


    private void CreateDay() {
        SendUserToEditDayActivity();
    }

    private void SendUserToManageDaysActivity() {


        UserCarryDayDate = editTextCreateDayDate.getText().toString().trim();
        UserCarryDepartment = getIntent().getExtras().getString("UserCarryDepartment");

        String onBindActiveShow = NavActiveShow.getText().toString().trim();
        String newActiveShow = onBindActiveShow.trim();


        Intent joinCreateShowIntent = new Intent(ManageDaysActivity.this, EditDayActivity.class);
        joinCreateShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        joinCreateShowIntent.putExtra("UserCarryActiveShow", activeShow);
        joinCreateShowIntent.putExtra("UserCarryDayDate", UserCarryDayDate);
        joinCreateShowIntent.putExtra("UserCarryDepartment", UserCarryDepartment);
        startActivity(joinCreateShowIntent);
        finish();
    }


    @Override
    public void onClick(View v) {


//        String dayDate = editTextDayDate.getText().toString();
//        String lastName = LastName.getText().toString();
//
//        String userCountry = countrySpinnerResult;
//        String userState = stateSpinnerResult;
//        String userCity = citySpinnerResult;
//
//        String userPrimaryDepartment = departmentSpinnerResult;
//        String userPrimaryPosition = positionSpinnerResult;
//
//        String userEmail = UserEmail.getText().toString();
//        String userPhoneNumber = UserPhoneNumber.getText().toString();
//
//
//        if (dayDate.isEmpty()) {
//            editTextEditDayDate.setError("Please provide your Full Name");
//            editTextEditDayDate.requestFocus();
//            return;
//
//        }
//        if (lastName.isEmpty()) {
//            LastName.setError("Please set a Username");
//            LastName.requestFocus();
//            return;
//        }
//        else
//        {
//            HashMap createEditDayHashMap = new HashMap();
//            createEditDayHashMap.put("dayDate", dayDate);
//            userMap.put("lastName", lastName);
//
//            userMap.put("activeShow", "");
//
//            userMap.put("userEmail", userEmail);
//            userMap.put("userPhoneNumber", userPhoneNumber);
//
//            userMap.put("dob", "");
//
//            userMap.put("userId", currentUserID);
//
//            userMap.put("userCountry", userCountry);
//            userMap.put("userState", userState);
//            userMap.put("userCity", userCity);
//
//            userMap.put("userPrimaryDepartment", userPrimaryDepartment);
//            userMap.put("userPrimaryPosition", userPrimaryPosition);
//
//
//
//
//


//            CCSMainDB.child("Shows")
//                    .child("Charmed S2")
//                    .child("Days")
//                    .child(editTextEditDayDate.getText().toString())
//                    //updateChildren()
//                    .addOnCompleteListener(new OnCompleteListener() {
//                        @Override
//                        public void onComplete(@NonNull Task task) {
//
//                            if (task.isSuccessful()) {
//                                Toast.makeText(EditDayActivity.this, "Lets Du Dis", Toast.LENGTH_LONG).show();
//                                SendUserToMainActivity();
//                                finish();
//
//                            } else {
//                                String message = task.getException().getMessage();
//                                Toast.makeText(EditDayActivity.this, "Access to nuclear weapons DENIED: " + message, Toast.LENGTH_LONG).show();
//
//
//                            }
//
//                        }
//                    });
    }


    private void SendUserToCreateEditShow() {

        Intent createShowIntent = new Intent(ManageDaysActivity.this, CreateShowActivity.class);
        createShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(createShowIntent);
        finish();
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

    // Setting Show Recycler
    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseRecyclerOptions options =
//                new FirebaseRecyclerOptions.Builder<GDDayTool>()
//                        .setQuery(daysRef, GDDayTool.class)
//                        .build();
//
//        FirebaseRecyclerAdapter<GDDayTool, com.example.corstan__cc__managment.ViewHolder.DayViewHolder> adapter =
//                new FirebaseRecyclerAdapter<GDDayTool, DayViewHolder>() {
//                    @NonNull
//                    @Override
//                    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daycard, parent, false)
//
//                        return DayViewHolder();
//                    }
//
//                    @Override
//                    protected void onBindViewHolder(@NonNull DayViewHolder dayViewHolder, int i, @NonNull GDDayTool gdDayTool) {
//
//                    }
//                };






//?????????????????????????????????????????????????????????????????????????????????????????????????
        //              SAVE BELOW
        //????????????????????????????????????????????????????
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null) {
            SendUserToSignInActivity();
        }
        else {
            CheckUserExistence();
        }
    }

    private void SetDaysAdapter() {

        UserCarryActiveShow.equals(textViewShowName.getText().toString().trim());


        daysRef = FirebaseDatabase.getInstance().getReference()
                .child("Shows")
                .child(UserCarryActiveShow)
                .child("Days")
//                .child(UserCarryDayDate)
//                ////.child(editTextEditDayDate.getText().toString())
//                .child("Locations")
        ;

        FirebaseRecyclerOptions<GDDayTool> options =
                new FirebaseRecyclerOptions.Builder<GDDayTool>()
                        .setQuery(daysRef, GDDayTool.class)
                        .build();

        final FirebaseRecyclerAdapter<GDDayTool, DayViewHolderV1> adapter =
                new FirebaseRecyclerAdapter<GDDayTool, DayViewHolderV1>(options) {

                    String onBindDate;



                    @Override
                    protected void onBindViewHolder(@NonNull final DayViewHolderV1 dayViewHolderV1, int i, @NonNull final GDDayTool gdDayTool) {
                        //dayCardDayDateDBFormat
                        onBindDate = (gdDayTool.getDayDate());
                        dayViewHolderV1.dayCardDayDateDBFormat.setText(onBindDate);

                        //dayViewHolderV1.textViewDayDate.;

                        //dayViewHolderV1.dayCardToday = itemView.findViewById(R.id.dayCardToday);
                        dayViewHolderV1.dayCardEpisodeName.setText(gdDayTool.getDayEpisodeName());
                        dayViewHolderV1.episodeDayOfDay2.setText(gdDayTool.getDayDayOfDay1()+" of " +gdDayTool.getDayDayOfDay2());
                        dayViewHolderV1.episodeDayCrewCall.setText(gdDayTool.getDayCrewCall());



                        dayViewHolderV1.dayCardGoToDayB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                ;

                                Intent editDayIntent = new Intent(ManageDaysActivity.this, EditDayActivity.class);
                                editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                //editDayIntent.putExtra("UserCarryLocationName", gdLocationTool.getLocationName());
                                editDayIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow);
                                editDayIntent.putExtra("UserCarryDayDate", gdDayTool.getDayDate());
                                editDayIntent.putExtra("UserCarryDepartment", UserCarryDepartment);

                                startActivity(editDayIntent);

                            }
                        });


                        Date dateOutput;
                        String dateInput;
                        String convertedDate;
////
////
////
//                        dateInput = onBindDate;
//                        DateFormat dateFormat = DateFormat.getDateInstance();
//                        try {
//                            dateOutput = dateFormat.parse(dateInput);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
////
//                        SimpleDateFormat formatterTextView = new SimpleDateFormat("EEE dd MMMM yyyy");
//                        dateOutput = new Date();
//                        convertedDate = formatterTextView.format(dateOutput);
//
//
//                        dateInput=(gdDayTool.getDayDate());
//                        SimpleDateFormat formatter, formatterTextView;
//
//
//                        formatterTextView = new SimpleDateFormat()
//                        //formatterTextView = new SimpleDateFormat(dateInput, "EEE dd MMMM yyyy");
//                        textViewDateOutput = new Date();
//                        selectedDateView = formatterTextView.format(textViewDateOutput);
//
//
                        Date todayDate;
                        String todayDateOutput;
                        SimpleDateFormat formatter, formatterTextView;

                        formatter = new SimpleDateFormat("dd-MM-yyyy");
                        todayDate = new Date();
                        todayDateOutput = formatter.format(todayDate);
//
                        if (todayDateOutput.equals(onBindDate)) {

                            dayViewHolderV1.dayCardToday.setText("Today");
                            dayViewHolderV1.dayCardToday.setBackgroundColor(getResources().getColor(R.color.dayCardToday));

                        }







                    }



                    @NonNull
                    @Override
                    public DayViewHolderV1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daycard, parent, false);
                        DayViewHolderV1 dayViewHolderV1 = new DayViewHolderV1(view);
                        return dayViewHolderV1;
                    }
                };

        adapter.startListening();
        dayList.setAdapter(adapter);

    }

    public class DayViewHolderV1 extends RecyclerView.ViewHolder{

        TextView textViewDayDate, dayCardDayDateDBFormat, dayCardToday, dayCardEpisodeName
                , episodeDayOfDay2, episodeDayCrewCall;

        Button dayCardGoToDayB;

        public DayViewHolderV1(@NonNull View itemView) {
            super(itemView);

            textViewDayDate = itemView.findViewById(R.id.dayCardDayDate);
            dayCardDayDateDBFormat = itemView.findViewById(R.id.dayCardDayDateDBFormat);
            dayCardToday = itemView.findViewById(R.id.dayCardToday);
            dayCardEpisodeName = itemView.findViewById(R.id.dayCardEpisodeName);
            episodeDayOfDay2 = itemView.findViewById(R.id.episodeDayOfDay2);
            episodeDayCrewCall = itemView.findViewById(R.id.episodeDayOfDay);

            dayCardGoToDayB =  itemView.findViewById(R.id.dayCardGoToDayB);


        }
    }
        private void SendUserToMainActivity () {
            Intent intent = new Intent(ManageDaysActivity.this, MainActivity.class);
            startActivity(intent);
        }

        private void SendUserToCreateJoinShowActivity () {

            Intent joinCreateShowIntent = new Intent(ManageDaysActivity.this, CreateJoinShowActivity.class);
            joinCreateShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //editDayIntent.putExtra("UserCarryDayDate", );
            startActivity(joinCreateShowIntent);
            finish();


        }

        private void SendUserToEditDayActivity () {

            //onBindActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");

            onBindUserSelectedDayDate = editTextCreateDayDate.getText().toString().trim();


            Intent editDayIntent = new Intent(ManageDaysActivity.this, EditDayActivity.class);
            editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //editDayIntent.putExtra("UserCarryDayDateManageDay", onBindUserSelectedDayDate);
            //editDayIntent.putExtra("UserCarryActiveShowManageDay", textViewShowName.getText().toString().trim());
            editDayIntent.putExtra("UserCarryDayDate", editTextCreateDayDate.getText().toString().trim());
            editDayIntent.putExtra("UserCarryActiveShow", textViewShowName.getText().toString().trim());
//          editDayIntent.putExtra("UserCarryLocationName", UserCarryLocationName.trim());
            editDayIntent.putExtra("UserCarryDepartment", UserCarryDepartment.trim());

            startActivity(editDayIntent);
        }


        @Override
        public void onStop () {
            super.onStop();
//        if (dayAdapter != null)
//            dayAdapter.stopListening();
        }
        @Override
        protected void onResume () {
            super.onResume();
//        if (dayAdapter != null)
//            dayAdapter.startListening();
        }


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

        private void SendUserToSignUpProfileActivity () {

            Intent signUpProfileIntent = new Intent(ManageDaysActivity.this, SignUpProfileActivity.class);
            signUpProfileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signUpProfileIntent);
            finish();

        }


        private void SendUserToSignInActivity ()
        {
            Intent loginIntent = new Intent(ManageDaysActivity.this, SignInActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginIntent);
            finish();


        }

        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item)
        {

            if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        private void SendUserToCreateDayActivity () {

            Intent createDayIntent = new Intent(ManageDaysActivity.this, CreateDayActivity.class);
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
    }
