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
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.corstan__cc__managment.Model.GDDayTool;
import com.example.corstan__cc__managment.Model.GDUserTool;
import com.example.corstan__cc__managment.Model.DayOfWeek;
import com.example.corstan__cc__managment.Model.DaysOfWeekAdapter;
import com.example.corstan__cc__managment.Model.GDLocationTool;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreateDayActivity extends AppCompatActivity implements View.OnClickListener {


    Uri uriProfileImage;

    //Edit Texts
    EditText editTextDayDay, editTextDayDate,
            editTextDayAddress, editTextDayCity, editTextDayShow, editTextDaySateInput;

    TextView textViewShowShow;
    //Button
    Button createDayB, addLocationB, manageStaffB, editLocationB;

    private Toolbar mToolbar;

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

    private String currentUserId, activeShow;

    private ArrayList<DayOfWeek> dayOfWeekArrayList;
    private DaysOfWeekAdapter daysOfWeekAdapter;

    RecyclerView locationList, staffList;

    String onBindUserSelectedDayDateReceive, onBindUserSelectedDayDateSend;
    private String dayOfTheWeekSpinnerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_day);
        GDDayTool GDDayTool = new GDDayTool();


//        CCSMainDB = FirebaseDatabase.getInstance().getReference();
//        activeShow = "Charmed S2";
//
//        dayRef = FirebaseDatabase.getInstance().getReference()
//                .child("Shows")
//                .child(activeShow)
//                .child("Days");
////        .child("Day Information");
//
//
//
//
//        mAuth = FirebaseAuth.getInstance();
//        currentUserId = mAuth.getCurrentUser().getUid();
//
//
//
////        locationList = findViewById(R.id.editDayLocatcionRecycler);
////        locationList.setHasFixedSize(true);
////        locationList.setLayoutManager(new LinearLayoutManager(this));
//        staffList = findViewById(R.id.editDayStaffRecycler);
//        staffList.setHasFixedSize(true);
//        staffList.setLayoutManager(new LinearLayoutManager(this));
//
//        activeShow = "Charmed S2";
//        //createDatabaseForm();
//        GDUserTool GDUserTool = new GDUserTool();
//
//        //Data Transfer Int's
////        day_Date_Data = getIntent().getExtras().get("day_Date_Data_Send").toString();
////        day_ActiveShow_Data_Send = getIntent().getExtras().get("day_ActiveShow_Data_Send").toString();
////        day_Day_Day_Data_Send = getIntent().getExtras().get("day_Day_Day_Data_Send").toString();
//
//        // String for DB Child updates
//
//
//        //Set Variables
//        //Text Views
////        editTextDayShow = findViewById(R.id.editTextDayShow);
////
////        //Edit Texts
////        editTextDayDay = findViewById(R.id.editTextDayDay);
//        editTextDayDate = findViewById(R.id.editTextEditDayDate);
//        editTextDayDate.setText(GDDayTool.getDayDate());
////        editTextDayAddress = findViewById(R.id.editTextDayAddress);
////        editTextDayCity = findViewById(R.id.editTextDayCity);
////
////        //Data Transfer Set Texts
////        editTextDayShow.setText(day_ActiveShow_Data_Send);
////        editTextDayDate.setText(day_Date_Data);
////        editTextDayDay.setText(day_Day_Day_Data_Send);
//
//        //Spinners
//        selectDaySpinner = findViewById(R.id.selectDaySpinner);
//
//
//        //Button
//        createDayB = findViewById(R.id.createDayB);
//        addLocationB = findViewById(R.id.addLocationB);
//        manageStaffB = findViewById(R.id.manageStaff);
//
//        createDayB.setOnClickListener(this);
//        addLocationB.setOnClickListener(this);
//        manageStaffB.setOnClickListener(this);
//
//
//        //Uses onItemSelected @overide
//      mToolbar = findViewById(R.id.main_page_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Create Day");
//
//        initDaysOfWeekList();
//        daysOfWeekAdapter = new DaysOfWeekAdapter(this, dayOfWeekArrayList);
//
//        currentUserDepartmentDbRef = FirebaseDatabase.getInstance().getReference()
//                .child("Users")
//                .child("Management")
//                .child(currentUserId)
//                .child("userPrimaryDepartment");
//
//        currentUserDepartmentDbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    currentUserDepartment = dataSnapshot.getValue().toString();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        CollectDayInfo();
//        SetLocationsAdapter();
//        //SetStaffAdapter();
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        int id = item.getItemId();
//        if(id == android.R.id.home){
//            SendUserToMainActivity();
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
//    private void SendUserToMainActivity() {
//        Intent intent = new Intent(CreateDayActivity.this, MainActivity.class);
//        startActivity(intent);
//    }
//    private void SetStaffAdapter() {
//
//        //Get currentUserId Department
//
//
//
//        locationRef = FirebaseDatabase.getInstance().getReference()
//                .child("Shows")
//                .child(activeShow)
//                .child("Days")
//                .child(editTextDayDate.getText().toString())
//                .child("Day");
//
////????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
////                     EditDay Recycler
//        FirebaseRecyclerOptions<GDLocationTool> options =
//                new FirebaseRecyclerOptions.Builder<GDLocationTool>()
//                        .setQuery(locationRef, GDLocationTool.class)
//                        .build();
//
//        final FirebaseRecyclerAdapter<GDLocationTool, EditDayActivity.LocationViewHolder> adapter =
//                new FirebaseRecyclerAdapter<GDLocationTool, EditDayActivity.LocationViewHolder>(options) {
//                    @Override
//                    protected void onBindViewHolder(@NonNull final EditDayActivity.LocationViewHolder locationViewHolder, int i, @NonNull final GDLocationTool gdLocationTool) {
//
//                        locationViewHolder.locationName.setText(gdLocationTool.getLocationName());
//                        locationViewHolder.locationAddress.setText((gdLocationTool.getLocationAddress() + ", " + gdLocationTool.getLocationCity()));
//
//
//                        locationViewHolder.editLocationB.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//
//                                {
//                                    Intent editDayIntent = new Intent(CreateDayActivity.this, AddLocationActivity.class);
//                                    editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    editDayIntent.putExtra("UserCarryLocationName", gdLocationTool.getLocationName());
//                                    editDayIntent.putExtra("UserCarryDayDate", editTextDayDate.getText().toString());
//                                    startActivity(editDayIntent);
//                                    finish();
//
//                                };
//                            }
//                        });
//
//
//
//
//                    }
//
//
//                    @NonNull
//                    @Override
//                    public EditDayActivity.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationcard_simple, parent, false);
//                        EditDayActivity.LocationViewHolder viewHolderHolder = new EditDayActivity.LocationViewHolder(view);
//                        return viewHolderHolder;
//
//                    }
//                };
//
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
//    //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//
//    private void SetLocationsAdapter() {
//
//        locationRef = FirebaseDatabase.getInstance().getReference()
//                .child("Shows")
//                .child(activeShow)
//                .child("Days")
//                .child(editTextDayDate.getText().toString())
//                .child("Locations");
//
//
//        FirebaseRecyclerOptions<GDLocationTool> options =
//                new FirebaseRecyclerOptions.Builder<GDLocationTool>()
//                        .setQuery(locationRef, GDLocationTool.class)
//                        .build();
//
//        final FirebaseRecyclerAdapter<GDLocationTool, EditDayActivity.LocationViewHolder> adapter =
//                new FirebaseRecyclerAdapter<GDLocationTool, EditDayActivity.LocationViewHolder>(options) {
//                    @Override
//                    protected void onBindViewHolder(@NonNull final EditDayActivity.LocationViewHolder locationViewHolder, int i, @NonNull final GDLocationTool gdLocationTool) {
//
//                        locationViewHolder.locationName.setText(gdLocationTool.getLocationName());
//                        locationViewHolder.locationAddress.setText((gdLocationTool.getLocationAddress() + ", " + gdLocationTool.getLocationCity()));
//
//
//                        locationViewHolder.editLocationB.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//
//                                {
//                                    Intent editDayIntent = new Intent(CreateDayActivity.this, AddLocationActivity.class);
//                                    editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    editDayIntent.putExtra("UserCarryLocationName", gdLocationTool.getLocationName());
//                                    editDayIntent.putExtra("UserCarryDayDate", editTextDayDate.getText().toString());
//                                    startActivity(editDayIntent);
//                                    finish();
//
//                                };
//                            }
//                        });
//
//
//
//
//                    }
//
//
//                    @NonNull
//                    @Override
//                    public EditDayActivity.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationcard_simple, parent, false);
//                        EditDayActivity.LocationViewHolder viewHolderHolder = new EditDayActivity.LocationViewHolder(view);
//                        return viewHolderHolder;
//
//                    }
//                };
//
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
//
//    //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//    public static class LocationViewHolder extends RecyclerView.ViewHolder {
//
//        TextView locationName, locationAddress,locationCity
//                ,locationCrewPark,locationCrewParkCity, cbLocationIntExt;
//
//
//
//        Button editLocationB;
//
//        public LocationViewHolder(@NonNull View itemView) {
//
//            super(itemView);
//
//            locationName = itemView.findViewById(R.id.locationCardLocationName);
//            locationAddress= itemView.findViewById(R.id.locationCardLocationAddress);
////            locationCity= itemView.findViewById(R.id.locationCard);
////            locationCrewPark= itemView.findViewById(R.id.locationCardLocationC);
////            locationCrewParkCity= itemView.findViewById(R.id.locationCardLocationName);
//
//            cbLocationIntExt= itemView.findViewById(R.id.locationCardLocationIntExt);
//
//
//            //dayDate = itemView.findViewById(R.id.dayCardDayDate);
//            //dayDay = itemView.findViewById(R.id.dayCardDayDay);
//
//            editLocationB = itemView.findViewById(R.id.editLocationB);
//            //activeShow = itemView.findViewById(R.id.dayCardActiveShow);
//
//        }
//    }
//    private void CheckUserExistence() {
//        final String current_user_id = mAuth.getCurrentUser().getUid();
//
//        CCSMainDB.child("Users")
//                .child("Management")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        if (!dataSnapshot.hasChild(current_user_id))
//                        {
//                            SendUserToSignUpProfileActivity();
//                            finish();
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//    }
//    //    public static class DayViewHolderHolder extends RecyclerView.ViewHolder {
////
////        TextView dayDate, dayDay, activeShow;
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
////            editDayB = itemView.findViewById(R.id.editDayB);
////            activeShow = itemView.findViewById(R.id.dayCardActiveShow);
////
////        }
////    }
//    private void SendUserToSignInActivity()
//    {
//        Intent loginIntent = new Intent(CreateDayActivity.this, SignInActivity.class);
//        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(loginIntent);
//        finish();
//
//
//    }
//
//    private void SendUserToSignUpProfileActivity() {
//
//        Intent signUpProfileIntent = new Intent(CreateDayActivity.this, SignUpProfileActivity.class);
//        signUpProfileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(signUpProfileIntent);
//        finish();
//
//    }
//    private void CollectDayInfo() {
//
//        ArrayAdapter<CharSequence> daysOfTheWeekAdapter = ArrayAdapter
//                .createFromResource(getApplicationContext(),
//                        R.array.days_of_the_week_array,
//                        android.R.layout.simple_spinner_item);
//        daysOfTheWeekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        selectDaySpinner.setAdapter(daysOfTheWeekAdapter);
//        selectDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                dayOfTheWeekSpinnerResult = parent.getItemAtPosition(position).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//    }
//    private void initDaysOfWeekList() {
//
//        dayOfWeekArrayList = new ArrayList<>();
//        dayOfWeekArrayList.add(new DayOfWeek("Sunday"));
//        dayOfWeekArrayList.add(new DayOfWeek("Monday"));
//        dayOfWeekArrayList.add(new DayOfWeek("Tuesday"));
//        dayOfWeekArrayList.add(new DayOfWeek("Wednesday"));
//        dayOfWeekArrayList.add(new DayOfWeek("Thursday"));
//        dayOfWeekArrayList.add(new DayOfWeek("Friday"));
//        dayOfWeekArrayList.add(new DayOfWeek("Saturday"));
//
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.createDayB:
//                startActivity(new Intent(this, MainActivity.class));
//                break;
//
//            case R.id.addLocationB:
//
//                Intent addLocationIntent = new Intent(CreateDayActivity.this, AddLocationActivity.class);
//                addLocationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                addLocationIntent.putExtra("UserCarryDayDate", onBindUserSelectedDayDateSend );
//                startActivity(addLocationIntent);
//                finish();
//
//                break;
//            case R.id.manageStaff:
//                Intent manageStaffIntent = new Intent(CreateDayActivity.this, ManageStaffActivity.class);
//                manageStaffIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                manageStaffIntent.putExtra("UserCarryDayDate", onBindUserSelectedDayDateSend );
//                startActivity(manageStaffIntent);
//                finish();
//
//
//
//                break;
//        }
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//
//
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//
//        if(currentUser == null) {
//            SendUserToSignInActivity();
//        }
//        else {
//            CheckUserExistence();
//        }
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//    }
//
    }


    private void SendUserToSignInActivity() {
        Intent loginIntent = new Intent(CreateDayActivity.this, SignInActivity.class);
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

                        if (!dataSnapshot.hasChild(current_user_id)) {
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


    @Override
    public void onClick(View v) {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            SendUserToSignInActivity();
        } else {
            CheckUserExistence();
        }
    }
}





