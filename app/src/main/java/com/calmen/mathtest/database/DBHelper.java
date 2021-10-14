package com.calmen.mathtest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.calmen.mathtest.database.DBSchema.*;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "mathtest.db";

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + StudentTable.NAME + "(" +
                StudentTable.Cols.FIRST_NAME + " TEXT," +
                StudentTable.Cols.LAST_NAME + " TEXT," +
                StudentTable.Cols.ID + " INTEGER," +
                StudentTable.Cols.PROFILE_PICTURE + " BLOB);");

        db.execSQL("CREATE TABLE " + PhoneNumberTable.NAME + "(" +
                PhoneNumberTable.Cols.PHONE_NO + " TEXT," +
                PhoneNumberTable.Cols.ID + " INTEGER);");

        db.execSQL("CREATE TABLE " + EmailTable.NAME + "(" +
                EmailTable.Cols.EMAIL + " TEXT," +
                EmailTable.Cols.ID + " INTEGER);");

        db.execSQL("CREATE TABLE " + OnlineImageTable.NAME + "(" +
                OnlineImageTable.Cols.PICTURE + " BLOB);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        throw new UnsupportedOperationException("Operation unavailable");
    }
}
