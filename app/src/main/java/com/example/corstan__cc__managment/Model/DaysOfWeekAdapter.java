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

package com.example.corstan__cc__managment.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.corstan__cc__managment.R;

import java.util.ArrayList;

public class DaysOfWeekAdapter extends ArrayAdapter <DayOfWeek> {

    public DaysOfWeekAdapter(Context context, ArrayList<DayOfWeek> dayOfWeekList) {
        super(context, 0, dayOfWeekList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.day_of_week_layout, parent, false
            );

        }
        TextView textViewDayOfWeek = convertView.findViewById(R.id.dayOfWeekText);
        DayOfWeek currentItem = getItem(position);

        if (currentItem != null) {
            textViewDayOfWeek.setText(currentItem.getDayOfWeek());
        }
        return convertView;

    }
}



