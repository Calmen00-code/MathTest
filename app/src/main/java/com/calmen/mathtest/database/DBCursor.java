package com.calmen.mathtest.database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.provider.ContactsContract;

import com.calmen.mathtest.database.DBSchema.*;
import com.calmen.mathtest.models.*;

import java.util.ArrayList;

public class DBCursor extends CursorWrapper {
    public DBCursor (Cursor cursor) { super(cursor); }

    public PhoneNumber getPhoneNumber() {
        String phoneNo = getString(getColumnIndex(PhoneNumberTable.Cols.PHONE_NO));
        int id = getInt(getColumnIndex(PhoneNumberTable.Cols.ID));

        return new PhoneNumber(phoneNo, id);
    }

    /***
     * @param context is used to retrieve the PhoneNumberTable and EmailTable from DB
     * @return student object for Student List retrieval
     */
    public Student getStudent(Context context) {
        String firstname = getString(getColumnIndex(StudentTable.Cols.FIRST_NAME));
        String lastname = getString(getColumnIndex(StudentTable.Cols.LAST_NAME));
        int id = getInt(getColumnIndex(StudentTable.Cols.ID));
        String photoURL = getString(getColumnIndex(StudentTable.Cols.PHOTO_URL));

        // load the existing phone number list from DB
        PhoneNumberList existingPhoneNumberList = new PhoneNumberList();
        existingPhoneNumberList.load(context);
        ArrayList<PhoneNumber> existingPhoneNumbers = existingPhoneNumberList.getPhoneNumbers();

        // select only the phone numbers that matches with the id
        // since the existingPhoneNumbers retrieves from DB included all student's phone number
        PhoneNumberList phoneNumberList = new PhoneNumberList();
        for (PhoneNumber phoneNumber: existingPhoneNumbers) {
            if (phoneNumber.getId() == id) {
                phoneNumberList.addPhoneNo(phoneNumber);
            }
        }

        

        return new Student();
    }

}
