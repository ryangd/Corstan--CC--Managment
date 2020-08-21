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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.widget.AdapterView.OnItemSelectedListener;

import com.example.corstan__cc__managment.Model.GDUserTool;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class SignUpProfileActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {



    private ProgressBar progressBar;

    //Edit Texts
    private EditText FullName, UserName;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar mToolbar;

    private TextView textViewNameTest;

    //Assign Check Boxes - Location(Geographic)
    private CheckBox checkBoxVancouver, checkBoxToronto, checkBoxMontreal,
    //Position
    checkBoxPA, checkBoxPAPrep, checkBoxGrip, checkBoxLampOp,
    //Union Affiliation
    //DGC
    checkBoxDGCPermitee, checkBoxDGCMember,
    //IASTE
    checkBoxIASTEPermitee, checkBoxIASTEMember,
    //ACFC
    checkBoxACFCPermitee, checkBoxACFCMember;

    private String firstName;
    private String lastName;

//??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????



    private CircleImageView ProfileImage;

    //Button
    private Button SaveInformationButton, ManageShowB;

    private CircleImageView NavProfileImage;
    private TextView NavProfileFullName, NavActiveShow, NavUserPosition, dayDate;

    //Firebase database Refrence
    private DatabaseReference CCSMainDB;
    private StorageReference CCSStorage;




    //Firebase Auth
    private FirebaseAuth mAuth;

    private String currentUserID, countrySpinnerResult, stateSpinnerResult
            , citySpinnerResult, departmentSpinnerResult, positionSpinnerResult
            , countryDBOutput, stateDBOutput, cityDBOutput, departmentDBOutput;

    static String Canada, Vancouver, Victoria;

    private TextView countryTextView, stateTextView, cityTextView, primaryDepartmentTextView, primaryPositionTextView;

    private EditText FirstName, LastName, UserEmail, UserPhoneNumber;


    final static int Gallery_Pick = 1;


    private Spinner countrySpinner, stateSpinner, citySpinner, departmentSpinner, positionSpinner;

    private ArrayAdapter<CharSequence> countryAdapter, naCAProvinceAdapter
            , naUSAStateAdapter, naCABCCityAdapter
            , naCAABCityAdapter, naCAONCityAdapter, naUSACAAdapter
            , naUSAGAAdapter, naUSANYAdapters, naUSAWAAdapter
            , departmentAdapter, productionAdapter
            ,adAdapter, accountingAdapter
            ,cameraAdapter, scriptSuperviserAdapter, lightingAdapter, gripsAdapter, soundAdapter,
            vfxAdapter, spfxAdapter, makeUpAdapter, hairAdapter, costumesAdapter, artDepartmentAdapter,
            setDecorationAdapter, propsAdapter, constructionAdapter, paintAdapter, greenAdapter,
            transportationAdapter, locationsAdapter, facsAdapter, cateringAdapter, securityAdapter, playbackAdapter;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_profile2);


        CCSMainDB = FirebaseDatabase.getInstance().getReference();
        CCSStorage = FirebaseStorage.getInstance().getReference();
        //createDatabaseForm();
        GDUserTool gdUserTool = new GDUserTool();
        //Set Variables


        //Edit Texts
        FirstName = findViewById(R.id.editTextFirstName);
        LastName = findViewById(R.id.editTextLastName);

        UserEmail = findViewById(R.id.editTextEmail);
        UserPhoneNumber = findViewById(R.id.editTextPhoneNumber);


        //Text View Test
//        textViewNameTest = findViewById(R.id.textViewNameTest);
//
//        //Check Boxes
//        //Location (Geographic)
//        checkBoxVancouver = findViewById(R.id.checkBoxVancouver);
//        checkBoxToronto = findViewById(R.id.checkBoxToronto);
//        checkBoxMontreal = findViewById(R.id.checkBoxMontreal);
//
//        //Position
//        checkBoxPA = findViewById(R.id.checkBoxPA);
//        checkBoxPAPrep = findViewById(R.id.checkBoxPAPrep);
//        checkBoxGrip = findViewById(R.id.checkBoxGrip);
//        checkBoxLampOp = findViewById(R.id.checkBoxPALampOp);
//
//        //Union Affiliation
//        //DGC
//        checkBoxDGCPermitee = findViewById(R.id.checkBoxDGCPermitee);
//        checkBoxDGCMember = findViewById(R.id.checkBoxDGCMember);
//        //IASTE
//        checkBoxIASTEPermitee = findViewById(R.id.checkBoxIASTEPermitee);
//        checkBoxIASTEMember = findViewById(R.id.checkBoxIASTEMember);
//        //ACFC
//        checkBoxACFCPermitee = findViewById(R.id.checkBoxACFCPermitee);
//        checkBoxACFCMember = findViewById(R.id.checkBoxACFCMember);

        //Button
        SaveInformationButton = findViewById(R.id.sign_up_information_button);
        ManageShowB = findViewById(R.id.manage_show_b);

        //Images
        ProfileImage = findViewById(R.id.sign_up_profile_image);

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Profile");

        drawerLayout = findViewById(R.id.drawable_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_closed);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView = findViewById(R.id.navigation_view);
//        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
//        NavProfileImage = navView.findViewById(R.id.nav_circleProfilePhoto);
//        NavProfileFullName = navView.findViewById(R.id.nav_user_full_name);
//        NavActiveShow = navView.findViewById(R.id.nav_user_active_show);
//        NavUserPosition = navView.findViewById(R.id.nav_user_position);

        //Spinners
        countrySpinner = findViewById(R.id.countrySpinner);
        stateSpinner = findViewById(R.id.stateSpinner);
        citySpinner = findViewById(R.id.citySpinner);

        countryTextView = findViewById(R.id.countryTextView);
        stateTextView = findViewById(R.id.stateTextView);
        cityTextView = findViewById(R.id.cityTextView);

        departmentSpinner = findViewById(R.id.primary_department_spinner);
        positionSpinner = findViewById(R.id.positionSpinner);

        //Text Views
        primaryDepartmentTextView = findViewById(R.id.primaryDepartmentTextView);
        primaryPositionTextView = findViewById(R.id.primaryPositionTextView);


        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter
                .createFromResource(getApplicationContext(),
                        R.array.country_array,
                        android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);

        countryTextView.setText(countryDBOutput);


        LocationCollector();
        DepartmentCollector();

        //Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        currentUserID = mAuth.getCurrentUser().getUid();

        progressBar = new ProgressBar(this);

        //CCSUserTool ccsUserTool = new CCSUserTool();

        SaveInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveAccountSetupInformation();

            }
        });

        ManageShowB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendUserToCreateJoinShow();
            }
        });


        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, Gallery_Pick);
            }
        });

        CCSMainDB
                .child("Users")
                .child("Management")
                .child(currentUserID)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    CountryPostitionCalculator();


                    if (dataSnapshot.hasChild("Profile Photo")) {

                        String image = dataSnapshot.child("Profile Photo")
                                .getValue()
                                .toString();
                        Picasso.get().load(image).placeholder(R.drawable.profile).into(ProfileImage);
                    }

                     if (dataSnapshot.hasChild("firstName")) {
                         RetrieveUserData();
                     }

                    else {
                        Toast.makeText(SignUpProfileActivity.this, "Please select profile photo first.", Toast.LENGTH_LONG).show();

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>TEST ACTIVITY<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private void SendUserToCreateJoinShow() {

        Intent createJoinShowIntent = new Intent(SignUpProfileActivity.this, CreateJoinShowActivity.class);
        createJoinShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(createJoinShowIntent);
        finish();
    }

    private void RetrieveUserData() {

        CCSMainDB.child("Users").child("Management")
                .child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                FirstName = findViewById(R.id.editTextFirstName);

                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("activeShow").exists())

                        if (dataSnapshot.child("firstName").exists()) {
                            FirstName.setText(dataSnapshot.child("firstName").getValue().toString().trim());
                        }
                    if (dataSnapshot.child("lastName").exists()) {
                        LastName.setText(dataSnapshot.child("lastName").getValue().toString().trim());
                    }
                    if (dataSnapshot.child("userEmail").exists()) {
                        UserEmail.setText(dataSnapshot.child("userEmail").getValue().toString().trim());
                    }
                    if (dataSnapshot.child("userPhoneNumber").exists()) {
                        UserPhoneNumber.setText(dataSnapshot.child("userPhoneNumber").getValue().toString().trim());
                    }
//????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????

                    //DB to Spinner Magic
//????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????

                    //User Location
                    if (dataSnapshot.child("userCountry").exists()) {
                        countryDBOutput = dataSnapshot.child("userCountry").getValue().toString();
                        countryTextView.setText(countryDBOutput);
                    }
                    if (dataSnapshot.child("userState").exists()) {
                        stateDBOutput = dataSnapshot.child("userState").getValue().toString();
                        stateTextView.setText(stateDBOutput);
                    } if (dataSnapshot.child("userCity").exists()) {
                        cityDBOutput = dataSnapshot.child("userCity").getValue().toString();
                        cityTextView.setText(cityDBOutput);
                    }


                }


//                else {
//                    CCSMainDB.child("Users").child("management").child(currentUserID)
//                            .child("activeShow").setValue(null);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void CountryPostitionCalculator() {



        CCSMainDB.child("Users")
                .child("Management")
                .child(currentUserID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child("userCountry").exists()) {
                            countryDBOutput = (dataSnapshot.child("userCountry").toString());

                        }
                    }








            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        })

        //countryDBOutput
        ;
    }


    private void DepartmentCollector() {
        //Departmant Spinner


        ArrayAdapter<CharSequence> departmentAdapter = ArrayAdapter.createFromResource(this,
                R.array.primary_department_array,
                android.R.layout.simple_spinner_item);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(departmentAdapter);
        departmentSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //position Spinner
                departmentSpinnerResult = parent.getItemAtPosition(position).toString();

                if (position == 0) {
//                    FullName.setError("Please select your primary 'Department'");
//                    FullName.requestFocus();
//                    return;

//                    naCAProvinceAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
//                            R.array.na_ca_province_array,
//                            android.R.layout.simple_spinner_item);
//                    naCAProvinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    departmentSpinnerResult.se



                }
                else if (position == 1) {
                    //USA productionAdapter
                    productionAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.production_array,
                            android.R.layout.simple_spinner_item);
                    productionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(productionAdapter);

                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                } else if (position == 2) {
                    //USA adAdapter
                    adAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.assistant_director_array,
                            android.R.layout.simple_spinner_item);
                    adAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(adAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (position == 3) {
                    //USA accountingAdapter
                    accountingAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.accounting_array,
                            android.R.layout.simple_spinner_item);
                    accountingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(accountingAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (position == 4) {
                    //USA States Adapter
                    cameraAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.camera_array,
                            android.R.layout.simple_spinner_item);
                    cameraAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(cameraAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                else if (position == 5) {
                    //USA States Adapter
                    scriptSuperviserAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.script_supervisor_array,
                            android.R.layout.simple_spinner_item);
                    scriptSuperviserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(scriptSuperviserAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                else if (position == 6) {
                    //USA States Adapter
                    lightingAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.lighting_array,
                            android.R.layout.simple_spinner_item);
                    lightingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(lightingAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                else if (position == 7) {
                    //USA States Adapter
                    gripsAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.grip_array,
                            android.R.layout.simple_spinner_item);
                    gripsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(gripsAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                }

                else if (position == 8) {
                    //USA States Adapter
                    soundAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.sound_array,
                            android.R.layout.simple_spinner_item);
                    soundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(soundAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();


                }

                else if (position == 9) {
                    //USA States Adapter
                   vfxAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.vfx_array,
                            android.R.layout.simple_spinner_item);
                    vfxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(vfxAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }

                else if (position == 10) {
                    //USA States Adapter
                    spfxAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.spfx_array,
                            android.R.layout.simple_spinner_item);
                    spfxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(spfxAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                else if (position == 11) {
                    //USA States Adapter
                    makeUpAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.makeup_array,
                            android.R.layout.simple_spinner_item);
                    makeUpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(makeUpAdapter);


                }

                else if (position == 12) {
                    //USA States Adapter
                    hairAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.hair_array,
                            android.R.layout.simple_spinner_item);
                    hairAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(hairAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                else if (position == 13) {
                    //USA States Adapter
                    costumesAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.costumes_array,
                            android.R.layout.simple_spinner_item);
                    costumesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(costumesAdapter);


                }

                else if (position == 14) {
                    //USA States Adapter
                    artDepartmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.art_Department_array,
                            android.R.layout.simple_spinner_item);
                    artDepartmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(artDepartmentAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }

                else if (position == 15) {
                    //USA States Adapter
                    setDecorationAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.set_decoration_array,
                            android.R.layout.simple_spinner_item);
                    setDecorationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(setDecorationAdapter);


                }

                else if (position == 16) {
                    //USA States Adapter
                    propsAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.props_array,
                            android.R.layout.simple_spinner_item);
                    propsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(propsAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }

                else if (position == 17) {
                    //USA States Adapter
                    constructionAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.construction_array,
                            android.R.layout.simple_spinner_item);
                    constructionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(constructionAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });



                }

                else if (position == 18) {
                    //USA States Adapter
                    paintAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.paint_array,
                            android.R.layout.simple_spinner_item);
                    paintAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(paintAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }

                else if (position == 19) {
                    //USA States Adapter
                    greenAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.greens_array,
                            android.R.layout.simple_spinner_item);
                    greenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(greenAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }

                else if (position == 20) {
                    //USA States Adapter
                    transportationAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.transportation_array,
                            android.R.layout.simple_spinner_item);
                    transportationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(transportationAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }

                else if (position == 21) {
                    //USA States Adapter
                    locationsAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.locations_array,
                            android.R.layout.simple_spinner_item);
                    locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(locationsAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }

                else if (position == 22) {
                    //USA States Adapter
                    facsAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.facs_array,
                            android.R.layout.simple_spinner_item);
                    facsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(facsAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }

                else if (position == 23) {
                    //USA States Adapter
                    cateringAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.catering_array,
                            android.R.layout.simple_spinner_item);
                    cateringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(cateringAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }

                else if (position == 24) {
                    //USA States Adapter
                    securityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.security_array,
                            android.R.layout.simple_spinner_item);
                    securityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(securityAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }

                else if (position == 25) {
                    //USA States Adapter
                    playbackAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.playback_array,
                            android.R.layout.simple_spinner_item);
                    playbackAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    positionSpinner.setAdapter(playbackAdapter);
                    positionSpinnerResult = parent.getItemAtPosition(position).toString();
                    positionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            positionSpinnerResult = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void LocationCollector() {
        //Inital Spinner
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(this,
                R.array.country_array,
                android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);
        countrySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //State Spinner
                countrySpinnerResult = parent.getItemAtPosition(position).toString();

                if (position == 0 ) {
//                    FullName.setError("Please select your primary 'Department'");
//                    FullName.requestFocus();
//                    return;
                }

                if (position == 1) {

                    naCAProvinceAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.na_ca_province_array,
                            android.R.layout.simple_spinner_item);
                    naCAProvinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    stateSpinner.setAdapter(naCAProvinceAdapter);
                    stateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //Canada City Adapter
                            stateSpinnerResult = parent.getItemAtPosition(position).toString();

                            if (position == 0){
//                                FullName.setError("Please select your 'Country'");
//                                FullName.requestFocus();
//                                return;

                            }

                            if (position == 1) {
                                naCAABCityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.na_ca_ab_hubs,
                                        android.R.layout.simple_spinner_item);
                                naCAABCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                citySpinner.setAdapter(naCAABCityAdapter);
                                citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        citySpinnerResult = parent.getItemAtPosition(position).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });


                            }

                            if (position == 2) {
                                naCABCCityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.na_ca_bc_hubs,
                                        android.R.layout.simple_spinner_item);
                                naCABCCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                citySpinner.setAdapter(naCABCCityAdapter);
                                citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        citySpinnerResult = parent.getItemAtPosition(position).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });


                            }

                            if (position == 3) {
                                naCAONCityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.na_ca_on_hubs,
                                        android.R.layout.simple_spinner_item);
                                naCAONCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                citySpinner.setAdapter(naCAONCityAdapter);
                                citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        positionSpinnerResult = parent.getItemAtPosition(position).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    {

                    }
                }

                if (position == 2){
                    //USA States Adapter
                    naUSAStateAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.na_usa_states_array,
                            android.R.layout.simple_spinner_item);
                    naUSAStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    stateSpinner.setAdapter(naUSAStateAdapter);
                    stateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            stateSpinnerResult = parent.getItemAtPosition(position).toString();

                            if (position == 0) {

                            }
                            if (position == 1) {
                                naUSACAAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.na_usa_ca_hubs,
                                        android.R.layout.simple_spinner_item);
                                naUSACAAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                citySpinner.setAdapter(naUSACAAdapter);
                                citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        citySpinnerResult = parent.getItemAtPosition(position).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });


                            }
                            if (position == 2) {
                                naUSAGAAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.na_usa_ga_hubs,
                                        android.R.layout.simple_spinner_item);
                                naUSAGAAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                citySpinner.setAdapter(naUSAGAAdapter);
                                citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        citySpinnerResult = parent.getItemAtPosition(position).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });


                            }
                            if (position == 3) {
                                naUSANYAdapters = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.na_usa_ny_hubs,
                                        android.R.layout.simple_spinner_item);
                                naUSANYAdapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                citySpinner.setAdapter(naUSANYAdapters);
                                citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        citySpinnerResult = parent.getItemAtPosition(position).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });


                            }


                            if (position == 4) {
                                naUSAWAAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.na_usa_wa_hubs,
                                        android.R.layout.simple_spinner_item);
                                naUSAWAAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                citySpinner.setAdapter(naUSAWAAdapter);
                                citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        citySpinnerResult = parent.getItemAtPosition(position).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });


                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Pick && resultCode == RESULT_OK && data != null) {
            Uri ImageUri = data.getData();
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                final StorageReference filePath = CCSStorage.child("Users")
                        .child("Management")
                        .child(currentUserID)
                        .child("Profile Images")
                        .child(currentUserID + ".jpg");

                filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                final String downloadUrl = uri.toString();

                                CCSMainDB
                                        .child("Users")
                                        .child("Management")
                                        .child(currentUserID)
                                        .child("Profile Photo")
                                        .setValue(downloadUrl)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpProfileActivity.this, "Bio-scan: Complete", Toast.LENGTH_LONG).show();
                                        } else {
                                            String message = task.getException().getMessage();
                                            Toast.makeText(SignUpProfileActivity.this, "Error" + message, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }

                        });
                    }
                });
            }
        }
    }

    private void SaveAccountSetupInformation() {

        String firstName = FirstName.getText().toString();
        String lastName = LastName.getText().toString();

        String userCountry = countrySpinnerResult;
        String userState = stateSpinnerResult;
        String userCity = citySpinnerResult;

        String userPrimaryDepartment = departmentSpinnerResult;
        String userPrimaryPosition = positionSpinnerResult;

        String userEmail = UserEmail.getText().toString();
        String userPhoneNumber = UserPhoneNumber.getText().toString();


        if (firstName.isEmpty()) {
            FirstName.setError("Please provide your Full Name");
            FirstName.requestFocus();
            return;

        }
        if (lastName.isEmpty()) {
            LastName.setError("Please set a Username");
            LastName.requestFocus();
            return;
        }
        else {
            HashMap userMap = new HashMap();
            userMap.put("firstName", firstName);
            userMap.put("lastName", lastName);

            //userMap.put("activeShow", "");

            userMap.put("userEmail", userEmail);
            userMap.put("userPhoneNumber", userPhoneNumber);

            userMap.put("dob", "");

            userMap.put("userId", currentUserID);

            userMap.put("userCountry", userCountry);
            userMap.put("userState", userState);
            userMap.put("userCity", userCity);

            userMap.put("userPrimaryDepartment", userPrimaryDepartment);
            userMap.put("userPrimaryPosition", userPrimaryPosition);





            CCSMainDB.child("Users")
                    .child("Management")
                    .child(currentUserID)
                    .updateChildren(userMap)
                    .addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpProfileActivity.this, "Your account has been implemented...", Toast.LENGTH_LONG).show();
                        SendUserToMainActivity();
                        finish();

                    } else {
                        String message = task.getException().getMessage();
                        Toast.makeText(SignUpProfileActivity.this, "Access to nuclear weapons DENIED: " + message, Toast.LENGTH_LONG).show();


                    }

                }
            });
        }

    }

    private void SendUserToMainActivity() {
        Intent signUpProfileIntent = new Intent(SignUpProfileActivity.this, MainActivity.class);
        signUpProfileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signUpProfileIntent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //onItem Selected for countrySpinner



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





//    public void updateUserInfo1() {
//
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            for (UserInfo profile : user.getProviderData()) {
//
//                //databaseUsers = FirebaseDatabase.getInstance().getReference();
//                CCSUserTool ccsUserTool = new CCSUserTool ();
//
//                // UID specific to the user
//                String uid = profile.getUid();
//                String userId = profile.getUid();
//
//                //String for "First" and "Last" "Name"
////                ccsUserTool.setFirstName(editTextFirstName.getText().toString().trim());
////                ccsUserTool.setLastName(editTextLastName.getText().toString().trim());
//
//                //Booleans for Location
//                ccsUserTool.setLocationVancouver(checkBoxVancouver.isChecked());
//                ccsUserTool.setLocationToronto(checkBoxToronto.isChecked());
//                ccsUserTool.setLocationMontreal(checkBoxMontreal.isChecked());
//
//                //Booleans for Position
//                ccsUserTool.setPositionPA(checkBoxPA.isChecked());
//                ccsUserTool.setPositionPAPrep(checkBoxPAPrep.isChecked());
//                ccsUserTool.setPositionGrip(checkBoxGrip.isChecked());
//                ccsUserTool.setPositionLampOp(checkBoxLampOp.isChecked());
//
//                //Booleans for Union Affiliation
//                ccsUserTool.setuADGCP(checkBoxDGCPermitee.isChecked());
//                ccsUserTool.setuADGCM(checkBoxDGCMember.isChecked());
//                ccsUserTool.setuAIASTEP(checkBoxIASTEPermitee.isChecked());
//                ccsUserTool.setuAIASTEM(checkBoxIASTEMember.isChecked());
//                ccsUserTool.setuAACFCP(checkBoxACFCPermitee.isChecked());
//                ccsUserTool.setuAACFCM(checkBoxACFCMember.isChecked());
//
//                //databaseUsers.child("users").child(userId).setValue(ccsUserTool);
//
//            }
//        }
//    }
//
//}




//@Override
//public void onClick(View v) {
//showProfilePhotoSelector();
//}

//choose image override
//@Override
//protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//    super.onActivityResult(requestCode, resultCode, data);
//    if (requestCode == CHOOSE_IMAGE && requestCode == RESULT_OK && data != null && data.getData() != null) {
//
//        uriProfileImage = data.getData();
//        try {
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
//            imageViewProfilePhoto.setImageBitmap(bitmap);
//
//            uploadImageToFirebaseStorage();
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
//
//    }
//}
