/***
 * @phoneNumbers stores the numbers of all the students in the DB
 * @id from each PhoneNo class will identify which number the student belongs to
 *
 * @aggregate: PhoneNumber
 * Communicate with DB whenever the app want to retrieve PhoneNumber(s) of a single student from DB
 */

package com.calmen.mathtest.models;

import android.content.Context;

import com.calmen.mathtest.database.DBModel;

import java.util.ArrayList;

public class PhoneNumberList {
    private DBModel dbModel;
    private ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();

    public PhoneNumberList() { }

    public void load (Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
        phoneNumbers = dbModel.getAllPhoneNumbers();
    }

    public void addPhoneNo(PhoneNumber number) {
        phoneNumbers.add(number);
    }

    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return this.phoneNumbers;
    }
}
