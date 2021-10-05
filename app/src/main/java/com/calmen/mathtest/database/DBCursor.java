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
        ArrayList<PhoneNumber> existingPhoneNumbers = existingPhoneNumberList.getPhoneNumbers(context);

        // select only the phone numbers that matches with the id since the existingPhoneNumbers
        // retrieves from DB included all student's phone number but not individual's
        PhoneNumberList phoneNumberList = new PhoneNumberList();
        for (PhoneNumber phoneNumber: existingPhoneNumbers) {
            if (phoneNumber.getId() == id) {
                phoneNumberList.addPhoneNo(phoneNumber);
            }
        }

        // load the existing email list from DB
        EmailList existingEmailList = new EmailList();
        existingEmailList.load(context);
        ArrayList<Email> existingEmails = existingEmailList.getEmails(context);

        // select only the emails that matches with the id since the existingEmails
        // retrieves from DB included all student's emails but not individual's
        EmailList emailList = new EmailList();
        for (Email email: existingEmails) {
            if (email.getId() == id) {
                emailList.addEmail(email);
            }
        }

        return new Student(firstname, lastname, id, photoURL, emailList, phoneNumberList);
    }

    public PhoneNumber getPhoneNumber() {
        String phoneNo = getString(getColumnIndex(PhoneNumberTable.Cols.PHONE_NO));
        int id = getInt(getColumnIndex(PhoneNumberTable.Cols.ID));

        return new PhoneNumber(phoneNo, id);
    }

    public Email getEmail() {
        String email = getString(getColumnIndex(EmailTable.Cols.EMAIL));
        int id = getInt(getColumnIndex(PhoneNumberTable.Cols.ID));

        return new Email(email, id);
    }
}
