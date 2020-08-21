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

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.corstan__cc__managment.Model.GDDayTool;
import com.example.corstan__cc__managment.Model.GDLocationTool;
import com.example.corstan__cc__managment.Model.GDUserTool;
import com.example.corstan__cc__managment.Model.UserCarryDate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class AddLocationActivity extends AppCompatActivity implements View.OnClickListener {
            //Location Edit Texts

    private FirebaseAuth mAuth;
    private EditText editTextLocationName, editTextLocationAddress
            , editTextLocationCity, editTextLocationNickname
                    , editTextLocationContact, editTextLocationContactPhoneNumber
                    , editTextLocationContactEmail, editTextLocationContactHours
            //Crew Park Edit Texts
            , editTextLocationCrewParkName, editTextLocationCrewParkCity
                    , editTextLocationCrewParkNickname, editTextLocationCrewParkAddress
                    ,editTextLocationCrewParkContact, editTextLocationCrewParkContactPhoneNumber
                    ,editTextLocationCrewParkContactEmail, editTextLocationCrewParkContactHours
            //Location Safety
                , editTextLocationNearestHospital, editTextLocationNearestHospitalAddress
                    ,editTextLocationNearestHospitalContactPhoneNumber, editTextLocationNearestHospitalCity;

    private CheckBox cbLocationInt, cbLocationExt
            , cbLocationCrewParkCrew, cbLocationCrewParkBG;

    private Button saveLocationInformationB;

    private DatabaseReference CCSMainDB, dayItemLocationRef;

    private String activeShow;

    String onBindUserSelectedDayDate;
    String UserCarryLocationName, UserCarryDayDate, UserCarryActiveShow, UserCarryDepartment;

    TextView textView5;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        CCSMainDB = FirebaseDatabase.getInstance().getReference();

        //GDLocationTool gdLocationTool = new GDLocationTool();


        //activeShow = ;


        UserCarryDayDate = getIntent().getExtras().getString("UserCarryDayDate");
        UserCarryLocationName = getIntent().getExtras().getString("UserCarryLocationName");
        UserCarryActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");
        UserCarryDepartment = getIntent().getExtras().getString("UserCarryDepartment");






        //Location
        editTextLocationName = findViewById(R.id.editTextLocationName);
        editTextLocationName.setText(UserCarryLocationName);
        editTextLocationNickname = findViewById(R.id.editTextLocationNickname);
        //editTextLocationNickname.setText(gdLocationTool.getLocationCrewParkNickname());
        editTextLocationAddress = findViewById(R.id.editTextLocationAddress);
        editTextLocationCity = findViewById(R.id.editTextLocationCity);
        //Location Contact
        editTextLocationContact = findViewById(R.id.editTextLocationContact);
        editTextLocationContactPhoneNumber = findViewById(R.id.editTextLocationContactPhoneNumber);
        editTextLocationContactEmail = findViewById(R.id.editTextLocationContactEmail);
        editTextLocationContactHours = findViewById(R.id.editTextLocationContactHours);

        //Crew Park
        editTextLocationCrewParkName = findViewById(R.id.editTextLocationCrewParkName);
        editTextLocationCrewParkNickname = findViewById(R.id.editTextLocationCrewParkNickname);
        editTextLocationCrewParkAddress = findViewById(R.id.editTextLocationCrewParkAddress);
        editTextLocationCrewParkCity = findViewById(R.id.editTextLocationCrewParkCity);
        //Crew Park Contact
        editTextLocationCrewParkContact = findViewById(R.id.editTextLocationCrewParkContact);
        editTextLocationCrewParkContactPhoneNumber = findViewById(R.id.editTextLocationCrewParkContactPhoneNumber);
        editTextLocationCrewParkContactEmail = findViewById(R.id.editTextLocationCrewParkContactEmail);
        editTextLocationCrewParkContactHours = findViewById(R.id.editTextLocationCrewParkContactHours);

        //Nearest Hospital
        editTextLocationNearestHospital = findViewById(R.id.editTextLocationNearestHospital);
        editTextLocationNearestHospitalAddress = findViewById(R.id.editTextLocationNearestHospitalAddress);
        editTextLocationNearestHospitalCity = findViewById(R.id.editTextLocationNearestHospitalCity);
        editTextLocationNearestHospitalContactPhoneNumber = findViewById(R.id.editTextLocationNearestHospitalContactPhoneNumber);






        //textView5 = findViewById(R.id.textView5);
        //Location CheckBoxes
        cbLocationInt = findViewById(R.id.checkBoxLocationInt);
        cbLocationExt = findViewById(R.id.checkBoxLocationExt);
        //Crew Park CheckBoxes
        cbLocationCrewParkBG = findViewById(R.id.checkBoxLocationCrewParkBG);
        cbLocationCrewParkCrew = findViewById(R.id.checkBoxLocationCrewParkCrew);

        saveLocationInformationB = findViewById(R.id.saveLocationInformation);

        //editTextLocationName.setText(UserCarryLocationName);

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Location");




        saveLocationInformationB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectLocationDataToDB();
            }
        });

        FetchLocationDataFromDB1();



//        dayItemLocationRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                GDLocationTool gdLocationTool = new GDLocationTool();
//
//
//                if (dataSnapshot.child(UserCarryLocationName).exists()) {
//
//                    textView5.setText("Manage Location");
//
//
//
//                    gdLocationTool.setLocationName(dataSnapshot.child(UserCarryLocationName).child("locationName").getValue().toString());
//                    gdLocationTool.setLocationName(dataSnapshot.child(UserCarryLocationName).child("locationAddress").getValue().toString());
//                    gdLocationTool.setLocationName(dataSnapshot.child(UserCarryLocationName).child("locationCity").getValue().toString());
//                    gdLocationTool.setLocationName(dataSnapshot.child(UserCarryLocationName).child("locationCrewPark").getValue().toString());
//                    gdLocationTool.setLocationName(dataSnapshot.child(UserCarryLocationName).child("locationCrewParkCity").getValue().toString());
//
//                    if (dataSnapshot.child("chLocationInt").exists()) {
//                        gdLocationTool.setCbLocationInt(true);
//                        cbLocationInt.setClickable(gdLocationTool.getCbLocationInt());
//                    }
//                    if (dataSnapshot.child("chLocationExt").exists()) {
//                        gdLocationTool.setCbLocationExt(true);
//                        cbLocationExt.setClickable(gdLocationTool.getCbLocationExt());
//                    }
//
//                }
//                editTextLocationName.setText(gdLocationTool.getLocationName());
//                editTextLocationAddress.setText(gdLocationTool.getLocationAddress());
//                editTextLocationCity.setText(gdLocationTool.getLocationCity());
//                editTextLocationCrewPark.setText(gdLocationTool.getLocationCrewPark());
//                editTextLocationCrewParkCity.setText(gdLocationTool.getLocationCrewParkCity());
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//
//
    }

    private void FetchLocationDataFromDB1() {

        UserCarryActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");


        CCSMainDB.child("Shows")
                .child(UserCarryActiveShow.toString().trim())
                .child("Locations")
                .child("Locations")



                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {

                            GDLocationTool gdLocationTool = dataSnapshot.getValue(GDLocationTool.class);

                            //Location
                            //editTextLocationName = findViewById(R.id.editTextLocationName);
                            //editTextLocationName.setText("Landon's House");

                           editTextLocationNickname = findViewById(R.id.editTextLocationNickname);

                           editTextLocationNickname.setText(gdLocationTool.getLocationNickname());


                            editTextLocationAddress.setText((gdLocationTool.getLocationAddress()));
                            editTextLocationCity.setText((gdLocationTool.getLocationCity()));
                            //Location Contact
                            editTextLocationContact.setText((gdLocationTool.getLocationContact()));
                            editTextLocationContactPhoneNumber.setText((gdLocationTool.getLocationContactPhoneNumber()));
                            editTextLocationContactEmail.setText((gdLocationTool.getLocationContactEmail()));
                            editTextLocationContactHours.setText((gdLocationTool.getLocationContactHours()));

                            //Crew Park
                            editTextLocationCrewParkName.setText((gdLocationTool.getLocationCrewParkName()));
                            editTextLocationCrewParkNickname.setText((gdLocationTool.getLocationCrewParkNickname()));
                            editTextLocationCrewParkAddress.setText((gdLocationTool.getLocationCrewParkAddress()));
                            editTextLocationCrewParkCity.setText((gdLocationTool.getLocationCrewParkCity()));
                            //Crew Park Contact
                            editTextLocationCrewParkContact.setText((gdLocationTool.getLocationCrewParkContact()));
                            editTextLocationCrewParkContactPhoneNumber.setText((gdLocationTool.getLocationCrewParkContactPhoneNumber()));
                            editTextLocationCrewParkContactEmail.setText((gdLocationTool.getLocationCrewParkContactEmail()));
                            editTextLocationCrewParkContactHours.setText((gdLocationTool.getLocationCrewParkContactHours()));

                            //Nearest Hospital
                            editTextLocationNearestHospital.setText(gdLocationTool.getLocationNearestHospital());
                            editTextLocationNearestHospitalAddress.setText(gdLocationTool.getLocationNearestHospitalAddress());
                            editTextLocationNearestHospitalCity.setText(gdLocationTool.getLocationNearestHospitalCity());
                            editTextLocationNearestHospitalContactPhoneNumber.setText(gdLocationTool.getLocationNearestHospitalContactPhoneNumber());

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });






//        if(CCSMainDB.child("Shows")
//                .child(activeShow)
//                .child("Locations")
//                .child("Locations")
//                .child(UserCarryLocationName)
//
//
//                != null) {
//            //SendUserToSignInActivity();
//        }
//        else {
//            //CheckUserExistence();
//        }
    }

    private void CollectLocationDataToDB() {
        UserCarryActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");

        GDLocationTool gdLocationTool = new GDLocationTool();
        //gdLocationTool.setLocationName(editTextLocationName.getText().toString());

        String locationName = editTextLocationName.getText().toString();
        if (editTextLocationName != null) {
            gdLocationTool.setLocationName(locationName);
        }


        String locationAddress= editTextLocationAddress.getText().toString();
        if (editTextLocationAddress != null) {
            gdLocationTool.setLocationAddress(locationAddress);


            String locationCity = editTextLocationCity.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationCity(locationCity);
            }

            String locationNickname = editTextLocationNickname.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationNickname(locationNickname);
            }

            String locationContact = editTextLocationContact.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationContact(locationContact);
            }
            //***
            String locationContactPhoneNumber = editTextLocationContactPhoneNumber.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationContactPhoneNumber(locationContactPhoneNumber);
            }


            String locationContactEmail = editTextLocationContactEmail.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationContactEmail(locationContactEmail);
            }

            String locationContactHours = editTextLocationContactHours.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationContactHours(locationContactHours);
            }

            //Crew Park Edit Texts
            String locationCrewParkName = editTextLocationCrewParkName.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationCrewParkName(locationCrewParkName);
            }


            String locationCrewParkCity = editTextLocationCrewParkCity.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationCrewParkCity(locationCrewParkCity);
            }

            String locationCrewParkNickname = editTextLocationCrewParkName.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationCrewParkNickname(locationCrewParkNickname);
            }

            String locationCrewParkAddress = editTextLocationCrewParkAddress.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationCrewParkAddress(locationCrewParkAddress);
            }

            String locationCrewParkContact = editTextLocationCrewParkContact.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationCrewParkContact(locationCrewParkContact);
            }

            String locationCrewParkContactPhoneNumber = editTextLocationCrewParkContactPhoneNumber.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationCrewParkContactPhoneNumber(locationCrewParkContactPhoneNumber);
            }

            String locationCrewParkContactEmail = editTextLocationCrewParkContactEmail.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationCrewParkContactEmail(locationCrewParkContactEmail);
            }

            String locationCrewParkContactHours = editTextLocationCrewParkContactHours.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationCrewParkContactHours(locationCrewParkContactHours);
            }


            //Location Safety
            String locationNearestHospital = editTextLocationNearestHospital.getText().toString();
            ;
            if (editTextLocationName != null) {
                gdLocationTool.setLocationNearestHospital(locationNearestHospital);
            }

            String locationNearestHospitalAddress = editTextLocationNearestHospital.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationNearestHospitalAddress(locationNearestHospitalAddress);
            }

            String locationNearestHospitalContactPhoneNumber = editTextLocationNearestHospitalContactPhoneNumber.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationNearestHospitalContactPhoneNumber(locationNearestHospitalContactPhoneNumber);
            }

            String locationNearestHospitalCity = editTextLocationNearestHospitalCity.getText().toString();
            if (editTextLocationName != null) {
                gdLocationTool.setLocationNearestHospitalCity(locationNearestHospitalCity);
            }


            Boolean locationInt = cbLocationInt.isChecked();

            gdLocationTool.setLocationInt(locationInt);
            Boolean locationExt = cbLocationExt.isChecked();
            gdLocationTool.setLocationExt(locationExt);
            Boolean locationCrewParkCrew = cbLocationCrewParkCrew.isChecked();
            gdLocationTool.setLocationCrewParkCrew(locationCrewParkCrew);
            Boolean locationCrewParkBG = cbLocationInt.isChecked();
            gdLocationTool.setLocationCrewParkBG(locationCrewParkBG);

//        //Adding Data to Locations Department Folder
            CCSMainDB.child("Shows")
                    .child(UserCarryActiveShow)
                    .child("Locations")
                    .child("Locations")
                    .child(locationName)
                    .setValue(gdLocationTool);



//        //Adding Data to Locations Department Folder
//
//        CCSMainDB.child("Shows")
//                .child(activeShow)
//                .child("Locations")
//                .child("Locations")
//                .child(gdLocationTool.getLocationName())
//                .setValue(gdLocationTool);
//
//        CCSMainDB.child("Shows")
//                .child(activeShow)
//                .child("Locations")
//                .child("Locations")
//                .child(gdLocationTool.getLocationName())
//                .updateChildren(locationHashMap);
////-----------------------------------------------------------------------------------------------
            //Adding Data to Show Day
//            CCSMainDB.child("Shows")
//                    .child(UserCarryActiveSHow)
//                    .child("Days")
//                    .child(UserCarryDayDate)
//                    .child("Locations")
//                    .child(gdLocationTool.getLocationName())
//                    .setValue(gdLocationTool);

//            CCSMainDB.child("Shows")
//                    .child(activeShow)
//                    .child("Days")
//                    .child(onBindUserSelectedDayDate)
//                    .child("Locations")
//                    .child(gdLocationTool.getLocationName())
//                    .setValue(gdLocationTool);
        }













//        gdLocationTool.setCbLocationInt(cbLocationInt.isChecked());
//        gdLocationTool.setCbLocationInt(cbLocationInt.isChecked());
//
//        String locationName = editTextLocationName.getText().toString();
//        String locationAddress = editTextLocationAddress.getText().toString();
//        String locationCity = editTextLocationCity.getText().toString();
//        String locationCrewPark = editTextLocationCrewParkName.getText().toString();
//        String locationCrewParkCity = editTextLocationCrewParkCity.getText().toString();
//
//        Boolean locationInt;
//        Boolean LocationExt;
//
//
//        HashMap locationHashMap = new HashMap();
//
//        locationHashMap.put("locationName", locationName);
//        locationHashMap.put("locationAddress", locationAddress);
//        locationHashMap.put("locationCity", locationCity);
//        locationHashMap.put("locationCrewPark", locationCrewPark);
//        locationHashMap.put("firstCrewParkCity", locationCrewParkCity);
//
//
//        gdLocationTool.setLocationName(editTextLocationName.getText().toString());
//
////_

        SendUserToManageLocationsActivity();
    }

    private void SendUserToManageLocationsActivity() {

        Intent manageLocationsIntent = new Intent(AddLocationActivity.this, ManageLocationsActivity.class);
        manageLocationsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        manageLocationsIntent.putExtra("UserCarryDayDate", UserCarryDayDate.toString().trim() );
        manageLocationsIntent.putExtra("UserCarryActiveShow", UserCarryActiveShow );
        manageLocationsIntent.putExtra("UserCarryDepartment", UserCarryDepartment);
        startActivity(manageLocationsIntent);


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
        Intent intent = new Intent(AddLocationActivity.this, MainActivity.class);
        startActivity(intent);
    }



    private void SendUserToEditDayActivity() {



        Intent editDayIntent = new Intent(AddLocationActivity.this, EditDayActivity.class);
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

    private void SendUserToSignInActivity()
    {
        Intent loginIntent = new Intent(AddLocationActivity.this, SignInActivity.class);
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

    }



}

//    @Override
//    public void onClick(View v) {
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.createDayB:
//                    startActivity(new Intent(this, CreateDayActivity.class));
//                    break;
//
//    }
//}}}
