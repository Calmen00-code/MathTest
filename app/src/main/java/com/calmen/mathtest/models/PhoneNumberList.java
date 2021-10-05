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

import java.io.Serializable;
import java.util.ArrayList;

public class PhoneNumberList implements Serializable {
    private DBModel dbModel;
    private ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();

    public PhoneNumberList() { }

    public void load (Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
        this.phoneNumbers = dbModel.getAllPhoneNumbers();
    }

    /***
     * Method overloading
     * This is used to register a new number to a student
     * @param context is used to load the DB
     */
    public void addPhoneNo(PhoneNumber number, Context context) {
        phoneNumbers.add(number);

        if (dbModel == null) {
            load(context);
        }
        dbModel.addPhoneNumber(number);
    }

    /***
     * Method overloading
     * This is used by the DBCursor to retrieve the phone number(s) list of a student
     */
    public void addPhoneNo(PhoneNumber number) {
        phoneNumbers.add(number);
    }

    public ArrayList<PhoneNumber> getPhoneNumbers(Context context) {
        if (dbModel == null) {
            load(context);
        }
        return dbModel.getAllPhoneNumbers();
    }

    /***
     * @param id the reference which determines the number to be retrieved
     * @param context needed for load(context) to communicate with DB
     * @return all the phone number(s) allocated to the particular student determine by ID
     */
    public ArrayList<PhoneNumber> getPhoneNumbersByID(int id, Context context) {
        ArrayList<PhoneNumber> retPhoneNumbers = new ArrayList<>();
        if (dbModel == null) {
            load(context);
        }

        for (PhoneNumber number: phoneNumbers) {
            if (number.getId() == id) {
                retPhoneNumbers.add(number);
            }
        }
        return retPhoneNumbers;
    }
}
