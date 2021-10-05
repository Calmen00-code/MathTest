/***
 * Student models that stores the fields of the student
 */

package com.calmen.mathtest.models;

import android.provider.ContactsContract;

import java.util.ArrayList;

public class Student {
    private String firstname;
    private String lastname;
    private int id;
    private String photoURI;
    private EmailList emailList;
    private PhoneNumberList phoneNumberList;
    private byte[] image; // used only for retrieving the image in DB

    // Call when creating/registering a new student
    public Student(String inFirstname, String inLastname, int inId, String inPhotoURI) {
        this.firstname = inFirstname;
        this.lastname = inLastname;
        this.id = inId;
        this.photoURI = inPhotoURI;
        emailList = new EmailList();
        phoneNumberList = new PhoneNumberList();
    }

    // Call in DBCursor when retrieving the list of student
    // This is not called when the app is creating/registering a new Student
    public Student(String inFirstname, String inLastname, int inId, byte[] inImage,
                   EmailList inEmailList, PhoneNumberList inPhoneNumberList) {
        this.firstname = inFirstname;
        this.lastname = inLastname;
        this.id = inId;
        this.image = inImage;
        this.emailList = inEmailList;
        this.phoneNumberList = inPhoneNumberList;
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

    public String getPhotoURI() {
        return photoURI;
    }

    public byte[] getImage() {
        return image;
    }
}
