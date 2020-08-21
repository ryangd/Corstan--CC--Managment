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

public class GDShowTool {
    private String productionName;
    private String productionPhoneNumber6;
    private String locationManager;
    private String locationManagerEmail;
    private String assistantLocationManager;
    private String assistantLocationManagerEmail;
    private String assistantLocationManagerPhoneNumber;


//??????????????????????????????????????????????????????????????????????????????????????????????????????
    //Varable edits. DO NOT DELETE
//???????????????????????????????????????????????????????????????????????????????????????????????????????
//    //Production
//    showName;
//    productionCompanyName;
//    productionAddress;
//    productionPhoneNumber;
//    productionEmail;
//    productionFaxNumber;
//    productionHours;
//    //Studio
//    studioName;
//    studioCity;
//    studioPhoneNumber;
//    //HR
//    studioHrContact;
//    studioHrContactPhoneNumber;
//    studioHrContactEmail;
//    //Safety
//    studioSafetyContact;
//    studioSafetyContactPhoneNumber;
//    studioSafetyContactEmail;
    //?????????????????????????????????????????????????????????????????????????????????????????????????????

    //Production
    private String showName;
    private String productionCompanyName;
    private String productionAddress;
    private String productionPhoneNumber;
    private String productionEmail;
    private String productionFaxNumber;
    private String productionHours;
    //Studio
    private String studioName;
    private String studioCity;
    private String studioPhoneNumber;

    //HR
    private String studioHrContact;
    private String studioHrContactPhoneNumber;
    private String studioHrContactEmail;
    //Safety
    private String studioSafetyContact;
    private String studioSafetyContactPhoneNumber;
    private String studioSafetyContactEmail;

    public GDShowTool() {
    }

    public GDShowTool(String productionName, String productionPhoneNumber6, String locationManager, String locationManagerEmail, String assistantLocationManager, String assistantLocationManagerEmail, String assistantLocationManagerPhoneNumber, String showName, String productionCompanyName, String productionAddress, String productionPhoneNumber, String productionEmail, String productionFaxNumber, String productionHours, String studioName, String studioCity, String studioPhoneNumber, String studioHrContact, String studioHrContactPhoneNumber, String studioHrContactEmail, String studioSafetyContact, String studioSafetyContactPhoneNumber, String studioSafetyContactEmail) {
        this.productionName = productionName;
        this.productionPhoneNumber6 = productionPhoneNumber6;
        this.locationManager = locationManager;
        this.locationManagerEmail = locationManagerEmail;
        this.assistantLocationManager = assistantLocationManager;
        this.assistantLocationManagerEmail = assistantLocationManagerEmail;
        this.assistantLocationManagerPhoneNumber = assistantLocationManagerPhoneNumber;
        this.showName = showName;
        this.productionCompanyName = productionCompanyName;
        this.productionAddress = productionAddress;
        this.productionPhoneNumber = productionPhoneNumber;
        this.productionEmail = productionEmail;
        this.productionFaxNumber = productionFaxNumber;
        this.productionHours = productionHours;
        this.studioName = studioName;
        this.studioCity = studioCity;
        this.studioPhoneNumber = studioPhoneNumber;
        this.studioHrContact = studioHrContact;
        this.studioHrContactPhoneNumber = studioHrContactPhoneNumber;
        this.studioHrContactEmail = studioHrContactEmail;
        this.studioSafetyContact = studioSafetyContact;
        this.studioSafetyContactPhoneNumber = studioSafetyContactPhoneNumber;
        this.studioSafetyContactEmail = studioSafetyContactEmail;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getProductionPhoneNumber6() {
        return productionPhoneNumber6;
    }

    public void setProductionPhoneNumber6(String productionPhoneNumber6) {
        this.productionPhoneNumber6 = productionPhoneNumber6;
    }

    public String getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(String locationManager) {
        this.locationManager = locationManager;
    }

    public String getLocationManagerEmail() {
        return locationManagerEmail;
    }

    public void setLocationManagerEmail(String locationManagerEmail) {
        this.locationManagerEmail = locationManagerEmail;
    }

    public String getAssistantLocationManager() {
        return assistantLocationManager;
    }

    public void setAssistantLocationManager(String assistantLocationManager) {
        this.assistantLocationManager = assistantLocationManager;
    }

    public String getAssistantLocationManagerEmail() {
        return assistantLocationManagerEmail;
    }

    public void setAssistantLocationManagerEmail(String assistantLocationManagerEmail) {
        this.assistantLocationManagerEmail = assistantLocationManagerEmail;
    }

    public String getAssistantLocationManagerPhoneNumber() {
        return assistantLocationManagerPhoneNumber;
    }

    public void setAssistantLocationManagerPhoneNumber(String assistantLocationManagerPhoneNumber) {
        this.assistantLocationManagerPhoneNumber = assistantLocationManagerPhoneNumber;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getProductionCompanyName() {
        return productionCompanyName;
    }

    public void setProductionCompanyName(String productionCompanyName) {
        this.productionCompanyName = productionCompanyName;
    }

    public String getProductionAddress() {
        return productionAddress;
    }

    public void setProductionAddress(String productionAddress) {
        this.productionAddress = productionAddress;
    }

    public String getProductionPhoneNumber() {
        return productionPhoneNumber;
    }

    public void setProductionPhoneNumber(String productionPhoneNumber) {
        this.productionPhoneNumber = productionPhoneNumber;
    }

    public String getProductionEmail() {
        return productionEmail;
    }

    public void setProductionEmail(String productionEmail) {
        this.productionEmail = productionEmail;
    }

    public String getProductionFaxNumber() {
        return productionFaxNumber;
    }

    public void setProductionFaxNumber(String productionFaxNumber) {
        this.productionFaxNumber = productionFaxNumber;
    }

    public String getProductionHours() {
        return productionHours;
    }

    public void setProductionHours(String productionHours) {
        this.productionHours = productionHours;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getStudioCity() {
        return studioCity;
    }

    public void setStudioCity(String studioCity) {
        this.studioCity = studioCity;
    }

    public String getStudioPhoneNumber() {
        return studioPhoneNumber;
    }

    public void setStudioPhoneNumber(String studioPhoneNumber) {
        this.studioPhoneNumber = studioPhoneNumber;
    }

    public String getStudioHrContact() {
        return studioHrContact;
    }

    public void setStudioHrContact(String studioHrContact) {
        this.studioHrContact = studioHrContact;
    }

    public String getStudioHrContactPhoneNumber() {
        return studioHrContactPhoneNumber;
    }

    public void setStudioHrContactPhoneNumber(String studioHrContactPhoneNumber) {
        this.studioHrContactPhoneNumber = studioHrContactPhoneNumber;
    }

    public String getStudioHrContactEmail() {
        return studioHrContactEmail;
    }

    public void setStudioHrContactEmail(String studioHrContactEmail) {
        this.studioHrContactEmail = studioHrContactEmail;
    }

    public String getStudioSafetyContact() {
        return studioSafetyContact;
    }

    public void setStudioSafetyContact(String studioSafetyContact) {
        this.studioSafetyContact = studioSafetyContact;
    }

    public String getStudioSafetyContactPhoneNumber() {
        return studioSafetyContactPhoneNumber;
    }

    public void setStudioSafetyContactPhoneNumber(String studioSafetyContactPhoneNumber) {
        this.studioSafetyContactPhoneNumber = studioSafetyContactPhoneNumber;
    }

    public String getStudioSafetyContactEmail() {
        return studioSafetyContactEmail;
    }

    public void setStudioSafetyContactEmail(String studioSafetyContactEmail) {
        this.studioSafetyContactEmail = studioSafetyContactEmail;
    }
}

