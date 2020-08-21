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

public class Day {

    private String dayDate, dayDay;

    public Day (){

    }

    public Day(String dayDate, String dayDay) {
        this.dayDate = dayDate;
        this.dayDay = dayDay;
    }

    public String getDayDay() {
        return dayDay;
    }

    public void setDayDay(String dayDay) {
        this.dayDay = dayDay;
    }

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }
}
