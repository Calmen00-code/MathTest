/***
 * This class is created to fix TransactionTooLargeException when passing byte[][] image
 * from one activity to another by store image retrieve online into DB temporary
 */
package com.calmen.mathtest.models;

import android.content.Context;

import com.calmen.mathtest.database.DBModel;

import java.util.ArrayList;

public class OnlinePicture {
    DBModel dbModel;

    public OnlinePicture() {
        dbModel = new DBModel();
    }

    public void addOnlinePicture(byte[] image) {
        dbModel.addOnlineImage(image);
    }

    public ArrayList<byte[]> getOnlineImages() {
        return dbModel.getAllOnlineImages();
    }
}
