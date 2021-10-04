/***
 * Stores each student's email details
 * @id is the studentID which identify who does the email belongs
 */

package com.calmen.mathtest.models;

import java.io.Serializable;

public class Email implements Serializable {
    private String email;
    private int id;

    public Email(String inEmail, int inId) {
        this.email = inEmail;
        this.id = inId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
