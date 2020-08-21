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

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class GDLocationTool {

    private String locationName, locationAddress
            , locationCity, locationNickname
            , locationContact, locationContactPhoneNumber
            , locationContactEmail, locationContactHours
            //Crew Park Edit Texts
            , locationCrewParkName, locationCrewParkCity
            , locationCrewParkNickname, locationCrewParkAddress
            ,locationCrewParkContact, locationCrewParkContactPhoneNumber
            ,locationCrewParkContactEmail, locationCrewParkContactHours
            //Location Safety
            , locationNearestHospital, locationNearestHospitalAddress
            ,locationNearestHospitalContactPhoneNumber, locationNearestHospitalCity;

    private Boolean locationInt, locationExt
            , locationCrewParkCrew, locationCrewParkBG;

    public GDLocationTool(){

    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationNickname() {
        return locationNickname;
    }

    public void setLocationNickname(String locationNickname) {
        this.locationNickname = locationNickname;
    }

    public String getLocationContact() {
        return locationContact;
    }

    public void setLocationContact(String locationContact) {
        this.locationContact = locationContact;
    }

    public String getLocationContactPhoneNumber() {
        return locationContactPhoneNumber;
    }

    public void setLocationContactPhoneNumber(String locationContactPhoneNumber) {
        this.locationContactPhoneNumber = locationContactPhoneNumber;
    }

    public String getLocationContactEmail() {
        return locationContactEmail;
    }

    public void setLocationContactEmail(String locationContactEmail) {
        this.locationContactEmail = locationContactEmail;
    }

    public String getLocationContactHours() {
        return locationContactHours;
    }

    public void setLocationContactHours(String locationContactHours) {
        this.locationContactHours = locationContactHours;
    }

    public String getLocationCrewParkName() {
        return locationCrewParkName;
    }

    public void setLocationCrewParkName(String locationCrewParkName) {
        this.locationCrewParkName = locationCrewParkName;
    }

    public String getLocationCrewParkCity() {
        return locationCrewParkCity;
    }

    public void setLocationCrewParkCity(String locationCrewParkCity) {
        this.locationCrewParkCity = locationCrewParkCity;
    }

    public String getLocationCrewParkNickname() {
        return locationCrewParkNickname;
    }

    public void setLocationCrewParkNickname(String locationCrewParkNickname) {
        this.locationCrewParkNickname = locationCrewParkNickname;
    }

    public String getLocationCrewParkAddress() {
        return locationCrewParkAddress;
    }

    public void setLocationCrewParkAddress(String locationCrewParkAddress) {
        this.locationCrewParkAddress = locationCrewParkAddress;
    }

    public String getLocationCrewParkContact() {
        return locationCrewParkContact;
    }

    public void setLocationCrewParkContact(String locationCrewParkContact) {
        this.locationCrewParkContact = locationCrewParkContact;
    }

    public String getLocationCrewParkContactPhoneNumber() {
        return locationCrewParkContactPhoneNumber;
    }

    public void setLocationCrewParkContactPhoneNumber(String locationCrewParkContactPhoneNumber) {
        this.locationCrewParkContactPhoneNumber = locationCrewParkContactPhoneNumber;
    }

    public String getLocationCrewParkContactEmail() {
        return locationCrewParkContactEmail;
    }

    public void setLocationCrewParkContactEmail(String locationCrewParkContactEmail) {
        this.locationCrewParkContactEmail = locationCrewParkContactEmail;
    }

    public String getLocationCrewParkContactHours() {
        return locationCrewParkContactHours;
    }

    public void setLocationCrewParkContactHours(String locationCrewParkContactHours) {
        this.locationCrewParkContactHours = locationCrewParkContactHours;
    }

    public String getLocationNearestHospital() {
        return locationNearestHospital;
    }

    public void setLocationNearestHospital(String locationNearestHospital) {
        this.locationNearestHospital = locationNearestHospital;
    }

    public String getLocationNearestHospitalAddress() {
        return locationNearestHospitalAddress;
    }

    public void setLocationNearestHospitalAddress(String locationNearestHospitalAddress) {
        this.locationNearestHospitalAddress = locationNearestHospitalAddress;
    }

    public String getLocationNearestHospitalContactPhoneNumber() {
        return locationNearestHospitalContactPhoneNumber;
    }

    public void setLocationNearestHospitalContactPhoneNumber(String locationNearestHospitalContactPhoneNumber) {
        this.locationNearestHospitalContactPhoneNumber = locationNearestHospitalContactPhoneNumber;
    }

    public String getLocationNearestHospitalCity() {
        return locationNearestHospitalCity;
    }

    public void setLocationNearestHospitalCity(String locationNearestHospitalCity) {
        this.locationNearestHospitalCity = locationNearestHospitalCity;
    }

    public Boolean getLocationInt() {
        return locationInt;
    }

    public void setLocationInt(Boolean locationInt) {
        this.locationInt = locationInt;
    }

    public Boolean getLocationExt() {
        return locationExt;
    }

    public void setLocationExt(Boolean locationExt) {
        this.locationExt = locationExt;
    }

    public Boolean getLocationCrewParkCrew() {
        return locationCrewParkCrew;
    }

    public void setLocationCrewParkCrew(Boolean locationCrewParkCrew) {
        this.locationCrewParkCrew = locationCrewParkCrew;
    }

    public Boolean getLocationCrewParkBG() {
        return locationCrewParkBG;
    }

    public void setLocationCrewParkBG(Boolean locationCrewParkBG) {
        this.locationCrewParkBG = locationCrewParkBG;
    }
}
