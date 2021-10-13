package com.calmen.mathtest.view_list.grid_view_image;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.GridView;

import com.calmen.mathtest.R;

import java.io.Serializable;

public class GridViewImage extends AppCompatActivity implements Serializable {
    GridView gridView;
    String[] imagesURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_image);

        gridView = findViewById(R.id.gridViewImage);
<<<<<<< HEAD
        // images = (byte[][]) getIntent().getSerializableExtra("images");
        imagesURL = getIntent().getStringArrayExtra("imagesURL");
        Context loadImageContext = (Context) getIntent().getSerializableExtra("loadImageContext");

        if (imagesURL == null) {
            System.out.println("imagesURL is null in GridView");
=======
        images = (byte[][]) getIntent().getSerializableExtra("images");

        if (images == null) {
            System.out.println("Images is null in GridView");
>>>>>>> parent of 9655b48... FIX: OverloadException
        } else {
            GridImageAdapter adapter = new GridImageAdapter(imagesURL, this, loadImageContext);
            gridView.setAdapter(adapter);
        }
    }
}