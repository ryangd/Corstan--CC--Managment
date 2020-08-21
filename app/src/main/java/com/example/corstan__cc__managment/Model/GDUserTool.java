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

public class GDUserTool {



    private String userId;
    private String activeShow;

    private String profilePhoto;

    private String firstName;
    private String lastName;

    private String userPrimaryDepartment;
    private String userPrimaryPosition;


    //Booleans for Location
    private String userPhoneNumber;
    private String userEmail;

    private String userCountry;
    private String userState;
    private String activeCity;




    public GDUserTool(){

    }

    public GDUserTool(String userId, String activeShow, String profilePhoto, String firstName, String lastName, String userPrimaryDepartment, String userPrimaryPosition, String userPhoneNumber, String userEmail, String userCountry, String userState, String activeCity) {
        this.userId = userId;
        this.activeShow = activeShow;
        this.profilePhoto = profilePhoto;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPrimaryDepartment = userPrimaryDepartment;
        this.userPrimaryPosition = userPrimaryPosition;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.userCountry = userCountry;
        this.userState = userState;
        this.activeCity = activeCity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActiveShow() {
        return activeShow;
    }

    public void setActiveShow(String activeShow) {
        this.activeShow = activeShow;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserPrimaryDepartment() {
        return userPrimaryDepartment;
    }

    public void setUserPrimaryDepartment(String userPrimaryDepartment) {
        this.userPrimaryDepartment = userPrimaryDepartment;
    }

    public String getUserPrimaryPosition() {
        return userPrimaryPosition;
    }

    public void setUserPrimaryPosition(String userPrimaryPosition) {
        this.userPrimaryPosition = userPrimaryPosition;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getActiveCity() {
        return activeCity;
    }

    public void setActiveCity(String activeCity) {
        this.activeCity = activeCity;
    }
}
