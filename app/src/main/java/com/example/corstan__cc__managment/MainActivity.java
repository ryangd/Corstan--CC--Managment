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
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corstan__cc__managment.Model.GDDayTool;
import com.example.corstan__cc__managment.Model.GDLocationTool;
import com.example.corstan__cc__managment.Model.GDUserTool;
import com.example.corstan__cc__managment.Model.UserCarryDate;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private Button profileB, availabilityB, paperworkB
            , scheduleB, settingsB;


    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView dayList, locationList;
    private Toolbar mToolbar;

    private LinearLayoutManager linearLayoutManager;

    private CircleImageView NavProfileImage;
    private TextView NavProfileFullName, NavActiveShow, NavUserPosition, dayDate;
    private Button viewShootingScheduleB;

    private FirebaseAuth mAuth;

    private DatabaseReference CCSMainDB, dayRef, locationRef;

    private ImageButton CreateDayButton;

    public GDDayTool gdDayTool;

//    FirebaseRecyclerOptions<CCSUserTool> userQuery;
//    FirebaseRecyclerOptions<CCSDayTool> dayQuery;
//    FirebaseRecyclerAdapter<CCSUserTool, StaffViewHolder> staffAdapter;
//    FirebaseRecyclerAdapter<CCSDayTool, DayViewHolder> dayAdapter;

    private String newUserShow, activeShow;

    private String onBindUserSelectedDayDate;

    private EditText editTextEditDayDate;

    private TextView editTextEditDayCrewCall, editTextEditDayLunchTime
            , editTextEditDaySunUp, editTextEditDaySunUpDn
            ,editTextEditDayDayOfDay1, editTextEditDayDayOfDay2
            , editTextEditDayEpisodeName, textViewCurrentDay, textViewEpisodeName, textViewDayOfDay
            , textViewCrewCallTime, textViewLunchTime, textViewSunUp, textViewSunUpDn;

    String currentUserId, onBindUserSelectedDayDateSend
            , onBindUserSelectedDayDateReceive, UserCarryDayDate
            , UserCarryLocationName, UserCarryDepartment,



       //dayDate,
        dayEpisodeName,
    dayDayOfDay1,
    dayDayOfDay2,
    dayCrewCall,
    dayLunchTime,
    daySunUp,
    daySunUpDn;
    public String UserCarryActiveShow;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_v1);

        //Date today;

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

//        if (todayDate.equals(onBindDate)) {
//
//
//            dayViewHolderV1.dayCardToday.setBackgroundColor(getResources().getColor(R.color.dayCardToday));
//
//        }

        //GDDayTool gdDayTool = new GDDayTool();
        GDUserTool gdUserTool = new GDUserTool();

        //????????????????????????????????????????????????????????????????????????????????????????????????????????
//                         Carry Intents                                                                \\
//????????????????????????????????????????????????????????????????????????????????????????????????????????
//        editDayIntent.putExtra("UserCarryDayDate", UserCarryDayDate.trim());
//        editDayIntent.putExtra("UserCarryActiveShow", UserCarryActiveSHow.trim());
//        editDayIntent.putExtra("UserCarryLocation", UserCarryLocationName.trim());


//????????????????????????????????????????????????????????????????????????????????????????????????????????
        //textViewSelectedDate = findViewById(R.id.textViewSelectedDate);
//        if (Objects.requireNonNull(getIntent().getExtras()).getString("UserCarryDayDate") != null) {
//            UserCarryDayDate = getIntent().getExtras().getString("UserCarryDayDate");
        //textViewSelectedDate.setText(UserCarryDayDate);
//        }
//        if (Objects.requireNonNull(getIntent().getExtras()).getString("UserCarryActiveSHow") != null) {
//            UserCarryActiveSHow = getIntent().getExtras().getString("UserCarryDayDateManageDay");
//        }
//        if (Objects.requireNonNull(getIntent().getExtras()).getString("UserCarryLocationName") != null) {
//            UserCarryLocationName = getIntent().getExtras().getString("UserCarryDayDateManageDay");
//        }


        CCSMainDB = FirebaseDatabase.getInstance().getReference();
        //activeShow = gdUserTool.getActiveShow();


//        .child("Day Information");
//        Query dayQuery = dayRef.orderByKey();


        editTextEditDayDate = findViewById(R.id.editTextEditDayDate);
        editTextEditDayDate.setText(todayDateOutput);

        if (todayDate.equals(textViewDateOutput)) {

            //textViewCurrentDay.setBackgroundColor(Color.GREEN);

        }


        textViewEpisodeName = findViewById(R.id.textViewEpisodeName);
        textViewDayOfDay = findViewById(R.id.textViewDayOfDay);
        editTextEditDayDayOfDay2 = findViewById(R.id.editTextEditDayDayOfDay2);
        textViewCrewCallTime = findViewById(R.id.textViewCrewCallTime);
        textViewLunchTime = findViewById(R.id.textViewLunchTime);
        textViewSunUp = findViewById(R.id.textViewSunUp);
        textViewSunUpDn = findViewById(R.id.textViewSunUpDn);

        final String textEditDayDate = editTextEditDayDate.getText().toString();


        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();


        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");


        viewShootingScheduleB = findViewById(R.id.viewShootingScheduleB);
        CreateDayButton = findViewById(R.id.create_new_day_button);

        locationList = findViewById(R.id.editDayLocationRecycler);
        locationList.setHasFixedSize(true);
        locationList.setLayoutManager(new LinearLayoutManager(this));


        drawerLayout = findViewById(R.id.drawable_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_closed);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView = findViewById(R.id.navigation_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        NavProfileImage = navView.findViewById(R.id.nav_circleProfilePhoto);
        NavProfileFullName = navView.findViewById(R.id.nav_user_full_name);
        NavActiveShow = navView.findViewById(R.id.nav_user_active_show);
        NavUserPosition = navView.findViewById(R.id.nav_user_position);


//        dayList = findViewById(R.id.all_days_list);
//        dayList.setHasFixedSize(true);
//        dayList.setLayoutManager(new LinearLayoutManager(this));


//        dayQuery = new FirebaseRecyclerOptions.Builder<CCSDayTool>()
//        .setQuery(CCSMainDB.child("Shows").child(activeShow).child("Days"), CCSDayTool.class).build();

//        userQuery = new FirebaseRecyclerOptions.Builder<CCSUserTool>()
//                .setQuery(CCSMainDB.child("users"), CCSUserTool.class).build();

//        dayAdapter = new FirebaseRecyclerAdapter<CCSDayTool, DayViewHolder>(dayQuery) {
//            @Override
//            protected void onBindViewHolder(@NonNull DayViewHolder dayViewHolder, int i, @NonNull CCSDayTool ccsDayTool) {
//
//
//                if (ccsDayTool.getDayDay() != null)
//                dayViewHolder.dayDay.setText(ccsDayTool.getDayDay());
//                dayDate.setText(ccsDayTool.getDayDate());
//            }
//
////            @Override
////            protected void onBindViewHolder(@NonNull DayViewHolder holder, int i, @NonNull CCSUserTool profile) {
////                holder.dayDay.setText(Day.get);
////                holder.dayDate.setText(profile.getLastName());
////
////            }
//
//            @NonNull
//            @Override
//            public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daycard, parent, false);
//                DayViewHolder viewHolder = new DayViewHolder(view);
//
//
//                return new DayViewHolder(view);
//            }
//        };
//


//        dayAdapter.startListening();
//        dayList.setAdapter(dayAdapter);


//        staffAdapter = new FirebaseRecyclerAdapter<CCSUserTool, StaffViewHolder>(userQuery) {
//            @Override
//            protected void onBindViewHolder(@NonNull StaffViewHolder holder, int i, @NonNull CCSUserTool profile) {
//                holder.dayDay.setText(profile.getFirstName());
//                holder.lastName.setText(profile.getLastName());
//
//            }
//
//            @NonNull
//            @Override
//            public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
//                return new StaffViewHolder(view);
//            }
//        };
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        dayList.setLayoutManager(linearLayoutManager);
//        staffAdapter.startListening();
//        dayList.setAdapter(staffAdapter);



        DisplayAllDays();

        CCSMainDB.child("Users")
                .child("Management")
                .child(currentUserId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if (dataSnapshot.exists()) {

                            dataSnapshot.getValue(GDUserTool.class);

                            if (!dataSnapshot.hasChild("activeShow")) {


                                SendUserToCreateJoinShowActivity();
                            }


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
                                String navActiveShow = dataSnapshot.child("activeShow").getValue().toString();
                                NavActiveShow.setText(navActiveShow);
                                UserCarryActiveShow = (navActiveShow);


                            }
                            if (dataSnapshot.hasChild("userPrimaryPosition")) {
                                String navUserPosition = dataSnapshot.child("userPrimaryPosition").getValue().toString();
                                NavUserPosition.setText(dataSnapshot.child("userPrimaryDepartment").getValue().toString() + " - " + navUserPosition);
                                UserCarryDepartment = dataSnapshot.child("userPrimaryDepartment").getValue().toString();


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
        UserCarryActiveShow = NavActiveShow.getText().toString().trim();
        //UserCarryDepartment = NavUserPosition.getText().toString().trim();

        dayRef = FirebaseDatabase.getInstance().getReference()
                .child("Shows")
                .child(UserCarryActiveShow)
                .child("Days");





        CreateDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendUserToManageDaysActivity();

            }


        });
        viewShootingScheduleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendUserToManageDaysActivity();

            }


        });



        CCSMainDB.child("Shows")
                .child(editTextEditDayDate.getText().toString())
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
                            textViewEpisodeName.setText(dataSnapshot.child("dayEpisodeName").getValue().toString());
                        }
                        if (dataSnapshot.hasChild("dayCrewCall")) {
                            textViewCrewCallTime.setText(dataSnapshot.child("dayCrewCall").getValue().toString());
                        }
                        if (dataSnapshot.hasChild("dayDayOfDay1")) {
                            textViewDayOfDay.setText(dataSnapshot.child("dayDayOfDay1").getValue() + " of " + dataSnapshot.child("dayDayOfDay2"));
                        }
//                        if (dataSnapshot.hasChild("dayDayOfDay2")) {
//                            editTextEditDayDayOfDay2.setText(dataSnapshot.child("dayDayOfDay2").getValue().toString());
//                        }
                        if (dataSnapshot.hasChild("dayLunchTime")) {
                            textViewLunchTime.setText(dataSnapshot.child("dayLunchTime").getValue().toString());
                        }
                        if (dataSnapshot.hasChild("daySunUp")) {
                            textViewSunUp.setText(dataSnapshot.child("daySunUp").getValue().toString());
                        }
                        if (dataSnapshot.hasChild("daySunUpDn")) {
                            textViewSunUpDn.setText(dataSnapshot.child("daySunUpDn").getValue().toString());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



        SetLocationsAdapter();
//        textViewEditDayEpisodeName.setText(gdDayTool.getDayEpisodeName().trim());
    }

    private void SetLocationsAdapter() {

        locationRef = FirebaseDatabase.getInstance().getReference()
                .child("Shows")
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

        final FirebaseRecyclerAdapter<GDLocationTool, ManageLocationsActivity.LocationViewHolder> adapter =
                new FirebaseRecyclerAdapter<GDLocationTool, ManageLocationsActivity.LocationViewHolder>(options) {






                    @Override
                    protected void onBindViewHolder(@NonNull ManageLocationsActivity.LocationViewHolder locationViewHolder, int i, @NonNull final GDLocationTool gdLocationTool) {




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
                                    Intent editDayIntent = new Intent(MainActivity.this, AddLocationActivity.class);
                                    editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    editDayIntent.putExtra("UserCarryLocationName", gdLocationTool.getLocationName());
                                    editDayIntent.putExtra("UserCarryDayDate", editTextEditDayDate.getText().toString().trim());
                                    editDayIntent.putExtra("UserCarryActiveSHow", NavActiveShow.getText().toString().trim());

                                    startActivity(editDayIntent);
                                    finish();
                                }
                            }
                        });

                        locationViewHolder.addLocationToDayB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                CCSMainDB.child("Shows")
                                        .child(UserCarryActiveShow)
                                        .child("Days")
                                        .child(editTextEditDayDate.getText().toString().trim())
                                        .child("Locations")
                                        .child("Locations")
                                        .child(locationName)
                                        .setValue(gdLocationTool)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                            }
                                        });

                            }
                        });


                    }

                    @NonNull
                    @Override
                    public ManageLocationsActivity.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationcard_simple, parent, false);
                        ManageLocationsActivity.LocationViewHolder viewHolderHolder = new ManageLocationsActivity.LocationViewHolder(view);
                        return viewHolderHolder;
                    }
                };

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
        adapter.startListening();
        locationList.setAdapter(adapter);

    }

    private void SendUserToManageDaysActivity() {

        UserCarryDayDate = editTextEditDayDate.getText().toString().trim();

        String onBindActiveShow = NavActiveShow.getText().toString().trim();
        String newActiveShow = onBindActiveShow.trim();


        Intent joinCreateShowIntent = new Intent(MainActivity.this, ManageDaysActivity.class);
        joinCreateShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        joinCreateShowIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow);
        joinCreateShowIntent.putExtra("UserCarryDayDate", UserCarryDayDate);
        joinCreateShowIntent.putExtra("UserCarryDepartment", UserCarryDepartment);
        startActivity(joinCreateShowIntent);
        finish();
    }





private class DayViewHolder1 extends RecyclerView.ViewHolder {
        private EditText dayCardDayDate;

        DayViewHolder1(View itemView) {
            super(itemView);
            //imageThumbtextView = itemView.findViewById(R.id.image_thumb_text_view);
            dayCardDayDate = itemView.findViewById(R.id.dayCardDayDate);
//            imageUrlTextView = itemView.findViewById(R.id.image_url_text_view);
//            descTextView = itemView.findViewById(R.id.desc_text_view);
        }

    }


    private void SendUserToCreateEditShow() {

        Intent createShowIntent = new Intent(MainActivity.this, TestActivity.class);
        createShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(createShowIntent);
        finish();
    }

    private void DisplayAllDays() {

//        FirebaseRecyclerAdapter<CCSDayTool, DayViewHolder1> firebaseRecyclerAdapter
//                = new FirebaseRecyclerAdapter<CCSDayTool, DayViewHolder1>(
//                        CCSDayTool.class,
//                R.layout.day_layout,
//                DayViewHolder1.class,
//                dayRef
//        ) {
//            @Override
//            protected void onBindViewHolder(@NonNull DayViewHolder1 dayViewHolder1, int i, @NonNull CCSDayTool ccsDayTool) {
//
//            }
//
//            @NonNull
//            @Override
//            public DayViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                return null;
//            }
//        };
//
//

    }



    private void UserMenuSelector (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.nav_create_day:
                Toast.makeText(this, "Create Day", Toast.LENGTH_SHORT).show();
                SendUserToCreateDayActivity();
                break;
            case R.id.nav_profile:
                SendUserToSignUpProfileActivity();
                break;
            case R.id.nav_home:
                SendUserTooTest();
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

    private void SendUserTooTest() {

        Intent editDayIntent = new Intent(MainActivity.this, TestActivity.class);
        editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //editDayIntent.putExtra("UserCarryDayDate", );
        startActivity(editDayIntent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

//        FirebaseRecyclerOptions<GDDayTool> options =
//                new FirebaseRecyclerOptions.Builder<GDDayTool>()
//                .setQuery(dayRef, GDDayTool.class)
//                .build();
//
//        final FirebaseRecyclerAdapter<GDDayTool, DayViewHolderHolder> adapter =
//                new FirebaseRecyclerAdapter<GDDayTool, DayViewHolderHolder>(options) {
//                    @Override
//                    protected void onBindViewHolder(@NonNull final DayViewHolderHolder holder, final int position, @NonNull final GDDayTool ccsDayTool) {
//                        holder.dayDate.setText(ccsDayTool.getDayDate());
//
//
//
//                        holder.dayDay.setText(ccsDayTool.getDayDate());
//
//                        holder.activeShow.setText(ccsDayTool.getShowName());
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
//                                    Intent editDayIntent = new Intent(MainActivity.this, EditDayActivity.class);
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
//                    public DayViewHolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daycard, parent, false);
//                        DayViewHolderHolder viewHolderHolder = new DayViewHolderHolder(view);
//                        return viewHolderHolder;
//
//
//                    }
//                };
//
//        adapter.startListening();
//
//        dayList.setAdapter(adapter);
        //staffirebaseRecyclerAdapter.startListening();


        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null) {
            SendUserToSignInActivity();
        }
        else {
            CheckUserExistence();
        }
    }

    private void SendUserToCreateJoinShowActivity(){

        Intent joinCreateShowIntent = new Intent(MainActivity.this, CreateJoinShowActivity.class);
        joinCreateShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //editDayIntent.putExtra("UserCarryDayDate", );
        startActivity(joinCreateShowIntent);
        finish();


    }

//    private void SetLocationsAdapter() {
//
//
//
////        if (getIntent().getExtras().getString("UserCarryDayDate") != null) {
////            onBindUserSelectedDayDateReceive = getIntent().getExtras().getString("UserCarryDayDate");
////
////            carryDayDate = onBindUserSelectedDayDateReceive;
////
////        }
//
////        onBindUserSelectedDayDateReceive  = getIntent().getExtras().getString("UserCarryDayDate");
////        CCSMainDB.child("Shows")
////                .child("Charmed S2")
////                .child(onBindUserSelectedDayDateReceive)
////                .child("Locations");
//
//
//
//        locationRef = FirebaseDatabase.getInstance().getReference()
//                .child("Shows")
//                .child(NavActiveShow.getText().toString().trim())
//                .child("Locations")
//                //.child(editTextEditDayDate.getText().toString())
//                .child("Locations");
//
//        FirebaseRecyclerOptions<GDLocationTool> options =
//                new FirebaseRecyclerOptions.Builder<GDLocationTool>()
//                        .setQuery(locationRef, GDLocationTool.class)
//                        .build();
//
//        final FirebaseRecyclerAdapter<GDLocationTool, ManageLocationsActivity.LocationViewHolder> adapter =
//                new FirebaseRecyclerAdapter<GDLocationTool, ManageLocationsActivity.LocationViewHolder>(options) {
//
//
//                    @Override
//                    protected void onBindViewHolder(@NonNull final ManageLocationsActivity.LocationViewHolder locationViewHolder, int i, @NonNull final GDLocationTool gdLocationTool) {
//
//
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
//                                    Intent editDayIntent = new Intent(MainActivity.this, AddLocationActivity.class);
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
//                                        .child(onBindUserSelectedDayDateReceive)
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
//                    public ManageLocationsActivity.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationcard_simple, parent, false);
//                       // ManageLocationsActivity.LocationViewHolder viewHolderHolder;
////                                = new ManageLocationsActivity.LocationViewHolder(view);
//                        return new  ManageLocationsActivity.LocationViewHolder(view);
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
//        adapter.startListening();
//        locationList.setAdapter(adapter);
//
//    }

    private void SendUserToEditDayActivity() {

        onBindUserSelectedDayDateSend = editTextEditDayDate.getText().toString();

        String onBindUserActiveShow = NavActiveShow.getText().toString();

        dayRef = CCSMainDB
                .child("Shows")
                .child(onBindUserActiveShow)
                .child("Days");

        if (dayRef.child(onBindUserSelectedDayDateSend).toString().equals(onBindUserSelectedDayDateSend)) {

            GDDayTool gdDayTool;

            dayRef.child(onBindUserSelectedDayDateSend)
                    .child("dayDate")
                    .setValue(onBindUserSelectedDayDateSend);


        }

        Intent editDayIntent = new Intent(MainActivity.this, EditDayActivity.class);
        editDayIntent.putExtra("UserCarryDayDate", onBindUserSelectedDayDateSend);
        editDayIntent.putExtra("UserCarryShowName", onBindUserActiveShow);
        editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(editDayIntent);
        finish();
    }

//    public static class DayViewHolderHolder extends RecyclerView.ViewHolder {
//
////        TextView dayDate, dayDay, activeShow, dayCardToday;
////
////        Button editDayB;
////
////        public DayViewHolderHolder(@NonNull View itemView) {
////
////            super(itemView);
////
////            dayDate = itemView.findViewById(R.id.dayCardDayDate);
////            dayDay = itemView.findViewById(R.id.dayCardDayDay);
////
////            //editDayB = itemView.findViewById(R.id.locationCardGoToDay);
////            dayCardToday = itemView.findViewById(R.id.dayCardToday);
////
////        }
//    }

    @Override
    public void onStop() {
        super.onStop();
//        if (dayAdapter != null)
//            dayAdapter.stopListening();
    }
    @Override
    protected void onResume() {
        super.onResume();
//        if (dayAdapter != null)
//            dayAdapter.startListening();
    }


    private void SendUserToCreateJoinActivity() {

        Intent createJoinShowIntent = new Intent(MainActivity.this, CreateJoinShowActivity.class);
        createJoinShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(createJoinShowIntent);
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
                    SendUserToSignUpProfileActivity();
                    finish();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SendUserToSignUpProfileActivity() {

        Intent signUpProfileIntent = new Intent(MainActivity.this, SignUpProfileActivity.class);
        signUpProfileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signUpProfileIntent);
        finish();

    }




    private void SendUserToSignInActivity()
    {
        Intent loginIntent = new Intent(MainActivity.this, SignInActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void SendUserToCreateDayActivity() {

        Intent createDayIntent = new Intent(MainActivity.this, CreateDayActivity.class);
        createDayIntent.putExtra("UserCarryDayDate", UserCarryDayDate.trim());
        createDayIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow.trim());
        createDayIntent.putExtra("UserCarryLocation", UserCarryLocationName.trim());
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
