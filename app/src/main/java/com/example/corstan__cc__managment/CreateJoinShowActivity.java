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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corstan__cc__managment.Model.GDDayTool;
import com.example.corstan__cc__managment.Model.GDShowTool;
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

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateJoinShowActivity extends AppCompatActivity {



    Button profileB, availabilityB, paperworkB, scheduleB, settingsB;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView showList;
    private Toolbar mToolbar;

    private LinearLayoutManager linearLayoutManager;

    private CircleImageView NavProfileImage;
    private TextView NavProfileFullName, NavActiveShow, NavUserPosition, dayDate;
    private Button createShowB;

    private FirebaseAuth mAuth;

    private DatabaseReference CCSMainDB, showRef;

    private ImageButton CreateDayButton;

//    FirebaseRecyclerOptions<CCSUserTool> userQuery;
//    FirebaseRecyclerOptions<CCSDayTool> dayQuery;
//    FirebaseRecyclerAdapter<CCSUserTool, StaffViewHolder> staffAdapter;
//    FirebaseRecyclerAdapter<CCSDayTool, DayViewHolder> dayAdapter;

    private String newUserShow, activeShow;

    private String onBindUserSelectedDayDate;

    String currentUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_join_show);

        final GDUserTool gdUserTool = new GDUserTool();

        CCSMainDB = FirebaseDatabase.getInstance().getReference();
        //activeShow = gdUserTool.getActiveShow();


//        .child("Day Information");
//        Query dayQuery = dayRef.orderByKey();




        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        activeShow = "Charmed S2";
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Join or Create Show");

        createShowB = findViewById(R.id.create_show_b);


        drawerLayout = findViewById(R.id.drawable_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_closed);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView =  findViewById(R.id.navigation_view);
        final View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        NavProfileImage = navView.findViewById(R.id.nav_circleProfilePhoto);
        NavProfileFullName = navView.findViewById(R.id.nav_user_full_name);
        NavActiveShow = navView.findViewById(R.id.nav_user_active_show);
        NavUserPosition = navView.findViewById(R.id.nav_user_position);


        showList = findViewById(R.id.all_shows_list);
        showList.setHasFixedSize(true);
        showList.setLayoutManager(new LinearLayoutManager(this));

        CCSMainDB = FirebaseDatabase.getInstance().getReference();

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




        createShowB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToCreateEditShow();

            }
        });

        //DisplayAllDays();

        CCSMainDB.child("Users")
                .child("Management")
                .child(currentUserId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //GDUserTool gdUserTool = new GDUserTool();

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
                                String navActiveShow =dataSnapshot.child("activeShow").getValue().toString();
                                NavActiveShow.setText(navActiveShow);
                                activeShow = (navActiveShow);


                            }
                            if (dataSnapshot.hasChild("userPrimaryPosition")) {
                                String navUserPosition =dataSnapshot.child("userPrimaryPosition").getValue().toString();
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

        showRef = CCSMainDB
                .child("Shows");



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                UserMenuSelector(item);

                return false;
            }
        });

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

        Intent createShowIntent = new Intent(CreateJoinShowActivity.this, CreateShowActivity.class);
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
//Menu Buttins
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
        //final GDShowTool gdShowTool = new GDShowTool();
        super.onStart();

        FirebaseRecyclerOptions<GDShowTool> options =
                new FirebaseRecyclerOptions.Builder<GDShowTool>()
                        .setQuery(showRef, GDShowTool.class)
                        .build();

        FirebaseRecyclerAdapter<GDShowTool, ShowViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<GDShowTool, ShowViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ShowViewHolder showViewHolder, int i, @NonNull final GDShowTool gdShowTool) {
                        showViewHolder.showName.setText(gdShowTool.getShowName());



                        showViewHolder.joinShowB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //Set User activeShow
                                CCSMainDB.child("Users")
                                        .child("Management")
                                        .child(currentUserId)
                                        .child("activeShow")
                                        .setValue(gdShowTool.getShowName())

                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        SendUserToMainActivity();
                                    }
                                });
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showcardv1, parent, false);
//                        MainActivity.DayViewHolderHolder viewHolderHolder = new MainActivity.DayViewHolderHolder(view);
//                        return viewHolderHolder;


                        return new ShowViewHolder(view);
                    }
                };

        firebaseRecyclerAdapter.startListening();
        showList.setAdapter(firebaseRecyclerAdapter);


//        FirebaseRecyclerOptions<GDShowTool> optionsOld =
//                new FirebaseRecyclerOptions.Builder<GDShowTool>()
//                        .setQuery(showRef, GDShowTool.class)
//                        .build();
//
//        final FirebaseRecyclerAdapter<GDDayTool, CreateJoinShowActivity.ShowViewHolder> showAdapter =
//
//                new FirebaseRecyclerAdapter<GDDayTool, CreateJoinShowActivity.ShowViewHolder>(options) {
//                    @Override
//                    protected void onBindViewHolder(@NonNull CreateJoinShowActivity.ShowViewHolder showViewHolder, int i, @NonNull GDDayTool gdDayTool) {
//
//                        showViewHolder.showName.setText(gdShowTool.getShowName());
//
//                    }
//
//                    @NonNull
//                    @Override
//                    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        return null;
//                    }
//                };



//                new FirebaseRecyclerAdapter<GDDayTool, MainActivity.DayViewHolderHolder>(options) {
//                    @Override
//                    protected void onBindViewHolder(@NonNull final MainActivity.DayViewHolderHolder holder, final int position, @NonNull final GDDayTool ccsDayTool) {
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
////                                userSelectedDayDate.put("userSelectedDayDate", UserSelectedDayDate);
////
////                                CCSMainDB.child("Users").child("Management")
////                                        .child(currentUserId)
////                                        .updateChildren(userSelectedDayDate);
//
//
//                                {
//                                    Intent editDayIntent = new Intent(CreateJoinShowActivity.this, EditDayActivity.class);
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
//                    public MainActivity.DayViewHolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showcardv1, parent, false);
//                        MainActivity.DayViewHolderHolder viewHolderHolder = new MainActivity.DayViewHolderHolder(view);
//                        return viewHolderHolder;
//
//
//                    }
//                };

        //showAdapter.startListening();

        //dayList.setAdapter(adapter);
        //staffirebaseRecyclerAdapter.startListening();


        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null) {
            SendUserToSignInActivity();
        }
        else {
            CheckUserExistence();
        }
    }

    public static class ShowViewHolder extends RecyclerView.ViewHolder {


        //?????????????????????????????????????????????????????????????????????????????????
        TextView showName;
        Button joinShowB;


        //        Button editDayB;
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
//????????????????????????????????????????????????????????????????????????????????????????????????????
        public ShowViewHolder(@NonNull View itemView) {
            super(itemView);

            showName = itemView.findViewById(R.id.showCardShowName);

            joinShowB = itemView.findViewById(R.id.showCardJoinShowB);
        }
    }


    private void SendUserToMainActivity() {
        Intent intent = new Intent(CreateJoinShowActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void SendUserToCreateJoinShowActivity(){

        Intent joinCreateShowIntent = new Intent(CreateJoinShowActivity.this, CreateJoinShowActivity.class);
        joinCreateShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //editDayIntent.putExtra("UserCarryDayDate", );
        startActivity(joinCreateShowIntent);
        finish();


    }



    private void SendUserToEditDayActivity() {

        Intent editDayIntent = new Intent(CreateJoinShowActivity.this, EditDayActivity.class);
        editDayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //editDayIntent.putExtra("UserCarryDayDate", );
        startActivity(editDayIntent);
        finish();
    }


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

        Intent signUpProfileIntent = new Intent(CreateJoinShowActivity.this, SignUpProfileActivity.class);
        signUpProfileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signUpProfileIntent);
        finish();

    }


    private void SendUserToSignInActivity()
    {
        Intent loginIntent = new Intent(CreateJoinShowActivity.this, SignInActivity.class);
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

        Intent createDayIntent = new Intent(CreateJoinShowActivity.this, CreateDayActivity.class);
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
