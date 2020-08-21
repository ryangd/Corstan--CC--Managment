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
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.net.Uri;

import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.corstan__cc__managment.Model.GDShowTool;
import com.example.corstan__cc__managment.Model.GDUserTool;
import com.example.corstan__cc__managment.Model.Profile;
import com.example.corstan__cc__managment.ViewHolder.StaffViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateShowActivity extends AppCompatActivity {
//?????????????????????????????????????????????????????????????????????????????????????????????????????????
                        //EditTexts Raw Do Not Delete
//?????????????????????????????????????????????????????????????????????????????????????????????????????????
//    //Edit Texts
//    editTextShowName;
//    editTextProductionCompanyName;
//    editTextProductionAddress;
//    editTextProductionPhoneNumber;
//    editTextProductionEmail;
//    editTextProductionFaxNumber;
//    editTextProductionHours;
//    //Studio
//    editTextStudioName;
//    editTextStudioCity;
//    editTextStudioPhoneNumber;
//    //HR
//    editTextStudioHrContact;
//    editTextStudioHrContactPhoneNumber;
//    editTextStudioHrContactEmail;
//    //Safety
//    editTextStudioSafetyContact;
//    editTextStudioSafetyContactPhoneNumber;
//    editTextStudioSafetyContactEmail;
//?????????????????????????????????????????????????????????????????????????????????????????????????????????????

    Uri uriProfileImage;

            //Production editTexts
    EditText editTextShowName, editTextProductionCompanyName
            , editTextProductionAddress, editTextProductionPhoneNumber
            , editTextProductionEmail, editTextProductionFaxNumber
            , editTextProductionHours
            //Studio
            , editTextStudioName, editTextStudioCity
            , editTextStudioPhoneNumber
            //HR
            , editTextStudioHrContact, editTextStudioHrContactPhoneNumber
            , editTextStudioHrContactEmail
            //Safety
            , editTextStudioSafetyContact, editTextStudioSafetyContactPhoneNumber
            , editTextStudioSafetyContactEmail;


    TextView textViewNameTest;
    //Button
    Button createShowB;

    //Firebase database Refrence
    DatabaseReference CCSMainDB;
    //Child DB Referecnces
    DatabaseReference activeShowDB;
    //String for Child
    String activeShowString;

    RecyclerView recyclerView;

    FirebaseRecyclerOptions<GDUserTool> ccsUserToolFirebaseRecyclerOptions;
    FirebaseRecyclerAdapter<Profile, StaffViewHolder> adapter;

    //???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    private Toolbar mToolbar;

    //Firebase Auth
    private FirebaseAuth mAuth;

    String currentUserId;

    private Spinner join_create_spinner;

    private ArrayList shows;

    private ArrayAdapter<CharSequence> join_create_array;

    private String userPrimaryDepartment, userPrimaryPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_show2);

        CCSMainDB = FirebaseDatabase.getInstance().getReference();
        //createDatabaseForm();
        activeShowString = "activeShow";
// ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
 //      getSupportActionBar().setTitle("Create Show");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);






//  ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        //Set Variables
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        //Edit Texts
        editTextShowName = findViewById(R.id.editTextShowName);
        editTextProductionCompanyName = findViewById(R.id.editTextProductionCompanyName);
        editTextProductionAddress = findViewById(R.id.editTextProductionAddress);;
        editTextProductionPhoneNumber = findViewById(R.id.editTextProductionPhoneNumber);
        editTextProductionEmail = findViewById(R.id.editTextProductionEmail);;
        editTextProductionFaxNumber = findViewById(R.id.editTextProductionFaxNumber);;
        editTextProductionHours = findViewById(R.id.editTextProductionHours);;
        //Studio
        editTextStudioName = findViewById(R.id.editTextStudioName);;
        editTextStudioCity = findViewById(R.id.editTextStudioCity);;
        editTextStudioPhoneNumber = findViewById(R.id.editTextStudioPhoneNumber);;
        //HR
        editTextStudioHrContact = findViewById(R.id.editTextStudioHRContact);;
        editTextStudioHrContactPhoneNumber = findViewById(R.id.editTextHRContactPhoneNumber);
        editTextStudioHrContactEmail = findViewById(R.id.editTextStudioHREmail);
        //Safety
        editTextStudioSafetyContact = findViewById(R.id.editTextStudioSafetyContact);
        editTextStudioSafetyContactPhoneNumber = findViewById(R.id.editTextStudioSafetyContactPhoneNumber);;
        editTextStudioSafetyContactEmail = findViewById(R.id.editTextStudioSafetyContactEmail);;


        //Button
        createShowB = findViewById(R.id.saveShowInformation);

        //Firebase Auth

        GDShowTool ccsShowTool =  new GDShowTool();

        GDUserTool GDUserTool = new GDUserTool();
        //Build User Object
        ccsUserToolFirebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<GDUserTool>()
                .setQuery(CCSMainDB, GDUserTool.class).build();

        CCSMainDB.child("Users").child("Management").child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //userPrimaryDepartment = dataSnapshot.child("userPrimaryDepartment").getValue().toString();
                //userPrimaryPosition = dataSnapshot.child("userPrimaryPosition").getValue().toString();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        createShowB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createShow();

                Intent intent = new Intent(CreateShowActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            //saveUserProfilePhoto();
        });
        join_create_spinner = findViewById(R.id.join_spinner);
//        CCSMainDB.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                List<String> shows = new ArrayList<>();
//
//                for (DataSnapshot showsSnapshot: dataSnapshot.getChildren()) {
//                    String userType = dataSnapshot.child("Shows").getValue().toString();
//
//
//                    switch (userType) {
//                        case "Shows":
//                            String Name = showsSnapshot.getValue().toString();
//                            shows.add(Name);
//                            break;
//
//                    }
//
//
//
//                }
//
//                ArrayAdapter<String> showsAdapter = new ArrayAdapter<String>
//                        (getApplicationContext()
//                        , android.R.layout.simple_spinner_item
//                        , shows);
//                showsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                join_create_spinner.setAdapter(showsAdapter);
//                if (showsAdapter != null)
//                    showsAdapter.startListening();
//
//                }



//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



//        adapter = new FirebaseRecyclerAdapter<Profile, StaffViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull StaffViewHolder holder, int i, @NonNull Profile profile) {
//                holder.firstName.setText(profile.getFirstName());
//                holder.lastName.setText(profile.getLastName());
//
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
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        adapter.startListening();
//        recyclerView.setAdapter(adapter);
    }


    private void SendUserToSignInActivity()
    {
        Intent loginIntent = new Intent(CreateShowActivity.this, SignInActivity.class);
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
    protected void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.startListening();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null) {
            SendUserToSignInActivity();
        }
        else {
            CheckUserExistence();
        }
    }






    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null)
            adapter.stopListening();

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();






//Set onClicks
        //Update User Name OnClick

    }


    //Profile Image OnClick
    //imageViewProfilePhoto.setOnClickListener(new View.OnClickListener() {

    // @Override
    //public void onClick(View v) {
    //showProfilePhotoSelector();
    //}
    //}
    //);

    //Database innitalized in updateUserName Void
    //databaseUsers = FirebaseDatabase.getInstance().getReference().child(mAuth.getUid());


    //Checks Current User

    //public void loadUserInformation () {

    //databaseUsers.addValueEventListener(new ValueEventListener() {
    //     @Override
    //     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
    //         String firstName = dataSnapshot.child("firstName").getValue().toString();
//
    //  }

    //  @Override
    //  public void onCancelled(@NonNull DatabaseError databaseError) {
//
    //  }
    // });


    //Update User Name Function


    public void createShow() {


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {

                CCSMainDB = FirebaseDatabase.getInstance().getReference();

                activeShowDB = FirebaseDatabase.getInstance().getReference().child("users")
                        .child(currentUserId)
                        .child(activeShowString);


                GDShowTool gdShowTool = new GDShowTool();

//                CCSMainDB.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        //showData(dataSnapshot);
//                    }
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });


                // UID specific to the user
                // String uid = profile.getUid();
                String userId = profile.getUid();



                //    editTextShowName;
                gdShowTool.setShowName(editTextShowName.getText().toString());
                gdShowTool.setProductionCompanyName(editTextProductionCompanyName.getText().toString());
                gdShowTool.setProductionAddress(editTextProductionAddress.getText().toString());
                gdShowTool.setProductionPhoneNumber(editTextProductionPhoneNumber.getText().toString());
                gdShowTool.setProductionEmail(editTextProductionEmail.getText().toString());
                gdShowTool.setProductionFaxNumber(editTextProductionFaxNumber.getText().toString());
                gdShowTool.setProductionHours(editTextProductionHours.getText().toString());
                //Studio
                gdShowTool.setStudioName(editTextStudioName.getText().toString());
                gdShowTool.setStudioCity(editTextStudioCity.getText().toString());
                gdShowTool.setStudioPhoneNumber(editTextStudioPhoneNumber.getText().toString());
                //HR
                gdShowTool.setStudioHrContact(editTextStudioHrContact.getText().toString());
                gdShowTool.setStudioHrContactPhoneNumber(editTextStudioHrContactPhoneNumber.getText().toString());
                gdShowTool.setStudioHrContactEmail(editTextStudioHrContactEmail.getText().toString());
                //Safety
                ;
                gdShowTool.setStudioSafetyContact(editTextStudioSafetyContact.getText().toString());
                gdShowTool.setStudioSafetyContactPhoneNumber(editTextStudioSafetyContactPhoneNumber.getText().toString());
                gdShowTool.setStudioSafetyContactEmail(editTextStudioSafetyContactEmail.getText().toString());






                //String for Production Info
//                ccsShowTool.setProductionName(editTextProductionName.getText().toString().trim());
//                ccsShowTool.setProductionPhoneNumber6(editTextProductionPhoneNumber6.getText().toString().trim());
//
//                //String for Locations Info
//                ccsShowTool.setLocationManager(editTextLocationManager.getText().toString().trim());
//                ccsShowTool.setLocationManagerEmail(editTextLocationManagerEmail.getText().toString().trim());
//                ccsShowTool.setAssistantLocationManager(editTextAssistantLocationManager.getText().toString().trim());
//                ccsShowTool.setAssistantLocationManagerEmail(editTextAssistantLocationManagerEmail.getText().toString().trim());
//                ccsShowTool.setAssistantLocationManagerPhoneNumber(editTextAssistantLocationManagerPhoneNumber.getText().toString().trim());


                CCSMainDB.child("Shows")
                        .child(gdShowTool.getShowName())
//                        .child("Production Information")
                        .setValue(gdShowTool);


                //CCSMainDB.child("shows").child(ccsShowTool.getProductionName()).updateChildren("activeShow", );
                //CCSMainDB.child("users").child(user.getUid()).updateChildren("activeShow", CCSUserTool.class);
                //CCSMainDB.updateChildren(ccsShowTool.getProductionName(cc));
                //activeShowDB.setValue(ccsShowTool.getProductionName());

//                CCSMainDB.child("Shows").child(gdShowTool.getProductionName())
//                        .child(userPrimaryDepartment)
//                        .child(userPrimaryPosition)
//                        .setValue(userId);
                HashMap activeShowHashMap = new HashMap();

                activeShowHashMap.put("activeShow", gdShowTool.getShowName());


                CCSMainDB.child("Users")
                        .child("Management")
                        .child(currentUserId)
                        .updateChildren(activeShowHashMap)
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                        SendUserToMainActivity();
                    }
                });
            }
        }
    }


    private void SendUserToMainActivity() {
        Intent signUpProfileIntent = new Intent(CreateShowActivity.this, MainActivity.class);
        signUpProfileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signUpProfileIntent);
        finish();
    }
}

