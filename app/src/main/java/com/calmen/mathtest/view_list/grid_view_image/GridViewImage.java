package com.calmen.mathtest.view_list.grid_view_image;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.GridView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.OnlinePicture;

import java.io.Serializable;
import java.util.ArrayList;

public class GridViewImage extends AppCompatActivity implements Serializable {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_image);

        gridView = findViewById(R.id.gridViewImage);

        OnlinePicture onlinePicture = new OnlinePicture(this);
        Bitmap[] images = onlinePicture.getOnlineImages();
        GridImageAdapter adapter = new GridImageAdapter(images, this);
        gridView.setAdapter(adapter);
    }
}