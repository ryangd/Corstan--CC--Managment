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
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.corstan__cc__managment.Model.GDDayTool;
import com.example.corstan__cc__managment.Model.GDUserTool;
import com.example.corstan__cc__managment.Model.UserCarryDate;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Objects;


public class ManageStaffActivity extends AppCompatActivity {



    private DatabaseReference CCSMainDB, dayRef;

    ImageView cardviewProfilePicture;

    private FirebaseAuth mAuth;
    String userId, activeShow, currentUserId, currentUserDepartment;


    RecyclerView recyclerView, bookedStaffList;

    private Toolbar mToolbar;

    private String onBindUserSelectedDayDateSend, onBindUserSelectedDayDateReceive
            ,  setAvalableStaffOnBindUserId;;


            String UserCarryDayDate, UserCarryLocationName, UserCarryActiveShow, UserCarryDepartment;

            private TextView textViewSelectedDate;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_staff);

        CCSMainDB = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        //GDDayTool gdDayTool = new GDDayTool();

        textViewSelectedDate = findViewById(R.id.textViewSelectedDate);


        UserCarryDayDate = getIntent().getExtras().getString("UserCarryDayDate");
        textViewSelectedDate.setText(UserCarryDayDate);
        UserCarryLocationName = getIntent().getExtras().getString("UserCarryLocationName");
        UserCarryActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");
        UserCarryDepartment = getIntent().getExtras().getString("UserCarryDepartment");






        //Get Carry Over Intents
        if (getIntent().getExtras().getString("UserCarryDayDate") != null) {
            onBindUserSelectedDayDateReceive = getIntent().getExtras().getString("UserCarryDayDate");
            //gdDayTool.setDayDate(onBindUserSelectedDayDateReceive);

           // onBindUserSelectedDayDateSend = gdDayTool.getDayDate();
        }


        recyclerView = findViewById(R.id.available_staff_recyclerView);
        recyclerView.setHasFixedSize(true);

        bookedStaffList = findViewById(R.id.booked_staff_recyclerView);
        bookedStaffList.setHasFixedSize(true);
        bookedStaffList.setLayoutManager(new LinearLayoutManager(this));







        mToolbar = findViewById(R.id.manageStaffToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Manage Staff");

        //Gte Current UserId
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
                            currentUserDepartment = dataSnapshot.child("userPrimaryDepartment").getValue().toString().trim();
                        }
                        if (dataSnapshot.child("activeShow").exists()) {
                            GDUserTool.setActiveShow(dataSnapshot.child("activeShow").getValue().toString());
                            activeShow = GDUserTool.getActiveShow().trim();
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



        SetAvailableStaffAdapter();
        SetBookedStaffAdapter();
    }

    private void SetBookedStaffAdapter() {
        FirebaseRecyclerOptions<GDUserTool> userQuery;
        FirebaseRecyclerAdapter<GDUserTool, BookedStaffViewHolderHolder> bookedStaffAdapter;

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
//                        if (dataSnapshot.child("userPrimaryDepartment").exists()) {
//                            currentUserDepartment = dataSnapshot.child("userPrimaryDepartment").getValue().toString();
//                        }
//                        if (dataSnapshot.child("activeShow").exists()) {
//                            GDUserTool.setActiveShow(dataSnapshot.child("activeShow").getValue().toString());
//                            activeShow = GDUserTool.getActiveShow();
//                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        UserCarryDepartment = getIntent().getExtras().getString("UserCarryDepartment");
        userQuery = new FirebaseRecyclerOptions.Builder<GDUserTool>()
//                .setQuery(CCSMainDB.child("users"), CCSUserTool.class).build();
                .setQuery(dayRef
                        .child(UserCarryDepartment)
                        .child("Booked Staff"), GDUserTool.class).build();
        bookedStaffAdapter = new FirebaseRecyclerAdapter<GDUserTool, BookedStaffViewHolderHolder>(userQuery) {
            @Override
            protected void onBindViewHolder(@NonNull final BookedStaffViewHolderHolder holder, int i, @NonNull final GDUserTool profile) {

                String callTime;

                holder.bookedStaffCardUserName.setText(profile.getFirstName() + "" + profile.getLastName());
                holder.bookedStaffCardUserPosition.setText(profile.getUserPrimaryPosition());
                ;

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
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                userId = profile.getUserId();



                holder.bookedStaffCardRemoveFromDayB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dayRef
                                .child(currentUserDepartment)
                                .child("Booked Staff")
                                .child(profile.getUserId())
                                .removeValue();

                    }
                });

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

                ;

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
            public BookedStaffViewHolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookedstaffcard, parent, false);
                return new BookedStaffViewHolderHolder(view);
            }
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        bookedStaffList.setLayoutManager(linearLayoutManager);
        bookedStaffAdapter.startListening();
        bookedStaffList.setAdapter(bookedStaffAdapter);
    }





    private void SetAvailableStaffAdapter() {



        FirebaseRecyclerOptions<GDUserTool> userQuery;
        FirebaseRecyclerAdapter<GDUserTool, StaffViewHolderHolder> staffAdapter;

        userQuery = new FirebaseRecyclerOptions.Builder<GDUserTool>()
//                .setQuery(CCSMainDB.child("users"), CCSUserTool.class).build();
                .setQuery(CCSMainDB.child("Users").child("Management"), GDUserTool.class).build();
        staffAdapter = new FirebaseRecyclerAdapter<GDUserTool, StaffViewHolderHolder>(userQuery) {
            @Override
            protected void onBindViewHolder(@NonNull final StaffViewHolderHolder holder, int i, @NonNull final GDUserTool profile) {
                holder.availableStaffCardUserName.setText(profile.getFirstName()+" "+profile.getLastName());
                holder.availableStaffCardUserPosition.setText(profile.getUserPrimaryPosition());




                setAvalableStaffOnBindUserId = profile.getUserId();

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
                                    Picasso.get().load(image).placeholder(R.drawable.profile).into(holder.cardviewProfilePicture);

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
//                        .addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                if (dataSnapshot.exists()) {
//
//
//







                holder.availableStaffCardAddLocationToDayB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        HashMap onBindStaffHashMap = new HashMap();
                        onBindStaffHashMap.put("firstName", profile.getFirstName());
                        onBindStaffHashMap.put("lastName", profile.getLastName());
                        onBindStaffHashMap.put("callTime", holder.cardviewCallTimeEditText.getText().toString().trim());

                        onBindStaffHashMap.put("userPrimaryDepartment", profile.getUserPrimaryDepartment());
                        onBindStaffHashMap.put("userPrimaryPosition", profile.getUserPrimaryPosition());


                        onBindStaffHashMap.put("userId", profile.getUserId());


                        dayRef
                                .child(currentUserDepartment)
                                .child("Booked Staff")
                                .child(profile.getUserId())
                                .updateChildren(onBindStaffHashMap);




                    }
                });


            }

            @NonNull
            @Override
            public StaffViewHolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
                return new StaffViewHolderHolder(view);
            }
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        staffAdapter.startListening();
        recyclerView.setAdapter(staffAdapter);
    }

    public static class StaffViewHolderHolder extends RecyclerView.ViewHolder {

        TextView availableStaffCardUserName, availableStaffCardUserPosition;

        EditText cardviewCallTimeEditText;

        ImageView cardviewProfilePicture;

        String userId;

        Button availableStaffCardAddLocationToDayB;
        public StaffViewHolderHolder(@NonNull View itemView) {
            super(itemView);
            availableStaffCardUserName = itemView.findViewById(R.id.availableStaffCardUserName);
            availableStaffCardUserPosition = itemView.findViewById(R.id.availableStaffCardUserPosition);


            cardviewProfilePicture = itemView.findViewById(R.id.cardviewProfilePicture);

            cardviewCallTimeEditText = itemView.findViewById(R.id.cardviewCallTimeEditText2);

            availableStaffCardAddLocationToDayB = itemView.findViewById(R.id.availableStaffCardAddLocationToDayB);

        }
//        TextView locationName, locationAddress,locationCity
//                ,locationCrewPark,locationCrewParkCity, cbLocationIntExt;
//         Button editLocationB;
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
    }

    public static class BookedStaffViewHolderHolder extends RecyclerView.ViewHolder {

        TextView bookedStaffCardUserName, bookedStaffCardUserPosition;

        EditText bookedStaffCallTimeEditText;

        ImageView bookedStaffCardProfilePicture;

        String userId;

        Button bookedStaffCardRemoveFromDayB, bookedStaffCardUpdateCallTimeB;

        public BookedStaffViewHolderHolder(@NonNull View itemView) {
            super(itemView);
            bookedStaffCardUserName = itemView.findViewById(R.id.bookedStaffCardUserName);
            bookedStaffCardUserPosition = itemView.findViewById(R.id.bookedStaffCardUserPosition);

            bookedStaffCardProfilePicture = itemView.findViewById(R.id.bookedStaffCardProfilePicture);

            bookedStaffCallTimeEditText = itemView.findViewById(R.id.bookedStaffCardCallTimeEditText);

            bookedStaffCardRemoveFromDayB = itemView.findViewById(R.id.bookedStaffCardRemoveFromDayB);
            bookedStaffCardUpdateCallTimeB = itemView.findViewById(R.id.bookedStaffCardUpdateCallTimeB);

        }
//        TextView locationName, locationAddress,locationCity
//                ,locationCrewPark,locationCrewParkCity, cbLocationIntExt;
//         Button editLocationB;
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
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            SendUserToEditDayActivity();
        }


        return super.onOptionsItemSelected(item);
    }
    private void SendUserToMainActivity() {
        Intent intent = new Intent(ManageStaffActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void SendUserToCreateEditShow() {

        Intent createShowIntent = new Intent(ManageStaffActivity.this, CreateShowActivity.class);
        createShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(createShowIntent);
        finish();
    }
    private void SendUserToEditDayActivity() {

        Intent editDayIntent = new Intent(ManageStaffActivity.this, EditDayActivity.class);
        editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        editDayIntent.putExtra("UserCarryDayDate", UserCarryDayDate.trim());
        editDayIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow.trim());
        editDayIntent.putExtra("UserCarryDepartment", UserCarryDepartment);
        startActivity(editDayIntent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onStop() {
        super.onStop();

    }
    @Override
    protected void onResume() {
        super.onResume();
    }


}

























//        lv=findViewById(R.id.listView);
//        Query query = FirebaseDatabase.getInstance().getReference();
//
//        FirebaseListOptions<Profile> options = new FirebaseListOptions.Builder<Profile>()
//                .setLayout(R.layout.student)
//                .setQuery(query, Profile.class)
//                .build();
//        adapter= new FirebaseListAdapter(options) {
//            @Override
//            protected void populateView(View v, Object model, int position) {
//                TextView firstName = V.findViewById(R.id.firstName);
//                TextView lastName = V.findViewById(R.id.lastName);
//
//            }
//        };
//
//
//
//

//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        userId = user.getUid();
//
//
//        recyclerView = findViewById(R.id.myRecycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter = new MyAdapter(ShowStaff.this, list);
//        recyclerView.setAdapter(adapter);
//        list = new ArrayList<Profile>();
//        adapter = new MyAdapter(ShowStaff.this, list);
//        recyclerView.setAdapter(adapter);
//
//        CCSMainDB = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
//
//        CCSMainDB.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
//                    Profile p = dataSnapshot1.getValue(Profile.class);
//                    list.add(p);
//                }
//                adapter = new MyAdapter(ShowStaff.this, list);
//                recyclerView.setAdapter(adapter);
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//
//
//            }
//        });
//
//
//    }
//}
