/***
 * Student models that stores the fields of the student
 */

package com.calmen.mathtest.models;

import android.provider.ContactsContract;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    private String firstname;
    private String lastname;
    private int id;
    private String photoURI;
    private EmailList emailList;
    private PhoneNumberList phoneNumberList;
    private byte[] image; // used only for retrieving the image in DB
    private int score;
    private String date;
    private String time;
    private String timeSpent;

    // Call when creating/registering a new student in ContactRegistration OR
    // Call when creating/registering a new student in ManualRegistration when Browse Photo selected
    public Student(String inFirstname, String inLastname, int inId, String inPhotoURI,
                   EmailList inEmailList, PhoneNumberList inPhoneNumberList) {
        this.firstname = inFirstname;
        this.lastname = inLastname;
        this.id = inId;
        this.date = "";
        this.score = 0;
        this.time = "";
        this.timeSpent = "";
        this.photoURI = inPhotoURI;
        this.emailList = inEmailList;
        this.phoneNumberList = inPhoneNumberList;
    }

    // Call in DBCursor when retrieving the list of student OR
    // Call in ManualRegistration when Browse photo online / Take live photo as profile picture
    public Student(String inFirstname, String inLastname, int inId, String inDate, int inScore, String inTime,
                   String inTimeSpent, byte[] inImage, EmailList inEmailList,
                   PhoneNumberList inPhoneNumberList) {
        this.firstname = inFirstname;
        this.lastname = inLastname;
        this.id = inId;
        this.image = inImage;
        this.date = inDate;
        this.score = inScore;
        this.time = inTime;
        this.timeSpent = inTimeSpent;
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

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }

    public String getTimeSpent() {
        return timeSpent;
    }
}
