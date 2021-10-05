package com.calmen.mathtest.shared;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Conversion {
    /***
     *  Function below is referred to the code developed by Jram
     *  in https://stackoverflow.com/questions/9357668/how-to-store-image-in-sqlite-database
     */
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        outputStream.close();
        return outputStream.toByteArray();
    }

    public static Bitmap getImageAsBitmap(String pictureURI, Context context) throws IOException {
        Uri uri = Uri.parse(pictureURI);
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        return bitmap;
    }
}
