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

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SelectDayActivity extends AppCompatActivity {

    String activeShow, UserCarryDayDate, UserCarryLocationName, UserCarryActiveShow, UserCarryDepartment;

    TextView showTextView, datDateValidationTextView, textViewShowName, textViewShowNameCurrentDate, textViewSelectedDateWeekDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_day);

        if ((getIntent().getExtras()).getString("UserCarryActiveShow") != null) {
            UserCarryActiveShow = getIntent().getExtras().getString("UserCarryActiveShow");
        } else {
            UserCarryActiveShow = textViewShowName.getText().toString().trim();
        }
        if (getIntent().getExtras().getString("UserCarryDayDate") != null) {
            //onBindUserSelectedDayDate = getIntent().getExtras().getString("UserCarryDayDate");
        }
        if (getIntent().getExtras().getString("UserCarryDepartment") != null) {
            UserCarryDepartment = getIntent().getExtras().getString("UserCarryDepartment");
        }
    }
}
