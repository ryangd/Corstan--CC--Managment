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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.corstan__cc__managment.Model.GDDayTool;
import com.example.corstan__cc__managment.Model.GDShowTool;
import com.example.corstan__cc__managment.Model.GDUserTool;
import com.example.corstan__cc__managment.Model.DayOfWeek;
import com.example.corstan__cc__managment.Model.DaysOfWeekAdapter;
import com.example.corstan__cc__managment.Model.GDLocationTool;
import com.example.corstan__cc__managment.Model.UserCarryDate;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditDayActivity extends AppCompatActivity implements View.OnClickListener {


//????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//                                     EditTexts Raw DO NOT DELETE
//?????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//    editTextEditDayDate,
//    editTextEditDayEpisodeName,
//    editTextEditDayDayOfDay1
//    editTextEditDayDayOfDay2
//    editTextEditDayCrewCall,
//    editTextEditDayLunchTime,
//    editTextEditDaySunUp,
//    editTextEditDaySunUpDn;
//
//    cbMainUnit,
//    cbSecondUnit;
//?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????

    private ArrayList<DayOfWeek> dayOfWeekArrayList;
    private DaysOfWeekAdapter daysOfWeekAdapter;


    Uri uriProfileImage;

    //Edit Texts


    TextView textViewShowName, textViewEditDayTitle, textViewCurrentDay;
    //Button


    Toolbar mToolbar;

    //Firebase database Refrence
    DatabaseReference CCSMainDB;

    DatabaseReference dayRef, locationRef, currentUserDepartmentDbRef;
    //Child DB Referecnces
    DatabaseReference activeShowDB;

    String activeShowString, currentUserDepartment;

    //Spinners
    Spinner selectDaySpinner;

    //Local Data Transfer Strings
    String day_Date_Data, day_ActiveShow_Data_Send, day_Day_Day_Data_Send;

    //Firebase Auth
    private FirebaseAuth mAuth;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private String currentUserId, userId, dayDate;

    private EditText editTextEditDayDate, editTextEditDayEpisodeName,
            editTextEditDayCrewCall, editTextEditDayLunchTime, editTextEditDaySunUp, editTextEditDaySunUpDn, editTextEditDayDayOfDay1, editTextEditDayDayOfDay2;

    private CheckBox cbMainUnit, cbSecondUnit;


    private RecyclerView scenesList, locationList, bookedStaffList;
    //editDaySceneRecycler, editDayLocationRecycler, editDayStaffRecycler,

    private TextView dept_Staff_TextView, editDayStaffHeaderTextView;

    private CircleImageView NavProfileImage;
    private TextView NavProfileFullName, NavActiveShow, NavUserPosition;

    Button manageScenesB, createDayB, manageLocationsB, manageStaffB, editLocationB, button;


    String onBindUserSelectedDayDateReceiveManageDay, onBindUserSelectedDayDateReceiveCreateDay
            , onBindUserSelectedDayDateSend
            , referenceDateSend, referenceDateReceive
            , activeShow, activeShowDBSnapshot, onBindUserActiveShowReceive
            , UserCarryDayDate, UserCarryActiveShow, UserCarryLocationName, UserCarryDepartment;
    private String dayOfTheWeekSpinnerResult;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_day_v1);
        GDDayTool gdDayTool = new GDDayTool();
        GDUserTool gdUserTool = new GDUserTool();

        Date todayDate, textViewDateOutput;
        String todayDateOutput, selectedDateView;
        SimpleDateFormat formatter, formatterTextView;

        formatter = new SimpleDateFormat("dd-MM-yyyy");
        todayDate = new Date();
        todayDateOutput = formatter.format(todayDate);


        formatterTextView = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        textViewDateOutput = new Date();
        selectedDateView = formatterTextView.format(textViewDateOutput);


        textViewCurrentDay = findViewById(R.id.textViewCurrentDay);
        textViewCurrentDay.setText(selectedDateView);
//????????????????????????????????????????????????????????????????????????????????????????????????????????
//                         Carry Intents                                                                \\
//????????????????????????????????????????????????????????????????????????????????????????????????????????
//        editDayIntent.putExtra("UserCarryDayDate", UserCarryDayDate.trim());
//        editDayIntent.putExtra("UserCarryActiveShow", UserCarryActiveSHow.trim());
//        editDayIntent.putExtra("UserCarryActiveShow", UserCarryActiveSHow.trim());


//????????????????????????????????????????????????????????????????????????????????????????????????????????
        if (Objects.requireNonNull(getIntent().getExtras()).getString("UserCarryDayDate") != null) {
            UserCarryDayDate = getIntent().getExtras().getString("UserCarryDayDate").trim();
        }
        if (Objects.requireNonNull(getIntent().getExtras()).getString("UserCarryActiveShow") != null) {
            UserCarryActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");
        }
        if (Objects.requireNonNull(getIntent().getExtras()).getString("UserCarryLocationName") != null) {
            UserCarryLocationName = getIntent().getExtras().getString("UserCarryLocationName");
        }
        if (Objects.requireNonNull(getIntent().getExtras()).getString("UserCarryDepartment") != null) {
            UserCarryDepartment = Objects.requireNonNull(getIntent().getExtras().getString("UserCarryDepartment")).trim();
            editDayStaffHeaderTextView = findViewById(R.id.editDayStaffHeaderTextView);
            editDayStaffHeaderTextView.setText(UserCarryDepartment + " Booked Staff");
            textViewEditDayTitle  = findViewById(R.id.textViewEditDayTitle);
            textViewEditDayTitle.setText(UserCarryDepartment);
            //gdUserTool.setActiveShow(UserCarryDepartment);
        }


                //onBindUserSelectedDayDateReceiveCreateDay = getIntent().getExtras().getString("UserCarryDayDateCreateDay").trim();





            //onBindUserActiveShowReceive = getIntent().getExtras().getString("UserCarryActiveShowManageDay");
            //activeShow.equals(onBindUserActiveShowReceive);



         //   String activeShow = UserCarryActiveSHow.trim();
        //activeShow = onBindUserActiveShowReceive;



            CCSMainDB = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        CCSMainDB.child("Users")
                .child("Management")
                .child(currentUserId)
                .addValueEventListener(new ValueEventListener() {
                   // TextView textViewShowName = findViewById(R.id.textViewShowName);



                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //textViewShowName.setText(dataSnapshot.child("activeShow").getValue().toString());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        //activeShow = gdUserTool.getActiveShow().trim();

//        currentUserDepartment = gdUserTool.getUserPrimaryDepartment().trim();
//        editTextEditDayDate = findViewById(R.id.editTextEditDayDate);
//        if (onBindUserSelectedDayDateReceiveManageDay != null) {
//            editTextEditDayDate.setText(onBindUserSelectedDayDateReceiveManageDay.trim());
//
//        }







            scenesList = findViewById(R.id.editDaySceneRecycler);
            scenesList.setHasFixedSize(true);
            scenesList.setLayoutManager(new LinearLayoutManager(this));

            locationList = findViewById(R.id.editDayLocationRecycler);
            locationList.setHasFixedSize(true);
            locationList.setLayoutManager(new LinearLayoutManager(this));

            bookedStaffList = findViewById(R.id.editDayStaffRecycler);
            bookedStaffList.setHasFixedSize(true);
            bookedStaffList.setLayoutManager(new LinearLayoutManager(this));


            //Data Transfer Int's
//        day_Date_Data = getIntent().getExtras().get("day_Date_Data_Send").toString();
//        day_ActiveShow_Data_Send = getIntent().getExtras().get("day_ActiveShow_Data_Send").toString();
//        day_Day_Day_Data_Send = getIntent().getExtras().get("day_Day_Day_Data_Send").toString();

            // String for DB Child updates


            //Set Variables
            //Text Views
//        editTextDayShow = findViewById(R.id.editTextDayShow);
//
//        //Edit Texts
//        editTextDayDay = findViewById(R.id.editTextDayDay);
            //editTextDayDate = findViewById(R.id.editTextEditDayDate);
            //editTextDayDate.setText(GDDayTool.getDayDate());
//        editTextDayAddress = findViewById(R.id.editTextDayAddress);
//        editTextDayCity = findViewById(R.id.editTextDayCity);
//
//        //Data Transfer Set Texts
//        editTextDayShow.setText(day_ActiveShow_Data_Send);
//        editTextDayDate.setText(day_Date_Data);
//        editTextDayDay.setText(day_Day_Day_Data_Send);




        editTextEditDayDate = findViewById(R.id.editTextEditDayDate);
             //editTextEditDayDate.setText(onBindUserSelectedDayDateReceive);
            editTextEditDayDate.setText(UserCarryDayDate.trim());
            editTextEditDayEpisodeName = findViewById(R.id.editTextEditDayEpisodeName);
            editTextEditDayDayOfDay1 = findViewById(R.id.editTextEditDayDayOfDay1);
            editTextEditDayDayOfDay2 = findViewById(R.id.editTextEditDayDayOfDay2);
            editTextEditDayCrewCall = findViewById(R.id.editTextEditDayCrewCallTime);
            editTextEditDayLunchTime = findViewById(R.id.editTextEditDayLunchTime);
            editTextEditDaySunUp = findViewById(R.id.editTextEditDaySunUp);
            editTextEditDaySunUpDn = findViewById(R.id.editTextEditDaySunUpDn);

            cbMainUnit = findViewById(R.id.cbMainUnit);
            cbSecondUnit = findViewById(R.id.cbSecondUnit);




            //Button
            manageScenesB = findViewById(R.id.manageScenesB);
            manageLocationsB = findViewById(R.id.manageLocationsB);
            manageStaffB = findViewById(R.id.manageStaffB);
            createDayB = findViewById(R.id.createDayB);
            //button = findViewById(R.id.button);


            manageScenesB.setOnClickListener(this);
            manageLocationsB.setOnClickListener(this);
            manageStaffB.setOnClickListener(this);
            createDayB.setOnClickListener(this);

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



        //Uses onItemSelected @overide


            initDaysOfWeekList();
            daysOfWeekAdapter = new DaysOfWeekAdapter(this, dayOfWeekArrayList);

//        CCSMainDB.child("Shows")
//                .child(activeShow)
//                .child("Days")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        if (dataSnapshot.hasChild(editTextEditDayDate.getText().toString())) {
//
//                            GDDayTool gdDayTool = dataSnapshot.getValue(GDDayTool.class);
//
//
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                UserMenuSelector(item);

                return false;
            }
        });

        CCSMainDB.child("Shows")
                .child(UserCarryActiveShow)
                .child("Days")
                .child(editTextEditDayDate.getText().toString())
                .child("AD Department")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //private String dayDate;
//                            private
//                            private String dayDayOfDay1;
//                            private String dayDayOfDay2;
//                            private String dayCrewCall;
//                            private String dayLunchTime;
//                            private String daySunUp;
//                            private String daySunUpDn;
//
//                            private Boolean cbMainUnit;
//                            private Boolean cbSecondUnit;
//??????????????????????????????????????????????????????????????????????????????????????????????????
//                            Example
//???????????????????????????????????????????????????????????????????????????????????????????????????
//                           if (dataSnapshot.hasChild("firstName")) {
//                                String fullName = dataSnapshot.child("firstName")
//                                        .getValue()
//                                        .toString()
//                                        + " " + dataSnapshot
//                                        .child("lastName")
//                                        .getValue();
//                                NavProfileFullName.setText(fullName);
// ?????????????????????????????????????????????????????????????????????????????????????????????????


//                            if (dataSnapshot.hasChild("dayEpisodeName") ) {
//

                        if (dataSnapshot.hasChild("dayEpisodeName")) {
                            editTextEditDayEpisodeName.setText(dataSnapshot.child("dayEpisodeName").getValue().toString());
                        }
                        if (dataSnapshot.hasChild("dayCrewCall")) {
                            editTextEditDayCrewCall.setText(dataSnapshot.child("dayCrewCall").getValue().toString());
                        }
                        if (dataSnapshot.hasChild("dayDayOfDay1")) {
                            editTextEditDayDayOfDay1.setText(dataSnapshot.child("dayDayOfDay1").getValue().toString());
                        }
                        if (dataSnapshot.hasChild("dayDayOfDay2")) {
                            editTextEditDayDayOfDay2.setText(dataSnapshot.child("dayDayOfDay2").getValue().toString());
                        }
                        if (dataSnapshot.hasChild("dayLunchTime")) {
                            editTextEditDayLunchTime.setText(dataSnapshot.child("dayLunchTime").getValue().toString());
                        }
                        if (dataSnapshot.hasChild("daySunUp")) {
                            editTextEditDaySunUp.setText(dataSnapshot.child("daySunUp").getValue().toString());
                        }
                        if (dataSnapshot.hasChild("daySunUpDn")) {
                            editTextEditDaySunUpDn.setText(dataSnapshot.child("daySunUpDn").getValue().toString());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        dayRef = FirebaseDatabase.getInstance().getReference()
                .child("Shows")
                .child(UserCarryActiveShow.trim())
                .child("Days")
                .child(UserCarryDayDate.trim());





        SetBookedLocationsAdapter();
//        SetStaffAdapter();
       SetBookedStaffAdapter();
        }

    private void SetBookedStaffAdapter() {
        FirebaseRecyclerOptions<GDUserTool> bookedUserQuery;
        FirebaseRecyclerAdapter<GDUserTool, ManageStaffActivity.BookedStaffViewHolderHolder> staffAdapter;
        UserCarryDepartment = getIntent().getExtras().getString("UserCarryDepartment");


        CCSMainDB.child("Users")
                .child("Management")
                .child(currentUserId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        GDUserTool GDUserTool = new GDUserTool();

                        if (dataSnapshot.child("userId").exists()) {
                            currentUserId = dataSnapshot.child("userId").getValue().toString();
                        }
                        if (dataSnapshot.child("userPrimaryDepartment").exists()) {
                            currentUserDepartment = dataSnapshot.child("userPrimaryDepartment").getValue().toString();
                        }
//                        if (dataSnapshot.child("activeShow").exists()) {
//                            GDUserTool.setActiveShow(dataSnapshot.child("activeShow").getValue().toString());
//                            activeShow = GDUserTool.getActiveShow();
//                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        //UserCarryDepartment = textViewEditDayTitle.getText().toString().trim();
        bookedUserQuery = new FirebaseRecyclerOptions.Builder<GDUserTool>()
//                .setQuery(CCSMainDB.child("users"), CCSUserTool.class).build();
                .setQuery(dayRef
                        .child(UserCarryDepartment.toString())
                        .child("Booked Staff"), GDUserTool.class).build();
        staffAdapter = new FirebaseRecyclerAdapter<GDUserTool, ManageStaffActivity.BookedStaffViewHolderHolder>(bookedUserQuery) {
            @Override
            protected void onBindViewHolder(@NonNull final ManageStaffActivity.BookedStaffViewHolderHolder holder, int i, @NonNull final GDUserTool profile) {

                holder.bookedStaffCardUserName.setText(profile.getFirstName() + "" + profile.getLastName());
                holder.bookedStaffCardUserPosition.setText(profile.getUserPrimaryPosition());


                dayRef
                        .child(currentUserDepartment)
                        .child("Booked Staff")
                        .child(profile.getUserId())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("callTime")) {
                                    String callTime = dataSnapshot.child("callTime").getValue().toString().trim();
                                    holder.bookedStaffCallTimeEditText.setText(callTime);
                                    holder.bookedStaffCallTimeEditText.setBackgroundColor(getResources().getColor(R.color.ccBlue));
                                    if (callTime.equals("")) {
                                        holder.bookedStaffCallTimeEditText.setBackgroundColor(Color.RED);
                                        holder.bookedStaffCallTimeEditText.setHintTextColor(Color.WHITE);

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

//
//                if (holder.bookedStaffCallTimeEditText.getText().toString().trim() != callTime) {
//                    holder.bookedStaffCallTimeEditText.setBackgroundColor(Color.YELLOW);
//                }




//??????????????????????????????????????????????????????????????????????????????????????????????????
//                          Loads - Staff info, callTime

                CCSMainDB
                        .child("Users")
                        .child("Management")
                        .child(profile.getUserId())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if (dataSnapshot.hasChild("Profile Photo")) {

                                    String image = dataSnapshot.child("Profile Photo")
                                            .getValue()
                                            .toString();
                                    Picasso.get().load(image).placeholder(R.drawable.profile).into(holder.bookedStaffCardProfilePicture);
                                }
                                if (dataSnapshot.hasChild("callTime")) {

                                    String callTime = dataSnapshot.child("Profile Photo").getValue().toString();
                                    holder.bookedStaffCallTimeEditText.setText(callTime);
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                userId = profile.getUserId();

                holder.bookedStaffCardUpdateCallTimeB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap onBindUpdateCallTimeHashMap = new HashMap();
                        onBindUpdateCallTimeHashMap.put("callTime", holder.bookedStaffCallTimeEditText.getText().toString().trim());


                        dayRef
                                .child(currentUserDepartment)
                                .child("Booked Staff")
                                .child(profile.getUserId())
                                .updateChildren(onBindUpdateCallTimeHashMap)
                                .addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        holder.bookedStaffCallTimeEditText.setBackgroundColor(Color.GREEN);

                                    }
                                });



                    }

                });


//                holder.cardViewButton.setOnClickListener(new View.OnClickListener() {
//////                    @Override
//////                    public void onClick(View v) {
//////
//////                        //HashMap onBindStaffHashMap = new HashMap()
//////
//////                        dayRef
//////                                .child(currentUserDepartment)
//////                                .child("Booked Staff")
//////                                .child(userId)
//////                                .setValue(profile);
//////
//////
//////
//////
//////                    }
//                });


            }

            @NonNull
            @Override
            public ManageStaffActivity.BookedStaffViewHolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookedstaffcard, parent, false);
                return new ManageStaffActivity.BookedStaffViewHolderHolder(view);
            }
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        bookedStaffList.setLayoutManager(linearLayoutManager);
        staffAdapter.startListening();
        bookedStaffList.setAdapter(staffAdapter);
    }

    private void SetBookedLocationsAdapter() {

        locationRef = FirebaseDatabase.getInstance().getReference()
                .child("Shows")
                .child(UserCarryActiveShow)
                .child("Days")
                .child(editTextEditDayDate.getText().toString())
                .child("Locations")
                .child("Locations");
//                .child("Days")
//                .child(editTextEditDayDate.getText().toString())
//                .child("Locations");

        FirebaseRecyclerOptions<GDLocationTool> options =
                new FirebaseRecyclerOptions.Builder<GDLocationTool>()
                        .setQuery(locationRef,
                                GDLocationTool.class)
                        .build();

        final FirebaseRecyclerAdapter<GDLocationTool, LocationViewHolder> adapter =
                new FirebaseRecyclerAdapter<GDLocationTool, LocationViewHolder>(options) {






                    @Override
                    protected void onBindViewHolder(@NonNull LocationViewHolder locationViewHolder, int i, @NonNull final GDLocationTool gdLocationTool) {




                        locationViewHolder.locationName.setText(gdLocationTool.getLocationName());
                        final String locationName = locationViewHolder.locationName.getText().toString();
                        locationViewHolder.locationAddress.setText(gdLocationTool.getLocationAddress());
                        locationViewHolder.locationCity.setText(gdLocationTool.getLocationCity());

                        //locationViewHolder.locationNumberOfScenes.setText(gdLocationTool.getLocationAddress());

                        Boolean ExtInput = gdLocationTool.getLocationExt();
                        Boolean IntExtInput = gdLocationTool.getLocationInt();

                        String IntExtResult;

                        if (ExtInput = true) {
                            IntExtResult = "Ext.";
                        }
                        else{
                            IntExtResult = "Int.";
                        }




                        locationViewHolder.showLocationDetailsB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {



                                {
                                    Intent editDayIntent = new Intent(EditDayActivity.this, AddLocationActivity.class);
                                    editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    editDayIntent.putExtra("UserCarryLocationName", gdLocationTool.getLocationName());
                                    editDayIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow);
                                    editDayIntent.putExtra("UserCarryDayDate", UserCarryDayDate);
                                    editDayIntent.putExtra("UserCarryDepartment", UserCarryDepartment);

                                    //editDayIntent.putExtra("UserCarryDayDate", textViewSelectedDate.getText().toString().trim());
                                    //editDayIntent.putExtra("UserCarryActiveSHow", textViewSelectedDate.getText().toString().trim());

                                    startActivity(editDayIntent);
                                    finish();
                                }
                            }
                        });

                        locationViewHolder.locationCardRemoveFromDayB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                CCSMainDB.child("Shows")
                                        .child(UserCarryActiveShow)
                                        .child("Days")
                                        .child(UserCarryDayDate)
                                        .child("Locations")
                                        .child("Locations")
                                        .child(locationName)
                                        .removeValue();
                                        ;

                            }
                        });


                    }

                    @NonNull
                    @Override
                    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationsetcard, parent, false);
                        LocationViewHolder viewHolderHolder = new LocationViewHolder(view);
                        return viewHolderHolder;
                    }
                };


        adapter.startListening();
        locationList.setAdapter(adapter);

    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder {

        TextView locationName, locationAddress
                ,locationCity, locationIntExt
                , locationNumberOfScenes;




        Button showLocationDetailsB, locationCardRemoveFromDayB;

        public LocationViewHolder(@NonNull View itemView) {



            super(itemView);

            locationName = itemView.findViewById(R.id.locationCardLocationName);
            locationAddress= itemView.findViewById(R.id.locationCardLocationAddress);
            locationCity= itemView.findViewById(R.id.locationCardLocationCity);
//            locationCrewPark= itemView.findViewById(R.id.locationCardLocationC);
//            locationCrewParkCity= itemView.findViewById(R.id.locationCardLocationName);

            //cbLocationIntExt= itemView.findViewById(R.id.locationCardLocationIntExt);


            //dayDate = itemView.findViewById(R.id.dayCardDayDate);
            //dayDay = itemView.findViewById(R.id.dayCardDayDay);

            showLocationDetailsB = itemView.findViewById(R.id.locationCardShowLocationDetails);
            locationCardRemoveFromDayB = itemView.findViewById(R.id.locationCardRemoveFromDayB);
            //activeShow = itemView.findViewById(R.id.dayCardActiveShow);

        }
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            SendUserToMainActivity();
        }


        return super.onOptionsItemSelected(item);
    }
    private void SendUserToMainActivity() {
        Intent intent = new Intent(EditDayActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void SaveDayInformation() {



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {


                activeShow = UserCarryActiveShow;

                dayRef = CCSMainDB
                        .child("Shows")
                        .child(activeShow)
                        .child("Days");


                CCSMainDB = FirebaseDatabase.getInstance().getReference();

                CCSMainDB.child("Users")
                        .child(currentUserId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                String userId = profile.getUid();


                GDDayTool gdDayTool = new GDDayTool();

                //????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//                                     EditTexts Raw DO NOT DELETE
//?????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//    editTextEditDayDate,
//    editTextEditDayEpisodeName,
//    editTextEditDayDayOfDay1
//    editTextEditDayDayOfDay2
//    editTextEditDayCrewCall,
//    editTextEditDayLunchTime,
//    editTextEditDaySunUp,
//    editTextEditDaySunUpDn;
//
//    cbMainUnit,
//    cbSecondUnit;
//?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????

                if (editTextEditDayDate == null) {
                    editTextEditDayDate.setError("Please set the 'Date'");
                    editTextEditDayDate.requestFocus();
                    return;
                }
//
//                if (activeShowString == null) {
//                    SendUserToCreateJoinShow();
//                }
//

                gdDayTool.setShowName(UserCarryActiveShow.trim());


                gdDayTool.setDayDate(editTextEditDayDate.getText().toString());
                String dayDate = editTextEditDayDate.getText().toString().trim();

                gdDayTool.setDayEpisodeName(editTextEditDayEpisodeName.getText().toString());
                gdDayTool.setDayDayOfDay1(editTextEditDayDayOfDay1.getText().toString());
                gdDayTool.setDayDayOfDay2(editTextEditDayDayOfDay2.getText().toString());
                gdDayTool.setDayCrewCall(editTextEditDayCrewCall.getText().toString());
                gdDayTool.setDayLunchTime(editTextEditDayLunchTime.getText().toString());
                gdDayTool.setDaySunUp(editTextEditDaySunUp.getText().toString());
                gdDayTool.setDaySunUpDn(editTextEditDaySunUpDn.getText().toString());

                gdDayTool.setCbMainUnit(cbMainUnit.isChecked());
                gdDayTool.setCbSecondUnit(cbSecondUnit.isChecked());

                //Save to
                //Locations Dept
//                CCSMainDB.child("Shows")
//                        .child(UserCarryActiveSHow)
//                        .child("Locations")
//                        .child("Locations")
//                        .child(ed)
//                        .setValue(gdDayTool);

                CCSMainDB.child("Shows")
                        .child(UserCarryActiveShow)
                        .child("Days")
                        .child(gdDayTool.getDayDate())
                        .setValue(gdDayTool);

                CCSMainDB.child("Shows")
                        .child(UserCarryActiveShow)
                        .child("Days")
                        .child(gdDayTool.getDayDate())
                        .child("AD Department")
                        .setValue(gdDayTool);



            }
        }




















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
    private void UserMenuSelector (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.nav_create_day:
                Toast.makeText(this, "Create Day", Toast.LENGTH_SHORT).show();
                //SendUserToCreateDayActivity();
                break;
            case R.id.nav_profile:
                SendUserToSignUpProfileActivity();
                break;
            case R.id.nav_home:
                SendUserToMainActivity();
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_friends:
                //SendUserToCreateJoinShowActivity();
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

    private void SetStaffAdapter() {

        //Get currentUserId Department


//
//            locationRef = FirebaseDatabase.getInstance().getReference()
//                    .child("Shows")
//                    .child(activeShow)
//                    .child("Days")
//                    .child(editTextEditDayDate.getText().toString())
//                    .child("Day");
//
//
//            FirebaseRecyclerOptions<GDLocationTool> options =
//                    new FirebaseRecyclerOptions.Builder<GDLocationTool>()
//                            .setQuery(locationRef, GDLocationTool.class)
//                            .build();
//
//            final FirebaseRecyclerAdapter<GDLocationTool, EditDayActivity.LocationViewHolder> adapter =
//                    new FirebaseRecyclerAdapter<GDLocationTool, EditDayActivity.LocationViewHolder>(options) {
//                        @Override
//                        protected void onBindViewHolder(@NonNull final EditDayActivity.LocationViewHolder locationViewHolder, int i, @NonNull final GDLocationTool gdLocationTool) {
//
//                            locationViewHolder.locationName.setText(gdLocationTool.getLocationName());
//                            locationViewHolder.locationAddress.setText((gdLocationTool.getLocationAddress() + ", " + gdLocationTool.getLocationCity()));
//
//
////                            locationViewHolder.editLocationB.setOnClickListener(new View.OnClickListener() {
////                                @Override
////                                public void onClick(View v) {
////
////
////                                    {
////                                        Intent editDayIntent = new Intent(EditDayActivity.this, AddLocationActivity.class);
////                                        editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                                        //editDayIntent.putExtra("UserCarryLocationName", gdLocationTool.getLocationName());
////                                       // editDayIntent.putExtra("UserCarryDayDate", editTextEditDayDate.getText().toString());
////                                        startActivity(editDayIntent);
////                                        finish();
////
////                                    };
////                                }
////                            });
//
//
//
//
//                        }
//
//
//                        @NonNull
//                        @Override
//                        public EditDayActivity.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationcard_simple, parent, false);
//                            EditDayActivity.LocationViewHolder viewHolderHolder = new LocationViewHolder(view);
//                            return viewHolderHolder;
//
//                        }
//                    };


//                new FirebaseRecyclerAdapter<GDLocationTool, EditDayActivity.LocationViewHolder>(options) {
//                    @Override
//                    protected void onBindViewHolder(@NonNull LocationViewHolder locationViewHolder, int i, @NonNull GDLocationTool gdLocationTool) {
//
//                        locationViewHolder.locationName.setText(gdLocationTool.getLocationName());
//                    }
//
//                    @Override
//                    protected void onBindViewHolder(@NonNull final MainActivity.DayViewHolderHolder holder, final int position, @NonNull final CCSDayTool ccsDayTool) {
//                        holder.dayDate.setText(ccsDayTool.getDayDate());
//
//
//
//                        holder.dayDay.setText(ccsDayTool.getDayDay());
//
//                        holder.activeShow.setText(ccsDayTool.getActiveShow());
//
//
//                        holder.editDayB.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                                UserCarryDate userCarryDate = new UserCarryDate();
//                                userCarryDate.setUserCarryDate(ccsDayTool.getDayDate());
//
//
//                                String UserSelectedDayDate = holder.dayDate.getText().toString();
//                                HashMap userSelectedDayDate = new HashMap();
//
//                                userSelectedDayDate.put("userSelectedDayDate", UserSelectedDayDate);
//
//                                CCSMainDB.child("Users").child("Management")
//                                        .child(currentUserId)
//                                        .updateChildren(userSelectedDayDate);
//
//
//                                {
//                                    Intent editDayIntent = new Intent(EditDayActivity.this, EditDayActivity.class);
//                                    editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    editDayIntent.putExtra("UserCarryDayDate", userCarryDate.getUserCarryDate());
//                                    startActivity(editDayIntent);
//                                    finish();
//
//                                };
//                            }
//                        });
//                    }
//
//
//
//
//
//
//
//
//                    @NonNull
//                    @Override
//                    public EditDayActivity.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daycard, parent, false);
//                        EditDayActivity.LocationViewHolder viewHolderHolder = new LocationViewHolder(view);
//                        return viewHolderHolder;
//
//
//                    }
//                };
//            adapter.startListening();
//            locationList.setAdapter(adapter);

    }
//    private void SetLocationsAdapter() {
//
//
//        locationRef = FirebaseDatabase.getInstance().getReference()
//                .child("Shows")
//                .child(UserCarryActiveShow)
//                .child("Days")
//                .child(UserCarryDayDate)
//                ////.child(editTextEditDayDate.getText().toString())
//                .child("Locations")
//        ;
//
//        FirebaseRecyclerOptions<GDLocationTool> options =
//                new FirebaseRecyclerOptions.Builder<GDLocationTool>()
//                        .setQuery(locationRef, GDLocationTool.class)
//                        .build();
//
//        final FirebaseRecyclerAdapter<GDLocationTool, LocationViewHolder> locationsAdapter =
//                new FirebaseRecyclerAdapter<GDLocationTool, EditDayActivity.LocationViewHolder>(options) {
//                    @Override
//                    protected void onBindViewHolder(@NonNull  LocationViewHolder locationViewHolder, int i, @NonNull final GDLocationTool gdLocationTool) {
//
//                        locationViewHolder.locationName.setText(gdLocationTool.getLocationName());
//                        final String locationName = locationViewHolder.locationName.getText().toString();
//                        locationViewHolder.locationAddress.setText(gdLocationTool.getLocationAddress());
//
//                        locationViewHolder.showLocationDetailsB.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//
//
//                                {
//                                    Intent editDayIntent = new Intent(EditDayActivity.this, AddLocationActivity.class);
//                                    editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    editDayIntent.putExtra("UserCarryLocationName", gdLocationTool.getLocationName());
//                                    editDayIntent.putExtra("UserCarryDayDate", editTextEditDayDate.getText().toString());
//                                    startActivity(editDayIntent);
//                                    finish();
//                                }
//                            }
//                        });
//
//                        locationViewHolder.addLocationToDayB.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                                CCSMainDB.child("Shows")
//                                        .child(activeShow)
//                                        .child(editTextEditDayDate.getText().toString().trim())
//                                        .child("Locations")
//                                        .child(locationName)
//                                        .setValue(gdLocationTool)
//                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Void> task) {
//
//                                            }
//                                        });
//
//                            }
//                        });
//
//
//                    }
//
//                    @NonNull
//                    @Override
//                    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationcard_simple, parent, false);
//                        LocationViewHolder viewHolderHolder = new LocationViewHolder(view);
//                        return viewHolderHolder;
//                    }
//                };
//
////                new FirebaseRecyclerAdapter<GDLocationTool, EditDayActivity.LocationViewHolder>(options) {
////                    @Override
////                    protected void onBindViewHolder(@NonNull LocationViewHolder locationViewHolder, int i, @NonNull GDLocationTool gdLocationTool) {
////
////                        locationViewHolder.locationName.setText(gdLocationTool.getLocationName());
////                    }
////
////                    @Override
////                    protected void onBindViewHolder(@NonNull final MainActivity.DayViewHolderHolder holder, final int position, @NonNull final CCSDayTool ccsDayTool) {
////                        holder.dayDate.setText(ccsDayTool.getDayDate());
////
////
////
////                        holder.dayDay.setText(ccsDayTool.getDayDay());
////
////                        holder.activeShow.setText(ccsDayTool.getActiveShow());
////
////
////                        holder.editDayB.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////
////                                UserCarryDate userCarryDate = new UserCarryDate();
////                                userCarryDate.setUserCarryDate(ccsDayTool.getDayDate());
////
////
////                                String UserSelectedDayDate = holder.dayDate.getText().toString();
////                                HashMap userSelectedDayDate = new HashMap();
////
////                                userSelectedDayDate.put("userSelectedDayDate", UserSelectedDayDate);
////
////                                CCSMainDB.child("Users").child("Management")
////                                        .child(currentUserId)
////                                        .updateChildren(userSelectedDayDate);
////
////
////                                {
////                                    Intent editDayIntent = new Intent(EditDayActivity.this, EditDayActivity.class);
////                                    editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                                    editDayIntent.putExtra("UserCarryDayDate", userCarryDate.getUserCarryDate());
////                                    startActivity(editDayIntent);
////                                    finish();
////
////                                };
////                            }
////                        });
////                    }
////
////
////
////
////
////
////
////
////                    @NonNull
////                    @Override
////                    public EditDayActivity.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daycard, parent, false);
////                        EditDayActivity.LocationViewHolder viewHolderHolder = new LocationViewHolder(view);
////                        return viewHolderHolder;
////
////
////                    }
////                };
//        locationsAdapter.startListening();
//        locationList.setAdapter(locationsAdapter);
//
//    }

    private void CheckUserExistence() {
        final String current_user_id = mAuth.getCurrentUser().getUid();

        CCSMainDB.child("Users")
                .child("Management")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (!dataSnapshot.hasChild(current_user_id))
                        {
                            SendUserToSignUpProfileActivity();
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
    private void SendUserToSignInActivity()
    {
        Intent loginIntent = new Intent(EditDayActivity.this, SignInActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();


    }

    private void SendUserToSignUpProfileActivity() {

        Intent signUpProfileIntent = new Intent(EditDayActivity.this, SignUpProfileActivity.class);
        signUpProfileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signUpProfileIntent);
        finish();

    }
    private void CollectDayInfo() {

        ArrayAdapter<CharSequence> daysOfTheWeekAdapter = ArrayAdapter
                .createFromResource(getApplicationContext(),
                        R.array.days_of_the_week_array,
                        android.R.layout.simple_spinner_item);
        daysOfTheWeekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectDaySpinner.setAdapter(daysOfTheWeekAdapter);
        selectDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayOfTheWeekSpinnerResult = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initDaysOfWeekList() {

        dayOfWeekArrayList = new ArrayList<>();
        dayOfWeekArrayList.add(new DayOfWeek("Sunday"));
        dayOfWeekArrayList.add(new DayOfWeek("Monday"));
        dayOfWeekArrayList.add(new DayOfWeek("Tuesday"));
        dayOfWeekArrayList.add(new DayOfWeek("Wednesday"));
        dayOfWeekArrayList.add(new DayOfWeek("Thursday"));
        dayOfWeekArrayList.add(new DayOfWeek("Friday"));
        dayOfWeekArrayList.add(new DayOfWeek("Saturday"));


    }

    private void SendUserToCreateJoinShow() {

        Intent createJoinShowIntent = new Intent(EditDayActivity.this, CreateJoinShowActivity.class);
        createJoinShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(createJoinShowIntent);
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.manageScenesB:
               // SaveDayInformation();
                startActivity(new Intent(this, MainActivity.class));


                case R.id.manageLocationsB:

                    SendUserToManageLocationsActivity();
                    break;
            case R.id.manageStaffB:
                Intent manageStaffIntent = new Intent(EditDayActivity.this, ManageStaffActivity.class);

                manageStaffIntent.putExtra("UserCarryDayDate", editTextEditDayDate.getText().toString().trim() );
                manageStaffIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow.trim());
                manageStaffIntent.putExtra("UserCarryDepartment", UserCarryDepartment);
                manageStaffIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(manageStaffIntent);
                finish();
                break;

            case R.id.createDayB:
                SaveDayInformation();
                Intent updateDayIntent = new Intent(EditDayActivity.this, ManageDaysActivity.class);
                updateDayIntent.putExtra("UserCarryDayDate",  editTextEditDayDate.getText().toString().trim());
                updateDayIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow.trim());
                updateDayIntent.putExtra("UserCarryDepartment", UserCarryDepartment);


                startActivity(new Intent(this, ManageDaysActivity.class));
                //manageStaffIntent.putExtra("UserCarryDayDate", onBindUserSelectedDayDateSend );

                break;




        }
    }

    private void SendUserToManageLocationsActivity() {

        Intent addLocationIntent = new Intent(EditDayActivity.this, ManageLocationsActivity.class);
        addLocationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        addLocationIntent.putExtra("UserCarryDayDate", editTextEditDayDate.getText().toString().trim() );
        //addLocationIntent.putExtra("UserCarryDayDate", UserCarryDayDate.trim());
        addLocationIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow.trim());
        addLocationIntent.putExtra("UserCarryDepartment", UserCarryDepartment);
        //addLocationIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow.trim());
        startActivity(addLocationIntent);


    }

    private void SendUserToAddCreateLocation() {

        Intent addLocationIntent = new Intent(EditDayActivity.this, AddLocationActivity.class);
        addLocationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        addLocationIntent.putExtra("UserCarryDayDate", onBindUserSelectedDayDateSend );
        startActivity(addLocationIntent);

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

        //SetLocationsAdapter();
    }
    @Override
    public void onStop() {
        super.onStop();

    }
    @Override
    protected void onResume() {
        super.onResume();

    }
}