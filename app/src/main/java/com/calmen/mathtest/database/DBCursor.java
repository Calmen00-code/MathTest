package com.calmen.mathtest.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.calmen.localdata.DBSchema.DBTable;
import com.calmen.mathtest.models.PhoneNumber;

public class DBCursor extends CursorWrapper {
    public DBCursor (Cursor cursor) { super(cursor); }

    public PhoneNumber getPhoneNumbers() {
        String phoneNo = getString(get)
    }
}
