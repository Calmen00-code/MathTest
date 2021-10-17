package com.calmen.mathtest.database;

import static com.calmen.mathtest.online_service.LoadImagePixabay.NUM_IMAGES;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.calmen.mathtest.database.DBSchema.*;

import com.calmen.mathtest.models.*;
import com.calmen.mathtest.shared.Conversion;

import java.io.IOException;
import java.util.ArrayList;

public class DBModel {
    SQLiteDatabase db;
    Context activityContext;

    public void load(Context context) {
        this.db = new DBHelper(context).getWritableDatabase();
        this.activityContext = context;
    }

    public void addStudent(Student student) throws IOException {
        ContentValues cv = new ContentValues();
        cv.put(StudentTable.Cols.FIRST_NAME, student.getFirstname());
        cv.put(StudentTable.Cols.LAST_NAME, student.getLastname());
        cv.put(StudentTable.Cols.ID, student.getId());

        // student.getPhotoURI is NULL if photo selected is not within internal system
        // could either be: online selected image, OR live taking photo
        if (student.getPhotoURI() == null) {
            cv.put(StudentTable.Cols.PROFILE_PICTURE, student.getImage());
        } else {
            Bitmap bitmap = Conversion.getImageAsBitmap(student.getPhotoURI(), activityContext);
            byte[] image = Conversion.getBitmapAsByteArray(bitmap);
            cv.put(StudentTable.Cols.PROFILE_PICTURE, image);
        }
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

    public void addOnlineImage(byte[] image) {
        ContentValues cv = new ContentValues();
        cv.put(OnlineImageTable.Cols.PICTURE, image);
        db.insert(OnlineImageTable.NAME, null, cv);
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

    /***
     * Get all online images retrieved that was stored in the DB
     */
    public Bitmap[] getAllOnlineImages() {
        Bitmap[] images = new Bitmap[NUM_IMAGES];
        Cursor cursor = db.query(OnlineImageTable.NAME, null, null,
                null, null, null, null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            int i = 0;
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                images[i] = Conversion.convertImageFromByteToBitmap(dbCursor.getOnlinePicture());
                dbCursor.moveToNext();
                ++i;
            }
        } finally {
            dbCursor.close();
        }
        return images;
    }

    public void updateStudentName(Student student, int studentID) {
        String[] whereVal = {String.valueOf(studentID)};
        ContentValues cv = new ContentValues();
        cv.put(StudentTable.Cols.FIRST_NAME, student.getFirstname());
        cv.put(StudentTable.Cols.LAST_NAME, student.getLastname());

        int updated = db.update(StudentTable.NAME, cv, StudentTable.Cols.ID + " =?", whereVal);
        if (updated > 0 ) {
            System.out.println("Student name updated");
        } else {
            System.out.println("Student name NOT UPDATED");
        }
    }

    public void updateStudentPhoneNo(PhoneNumber phoneNumber, PhoneNumber oldPhoneNumber, int studentID) {
        String[] whereVal = {String.valueOf(studentID), String.valueOf(oldPhoneNumber.getPhoneNo())};
        ContentValues cv = new ContentValues();
        cv.put(PhoneNumberTable.Cols.PHONE_NO, phoneNumber.getPhoneNo());

        int updated = db.update(PhoneNumberTable.NAME, cv, PhoneNumberTable.Cols.ID +
                " =? AND " + PhoneNumberTable.Cols.PHONE_NO + " =?" , whereVal);
        if (updated > 0 ) {
            System.out.println("Phone number updated");
        } else {
            System.out.println("Phone number NOT UPDATED");
        }
    }

    public void updateStudentEmail(Email email, Email oldEmail, int studentID) {
        String[] whereVal = {String.valueOf(studentID), String.valueOf(oldEmail.getEmail())};
        ContentValues cv = new ContentValues();
        cv.put(EmailTable.Cols.EMAIL, email.getEmail());

        int updated = db.update(EmailTable.NAME, cv, EmailTable.Cols.ID +
                " =? AND " + EmailTable.Cols.EMAIL + " =?" , whereVal);
        if (updated > 0 ) {
            System.out.println("Email updated");
        } else {
            System.out.println("Email NOT UPDATED");
        }
    }

    public void updateStudentPictureByByte(Student student, int studentID) {
        String[] whereVal = {String.valueOf(studentID)};
        ContentValues cv = new ContentValues();
        cv.put(StudentTable.Cols.PROFILE_PICTURE, student.getImage());

        int updated = db.update(StudentTable.NAME, cv, StudentTable.Cols.ID + " =?" , whereVal);
        if (updated > 0 ) {
            System.out.println("Profile pic updated");
        } else {
            System.out.println("Profile pic NOT UPDATED");
        }
    }

    public void updateStudentPictureByUri(Student student, int studentID, Context context) throws IOException {
        String[] whereVal = {String.valueOf(studentID)};
        ContentValues cv = new ContentValues();
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),
                Uri.parse(student.getPhotoURI()));
        byte[] image = Conversion.getBitmapAsByteArray(bitmap);
        cv.put(StudentTable.Cols.PROFILE_PICTURE, image);

        int updated = db.update(StudentTable.NAME, cv, StudentTable.Cols.ID + " =?" , whereVal);
        if (updated > 0 ) {
            System.out.println("Profile pic updated");
        } else {
            System.out.println("Profile pic NOT UPDATED");
        }
    }

    public void updateStudentTestResult(Student student, int studentID, Context context) throws IOException {
        String[] whereVal = {String.valueOf(studentID)};
        ContentValues cv = new ContentValues();
        cv.put(StudentTable.Cols.DATE, student.getDate());
        cv.put(StudentTable.Cols.MARK, student.getScore());
        cv.put(StudentTable.Cols.TIME, student.getTime());
        cv.put(StudentTable.Cols.TIME_SPENT, student.getTimeSpent());

        int updated = db.update(StudentTable.NAME, cv, StudentTable.Cols.ID + " =?" , whereVal);
        if (updated > 0 ) {
            System.out.println("Student Test Result updated");
        } else {
            System.out.println("Student Test Result NOT UPDATED");
        }
    }

    public void removeStudent(Student student) {
        // ID is unique
        String[] whereVal = {String.valueOf(student.getId())};
        int deleted = db.delete(StudentTable.NAME, StudentTable.Cols.ID + " =?", whereVal);
        if (deleted > 0 ) {
            System.out.println("Student deleted");
        } else {
            System.out.println("Student NOT DELETED");
        }
    }
}
