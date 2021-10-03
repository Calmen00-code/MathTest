package com.calmen.mathtest.models;

import java.util.ArrayList;

public class Student {
    private String firstname;
    private String lastname;
    private int id;
    private String photoURL;
    private EmailList emailList;
    private PhoneNumberList phoneNumberList;

    public Student(String inFirstname, String inLastname, int inId, String inPhotoURL) {
        this.firstname = inFirstname;
        this.lastname = inLastname;
        this.id = inId;
        this.photoURL = inPhotoURL;
        emailList = new EmailList();
        phoneNumberList = new PhoneNumberList();
    }

    public int getId() {
        return id;
    }

    public EmailList getEmailList() {
        return emailList;
    }

    public PhoneNumberList getPhoneNumberList() {
        return phoneNumberList;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhotoURL() {
        return photoURL;
    }
}
