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

package com.example.corstan__cc__managment.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corstan__cc__managment.CreateDayActivity;
import com.example.corstan__cc__managment.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class DayViewHolder extends RecyclerView.ViewHolder {

//RecyclerView.Adapter<DayViewHolder.ViewHolder> {


    public TextView dayDay;
    public TextView dayDate;
    public TextView dayAddress;
    public TextView dayCity;
    public TextView activeShow;

    public Button editDayB;

    public DayViewHolder(@NonNull View itemView, TextView dayDay, TextView dayDate, TextView dayAddress, TextView dayCity, TextView activeShow, Button editDayB) {
        super(itemView);
        this.dayDay = dayDay;
        this.dayDate = dayDate;
        this.dayAddress = dayAddress;
        this.dayCity = dayCity;
        this.activeShow = activeShow;
        this.editDayB = editDayB;
    }

    public TextView getDayDay() {
        return dayDay;
    }

    public void setDayDay(TextView dayDay) {
        this.dayDay = dayDay;
    }

    public TextView getDayDate() {
        return dayDate;
    }

    public void setDayDate(TextView dayDate) {
        this.dayDate = dayDate;
    }

    public TextView getDayAddress() {
        return dayAddress;
    }

    public void setDayAddress(TextView dayAddress) {
        this.dayAddress = dayAddress;
    }

    public TextView getDayCity() {
        return dayCity;
    }

    public void setDayCity(TextView dayCity) {
        this.dayCity = dayCity;
    }

    public TextView getActiveShow() {
        return activeShow;
    }

    public void setActiveShow(TextView activeShow) {
        this.activeShow = activeShow;
    }

    public Button getEditDayB() {
        return editDayB;
    }

    public void setEditDayB(Button editDayB) {
        this.editDayB = editDayB;
    }
}





