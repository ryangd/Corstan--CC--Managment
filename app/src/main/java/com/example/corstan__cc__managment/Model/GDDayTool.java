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

public class GDDayTool {
//????????????????????????????????????????????????????????????????????????????????????????????????????
    //                                  DO NOT DELETE
    //???????????????????????????????????????????????????????????????????????????????????????????????????\

    // showName
    // showName;

    //   dayDate,
    //    dayEpisodeName,
//    dayDayOfDay1
//    dayDayOfDay2
//    dayCrewCall,
//    dayLunchTime,
//    daySunUp,
//    daySunUpDn;
//
//    cbMainUnit,
//    cbSecondUnit;
//????????????????????????????????????????????????????????????????????????????????????????????????????????
    //Day Info
    private String showName;

    private String dayDate;
    private String dayEpisodeName;
    private String dayDayOfDay1;
    private String dayDayOfDay2;
    private String dayCrewCall;
    private String dayLunchTime;
    private String daySunUp;
    private String daySunUpDn;

    private Boolean cbMainUnit;
    private Boolean cbSecondUnit;

    public GDDayTool() {

    }

    public GDDayTool(String showName, String dayDate, String dayEpisodeName, String dayDayOfDay1, String dayDayOfDay2, String dayCrewCall, String dayLunchTime, String daySunUp, String daySunUpDn, Boolean cbMainUnit, Boolean cbSecondUnit) {
        this.showName = showName;
        this.dayDate = dayDate;
        this.dayEpisodeName = dayEpisodeName;
        this.dayDayOfDay1 = dayDayOfDay1;
        this.dayDayOfDay2 = dayDayOfDay2;
        this.dayCrewCall = dayCrewCall;
        this.dayLunchTime = dayLunchTime;
        this.daySunUp = daySunUp;
        this.daySunUpDn = daySunUpDn;
        this.cbMainUnit = cbMainUnit;
        this.cbSecondUnit = cbSecondUnit;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }

    public String getDayEpisodeName() {
        return dayEpisodeName;
    }

    public void setDayEpisodeName(String dayEpisodeName) {
        this.dayEpisodeName = dayEpisodeName;
    }

    public String getDayDayOfDay1() {
        return dayDayOfDay1;
    }

    public void setDayDayOfDay1(String dayDayOfDay1) {
        this.dayDayOfDay1 = dayDayOfDay1;
    }

    public String getDayDayOfDay2() {
        return dayDayOfDay2;
    }

    public void setDayDayOfDay2(String dayDayOfDay2) {
        this.dayDayOfDay2 = dayDayOfDay2;
    }

    public String getDayCrewCall() {
        return dayCrewCall;
    }

    public void setDayCrewCall(String dayCrewCall) {
        this.dayCrewCall = dayCrewCall;
    }

    public String getDayLunchTime() {
        return dayLunchTime;
    }

    public void setDayLunchTime(String dayLunchTime) {
        this.dayLunchTime = dayLunchTime;
    }

    public String getDaySunUp() {
        return daySunUp;
    }

    public void setDaySunUp(String daySunUp) {
        this.daySunUp = daySunUp;
    }

    public String getDaySunUpDn() {
        return daySunUpDn;
    }

    public void setDaySunUpDn(String daySunUpDn) {
        this.daySunUpDn = daySunUpDn;
    }

    public Boolean getCbMainUnit() {
        return cbMainUnit;
    }

    public void setCbMainUnit(Boolean cbMainUnit) {
        this.cbMainUnit = cbMainUnit;
    }

    public Boolean getCbSecondUnit() {
        return cbSecondUnit;
    }

    public void setCbSecondUnit(Boolean cbSecondUnit) {
        this.cbSecondUnit = cbSecondUnit;
    }
}