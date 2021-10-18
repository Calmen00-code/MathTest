package com.calmen.mathtest.view_list.grid_view_image;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.GridView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.OnlinePicture;
import com.calmen.mathtest.online_service.LoadImagePixabay;

import java.io.Serializable;
import java.util.ArrayList;

public class GridViewImage extends AppCompatActivity implements Serializable {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_image);

        gridView = findViewById(R.id.gridViewImage);
        LoadImagePixabay loadImagePixabay = (LoadImagePixabay) getIntent().getSerializableExtra("LoadImageInstance");
        byte[][] images = loadImagePixabay.getImages();

        for (int i = 0; i < images.length; ++i) {
            System.out.println("i: " + images[i]);
        }

        /*
        OnlinePicture onlinePicture = new OnlinePicture(this);
        Bitmap[] images = onlinePicture.getOnlineImages();
        GridImageAdapter adapter = new GridImageAdapter(images, this);
        gridView.setAdapter(adapter);
         */
    }
}