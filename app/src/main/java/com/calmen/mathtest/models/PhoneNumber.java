/***
 * Stores each student's phone number details
 * @id is the studentID which identify who does the phone number belongs
 */

package com.calmen.mathtest.models;

import java.io.Serializable;

public class PhoneNumber implements Serializable {
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
