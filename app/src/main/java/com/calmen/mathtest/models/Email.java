package com.calmen.mathtest.models;

public class Email {
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
