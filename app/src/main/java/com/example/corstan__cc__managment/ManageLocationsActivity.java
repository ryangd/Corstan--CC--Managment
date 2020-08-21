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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.corstan__cc__managment.Model.DayOfWeek;
import com.example.corstan__cc__managment.Model.DaysOfWeekAdapter;
import com.example.corstan__cc__managment.Model.GDDayTool;
import com.example.corstan__cc__managment.Model.GDLocationTool;
import com.example.corstan__cc__managment.Model.GDUserTool;
import com.example.corstan__cc__managment.Model.UserCarryDate;
import com.example.corstan__cc__managment.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ManageLocationsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<DayOfWeek> dayOfWeekArrayList;
    private DaysOfWeekAdapter daysOfWeekAdapter;


    Uri uriProfileImage;

    //Edit Texts


    TextView textViewShowShow, textViewDepartment;
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

    private String currentUserId, activeShow, userId, dayDate;

    private EditText editTextEditDayDate, editTextEditDayEpisodeName,
            editTextEditDayCrewCall, editTextEditDayLunchTime
            , editTextEditDaySunUp, editTextEditDaySunUpDn
            ,editTextEditDayDayOfDay1, editTextEditDayDayOfDay2
            ;

    private CheckBox cbMainUnit, cbSecondUnit;

    private RecyclerView scenesList, locationList, bookedStaffList;
    //editDaySceneRecycler, editDayLocationRecycler, editDayStaffRecycler,

    private TextView dept_Staff_TextView, textViewSelectedDate, textViewShowName;

    Button manageScenesB, createDayB, createLocationB, manageStaffB, manageCrewParkB, button, back_B;



    String nBindUserSelectedDayDateReceive, onBindUserSelectedDayDateSend, UserCarryDayDate, UserCarryActiveShow, UserCarryLocationName,  UserCarryDepartment;
    private String dayOfTheWeekSpinnerResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_locations);

        GDDayTool gdDayTool = new GDDayTool();

        //????????????????????????????????????????????????????????????????????????????????????????????????????????
//                         Carry Intents                                                                \\
//????????????????????????????????????????????????????????????????????????????????????????????????????????
//        editDayIntent.putExtra("UserCarryDayDate", UserCarryDayDate.trim());
//        editDayIntent.putExtra("UserCarryActiveShow", UserCarryActiveSHow.trim());
//        editDayIntent.putExtra("UserCarryLocation", UserCarryLocationName.trim());


//????????????????????????????????????????????????????????????????????????????????????????????????????????

        textViewShowName = findViewById(R.id.textViewShowName);
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate);

        textViewDepartment = findViewById(R.id.textViewDepartment);

        if (Objects.requireNonNull(getIntent().getExtras()).getString("UserCarryDayDate") != null) {
            UserCarryDayDate = getIntent().getExtras().getString("UserCarryDayDate");
            textViewSelectedDate.setText(UserCarryDayDate);
        }
        if (Objects.requireNonNull(getIntent().getExtras()).getString("UserCarryActiveShow") != null) {
            UserCarryActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");
            textViewShowName.setText(UserCarryActiveShow.trim());
        }
        if (Objects.requireNonNull(getIntent().getExtras()).getString("UserCarryLocationName") != null) {
            UserCarryLocationName = getIntent().getExtras().getString("UserCarryLocationName");
        }
        if (Objects.requireNonNull(getIntent().getExtras()).getString("UserCarryDepartment") != null) {
            UserCarryDepartment = getIntent().getExtras().getString("UserCarryDepartment");
            textViewDepartment.setText(UserCarryDepartment);
        }



        final GDUserTool gdDUserTool = new  GDUserTool();

        CCSMainDB = FirebaseDatabase.getInstance().getReference();

        activeShowString = gdDUserTool.getActiveShow();

        ;




        //.child("Day Information");

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();



//        scenesList = findViewById(R.id.editDaySceneRecycler);
//        scenesList.setHasFixedSize(true);
//        scenesList.setLayoutManager(new LinearLayoutManager(this));

        locationList = findViewById(R.id.locationsList);
        locationList.setHasFixedSize(true);
        locationList.setLayoutManager(new LinearLayoutManager(this));

//        bookedStaffList = findViewById(R.id.editDayStaffRecycler);
//        bookedStaffList.setHasFixedSize(true);
//        bookedStaffList.setLayoutManager(new LinearLayoutManager(this));




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

        createLocationB = findViewById(R.id.create_location_b);
        manageCrewParkB = findViewById(R.id.manage_crewpark_B);
        back_B = findViewById(R.id.back_B);

        //button = findViewById(R.id.button);



        createLocationB.setOnClickListener(this);
        manageCrewParkB.setOnClickListener(this);
        back_B.setOnClickListener(this);





        //Uses onItemSelected @overide
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Manage Locations");

        //initDaysOfWeekList();
        daysOfWeekAdapter = new DaysOfWeekAdapter(this, dayOfWeekArrayList);
        final String activeShow;
        CCSMainDB.child("Users")
                .child("Management")
                .child(currentUserId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("activeShow")) {

                            UserCarryActiveShow = dataSnapshot.child("activeShow")
                                    .getValue().toString();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


//
//        dayRef = FirebaseDatabase.getInstance().getReference()
//                .child("Shows")
//                .child("Lucifer")
//                .child("Days");
        //.child(onBindUserSelectedDayDateReceive);

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

//        CollectDayInfo();
//
        SetLocationsAdapter();
//        SetStaffAdapter();
//        SetBookedStaffAdapter();




    }




    private void SetLocationsAdapter() {

        locationRef = FirebaseDatabase.getInstance().getReference()

                .child("Shows")
                .child(UserCarryActiveShow)
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
                    protected void onBindViewHolder(@NonNull  LocationViewHolder locationViewHolder, int i, @NonNull final GDLocationTool gdLocationTool) {




                        locationViewHolder.locationName.setText(gdLocationTool.getLocationName());
                        final String locationName = locationViewHolder.locationName.getText().toString();
                        locationViewHolder.locationAddress.setText(gdLocationTool.getLocationAddress());
                        locationViewHolder.locationCity.setText(gdLocationTool.getLocationCity());

                        //locationViewHolder.locationNumberOfScenes.setText(gdLocationTool.getLocationAddress());

                        Boolean ExtInput = gdLocationTool.getLocationExt();
                        Boolean IntExtInput = gdLocationTool.getLocationInt();

                        String IntExtResult;
//
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
                                    Intent editDayIntent = new Intent(ManageLocationsActivity.this, AddLocationActivity.class);
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

                        locationViewHolder.addLocationToDayB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                CCSMainDB.child("Shows")
                                        .child(UserCarryActiveShow)
                                        .child("Days")
                                        .child(textViewSelectedDate.getText().toString().trim())
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
                    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationcard_simple, parent, false);
                        LocationViewHolder viewHolderHolder = new LocationViewHolder(view);
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
    public static class LocationViewHolder extends RecyclerView.ViewHolder {

        TextView locationName, locationAddress
                ,locationCity, locationIntExt
                , locationNumberOfScenes;




        Button showLocationDetailsB, addLocationToDayB;

        public LocationViewHolder(@NonNull View itemView) {

            super(itemView);

            locationName = itemView.findViewById(R.id.locationCardLocationName);
            locationAddress= itemView.findViewById(R.id.locationCardLocationAddress);
            locationCity= itemView.findViewById(R.id.locationCardLocationCity);
            locationIntExt= itemView.findViewById(R.id.locationCardLocationIntExt);
//            locationCrewParkCity= itemView.findViewById(R.id.locationCardLocationName);

            locationNumberOfScenes= itemView.findViewById(R.id.locationCardLocationNumberOfSets);


            //dayDate = itemView.findViewById(R.id.dayCardDayDate);
            //dayDay = itemView.findViewById(R.id.dayCardDayDay);

            showLocationDetailsB = itemView.findViewById(R.id.locationCardShowLocationDetails);
            addLocationToDayB = itemView.findViewById(R.id.locationCardAddLocationToDayB);
            //activeShow = itemView.findViewById(R.id.dayCardActiveShow);

        }
    }


    private void SendUserToAddCreateLocation() {

        Intent addLocationIntent = new Intent(ManageLocationsActivity.this, AddLocationActivity.class);
        addLocationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        addLocationIntent.putExtra("UserCarryDayDate", UserCarryDayDate );
        addLocationIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow );
        startActivity(addLocationIntent);


    }

    private void SendUserToSignInActivity()
    {
        Intent loginIntent = new Intent(ManageLocationsActivity.this, SignInActivity.class);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.back_B:

                SendUserToAddCreateLocation();

                Intent backIntent = new Intent(ManageLocationsActivity.this, EditDayActivity.class);
                backIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //backIntent.putExtra("UserCarryDayDate", onBindUserSelectedDayDateSend );
                backIntent.putExtra("UserCarryDayDate", textViewSelectedDate.getText().toString().trim() );
                backIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow.trim());
                backIntent.putExtra("UserCarryDepartment", UserCarryDepartment);
                startActivity(backIntent);
                finish();
                break;



            case R.id.manageLocationsB:

                SendUserToAddCreateLocation();

                Intent addLocationIntent = new Intent(ManageLocationsActivity.this, ManageStaffActivity.class);
                addLocationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                addLocationIntent.putExtra("UserCarryDayDate", textViewSelectedDate.getText().toString().trim() );
                addLocationIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow.trim());
                addLocationIntent.putExtra("UserCarryDepartment", UserCarryDepartment);
                startActivity(addLocationIntent);
                finish();
                break;
            case R.id.manage_crewpark_B:
                Intent manageStaffIntent = new Intent(ManageLocationsActivity.this, ManageStaffActivity.class);
                manageStaffIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                manageStaffIntent.putExtra("UserCarryDayDate", textViewSelectedDate.getText().toString().trim() );
                manageStaffIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow.trim());
                manageStaffIntent.putExtra("UserCarryDepartment", UserCarryDepartment);
                startActivity(manageStaffIntent);
                finish();
                break;

            case R.id.create_location_b:
                SendUserToAddCreateLocation();

                //startActivity(new Intent(this, MainActivity.class));
                break;




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

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
