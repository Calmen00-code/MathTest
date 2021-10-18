package com.calmen.mathtest.view_list.grid_view_image;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.online_service.LoadImagePixabayCopy;

import java.io.Serializable;

public class GridViewImage extends AppCompatActivity implements Serializable {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image_pixabay);

        gridView = findViewById(R.id.gridViewImage);
        LoadImagePixabayCopy loadImagePixabay = (LoadImagePixabayCopy) getIntent().getSerializableExtra("LoadImageInstance");
        byte[][] images = loadImagePixabay.getImages();

        for (int i = 0; i < images.length; ++i) {
            System.out.println("i: " + images[i]);
        }
    }
}