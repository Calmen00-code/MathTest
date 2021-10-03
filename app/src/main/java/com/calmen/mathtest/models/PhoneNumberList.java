package com.calmen.mathtest.models;

import java.util.ArrayList;

public class PhoneNumberList {
    private ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();

    public PhoneNumberList() { }

    public void addPhoneNo(PhoneNumber number) {
        phoneNumbers.add(number);
    }
}
