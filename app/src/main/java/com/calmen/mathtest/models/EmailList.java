package com.calmen.mathtest.models;

import java.util.ArrayList;

public class EmailList {
    private ArrayList<Email> emails = new ArrayList<>();

    public EmailList() { }

    public void addEmail(Email email) {
        emails.add(email);
    }
}
