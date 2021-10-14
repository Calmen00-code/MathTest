/***
 * This class is created to fix TransactionTooLargeException when passing byte[][] image
 * from one activity to another by store image retrieve online into DB temporary
 */
package com.calmen.mathtest.models;

import android.content.Context;
import android.graphics.Bitmap;

import com.calmen.mathtest.database.DBModel;

import java.util.ArrayList;

public class OnlinePicture {
    DBModel dbModel;

    public OnlinePicture(Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
    }

    public void addOnlinePicture(byte[] image) {
        dbModel.addOnlineImage(image);
    }

    public Bitmap[] getOnlineImages() {
        return dbModel.getAllOnlineImages();
    }
}
