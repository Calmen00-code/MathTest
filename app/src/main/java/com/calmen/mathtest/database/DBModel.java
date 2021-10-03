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
}
