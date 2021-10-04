package com.calmen.mathtest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.calmen.mathtest.database.DBSchema.*;

import com.calmen.mathtest.models.*;
import java.util.ArrayList;

public class DBModel {
    SQLiteDatabase db;
    Context activityContext;

    public void load(Context context) {
        this.db = new DBHelper(context).getWritableDatabase();
        this.activityContext = context;
    }

    public void addStudent(Student student) {
        ContentValues cv = new ContentValues();
        cv.put(StudentTable.Cols.FIRST_NAME, student.getFirstname());
        cv.put(StudentTable.Cols.LAST_NAME, student.getLastname());
        cv.put(StudentTable.Cols.ID, student.getId());
        cv.put(StudentTable.Cols.PHOTO_URL, student.getPhotoURL());
        db.insert(StudentTable.NAME, null, cv);
    }

    public void addPhoneNumber(PhoneNumber phoneNo) {
        ContentValues cv = new ContentValues();
        cv.put(PhoneNumberTable.Cols.PHONE_NO, phoneNo.getPhoneNo());
        cv.put(PhoneNumberTable.Cols.ID, phoneNo.getId());
        db.insert(PhoneNumberTable.NAME, null, cv);
    }

    public void addEmail(Email email) {
        ContentValues cv = new ContentValues();
        cv.put(EmailTable.Cols.EMAIL, email.getEmail());
        cv.put(EmailTable.Cols.ID, email.getId());
        db.insert(EmailTable.NAME, null, cv);
    }

    public ArrayList<PhoneNumber> getAllPhoneNumbers() {
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        Cursor cursor = db.query(PhoneNumberTable.NAME, null, null,
                null, null, null, null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                phoneNumbers.add(dbCursor.getPhoneNumber());
                dbCursor.moveToNext();
            }
        } finally {
            dbCursor.close();
        }
        return phoneNumbers;
    }

    public ArrayList<Email> getAllEmails() {
        ArrayList<Email> emails = new ArrayList<>();
        Cursor cursor = db.query(EmailTable.NAME, null, null,
                null, null, null, null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                emails.add(dbCursor.getEmail());
                dbCursor.moveToNext();
            }
        } finally {
            dbCursor.close();
        }
        return emails;
    }

    /***
     * @param context is used in DBCursor to retrieve student list from DB
     */
    public ArrayList<Student> getAllStudents(Context context) {
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.query(StudentTable.NAME, null, null,
                null, null, null, null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                students.add(dbCursor.getStudent(context));
                dbCursor.moveToNext();
            }
        } finally {
            dbCursor.close();
        }
        return students;
    }
}
