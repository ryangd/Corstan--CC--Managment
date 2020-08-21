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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.corstan__cc__managment.Model.GDDayTool;
import com.example.corstan__cc__managment.Model.GDUserTool;
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

public class ScheduleActivity extends AppCompatActivity implements android.view.View.OnClickListener {

    Button createDay, showStaffB, paperworkB, scheduleB, settingsB;

    String activeShowSelector, recycleDayDateData;
//
//    RecyclerView recyclerView;
//    DatabaseReference CCSMainDB;
//    FirebaseRecyclerOptions<CCSDayTool> dayQuery;
//    FirebaseRecyclerAdapter<CCSDayTool, DayViewHolder> adapter;

    DatabaseReference CCSMainDB;

    private FirebaseAuth mAuth;
    private String userId;

    private ArrayList<String> mDayDay = new ArrayList<>();
    private ArrayList<String> mDayDate = new ArrayList<>();


    private RecyclerView recyclerViewScheduleDay;


    GDUserTool GDUserTool;



    private void SendUserToSignInActivity() {
        Intent loginIntent = new Intent(ScheduleActivity.this, SignInActivity.class);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        CCSMainDB = FirebaseDatabase.getInstance().getReference();

//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//
        activeShowSelector = "Charmed S3";

        //Assigning Variables
        createDay = findViewById(R.id.createDayB);
        showStaffB = (findViewById(R.id.showStaffB));
//        paperworkB = (findViewById(R.id.paperworkB));
//        scheduleB = (findViewById(R.id.scheduleB));
//        settingsB = (findViewById(R.id.settingsB));


        //Assisnging the button clicks
        createDay.setOnClickListener(this);
        showStaffB.setOnClickListener(this);
//        paperworkB.setOnClickListener(this);
//        scheduleB.setOnClickListener(this);
//        settingsB.setOnClickListener(this);


        recyclerViewScheduleDay = findViewById(R.id.recyclerViewScheduleDay);
        recyclerViewScheduleDay.setHasFixedSize(true);

        //ccsDayTool = new CCSDayTool();

        CCSMainDB = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    protected void onStart() {


        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            SendUserToSignInActivity();
        } else {
            CheckUserExistence();
        }




        FirebaseRecyclerOptions<GDDayTool> options =
                new FirebaseRecyclerOptions.Builder<GDDayTool>()
                        .setQuery(CCSMainDB.child("shows").child("Charmed S1"), GDDayTool.class)
                        .build();



        FirebaseRecyclerAdapter<GDDayTool, DayViewHolderAdapter> adapter =
                new FirebaseRecyclerAdapter<GDDayTool, DayViewHolderAdapter>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final DayViewHolderAdapter dayViewHolderAdapter, final int position, @NonNull final GDDayTool ccsDayTool) {


                        dayViewHolderAdapter.dayDay.setText(ccsDayTool.getDayDate());

                        if (ccsDayTool.getDayDate() != null) {
                            dayViewHolderAdapter.dayDate.setText(ccsDayTool.getDayDate());
                        }
//                        if (ccsDayTool.getDayAddress() != null) {
//                            dayViewHolderAdapter.dayAddress.setText(ccsDayTool.getDayAddress());
//                        }
//                        if (ccsDayTool.getDayCity() != null) {
//                            dayViewHolderAdapter.dayCity.setText(ccsDayTool.getDayCity());
//                        }
                        if (ccsDayTool.getShowName() != null) {
                            dayViewHolderAdapter.activeShow.setText(ccsDayTool.getShowName());
                        }

                        dayViewHolderAdapter.editDayB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String day_Date_Data_Send = getRef(position).getKey();
                                String day_ActiveShow_Data_Send = "Charmed S1";
                                String day_Day_Day_Data_Send = ccsDayTool.getDayDate();


                                Intent editDayIntent = new Intent(ScheduleActivity.this, EditDayActivity.class);
                                //Put Extras
                                editDayIntent.putExtra("day_Date_Data_Send", day_Date_Data_Send);
                                editDayIntent.putExtra("day_ActiveShow_Data_Send", day_ActiveShow_Data_Send);
                                editDayIntent.putExtra("day_Day_Day_Data_Send", day_Day_Day_Data_Send);


                                startActivity(editDayIntent);
                            }
                        });

                        //dayViewHolderAdapter.editDayB.setText(ccsDayTool.get());


                    }

                    @NonNull
                    @Override
                    public DayViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daycard, parent, false);
                        DayViewHolderAdapter viewHolder = new DayViewHolderAdapter(view);
                        return viewHolder;
                    }
                };
        recyclerViewScheduleDay.setAdapter(adapter);
        recyclerViewScheduleDay.setLayoutManager(new LinearLayoutManager(this));
        adapter.startListening();


    }



    public static class DayViewHolderAdapter extends RecyclerView.ViewHolder{

        TextView dayDay;
        TextView dayDate;
        TextView dayAddress;
        TextView dayCity;
        TextView activeShow;

         Button editDayB;

        public DayViewHolderAdapter(@NonNull View itemView) {


            super(itemView);

            //activeShow = itemView.findViewById(R.id.dayCardActiveShow);


            //Edit Texts
//            dayDay = itemView.findViewById(R.id.dayCardDayDay);
//            dayDate = itemView.findViewById(R.id.dayCardDayDate);
//            dayAddress = itemView.findViewById(R.id.editTextDayAddress);
//            dayCity = itemView.findViewById(R.id.editTextDayCity);


            //Button
            //editDayB = itemView.findViewById(R.id.locationCardGoToDay);
        }
    }




    @Override
    public void onClick (View view) {
        switch (view.getId()){
            case R.id.createDayB:
                startActivity(new Intent(this, CreateDayActivity.class));
                break;
//
            case R.id.showStaffB:
                startActivity(new Intent(this, ManageStaffActivity.class));
                break;
//            case R.id.paperworkB:
//                startActivity(new Intent (this, PaperworkActivity.class));
//                break;
//           case R.id.scheduleB:
//               startActivity(new Intent(this, ScheduleActivity.class));
//                break;
//            case R.id.settingsB:
//                startActivity(new Intent(this, SettingsActivity.class));
//                break;
        }

    }
}