package com.calmen.mathtest.models;

public class PhoneNumber {
    private String phoneNo;
    private int id;

    public PhoneNumber(String inPhoneNo, int inId) {
        this.phoneNo = inPhoneNo;
        this.id = inId;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
