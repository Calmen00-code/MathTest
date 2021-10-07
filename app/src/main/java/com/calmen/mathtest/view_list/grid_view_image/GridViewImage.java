package com.calmen.mathtest.view_list.grid_view_image;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.GridView;

import com.calmen.mathtest.R;

import java.io.Serializable;

public class GridViewImage extends AppCompatActivity implements Serializable {
    GridView gridView;
    byte[][] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_image);

        gridView = findViewById(R.id.gridViewImage);
        images = (byte[][]) getIntent().getSerializableExtra("images");

        if (images == null) {
            System.out.println("Images is null in GridView");
        } else {
            GridImageAdapter adapter = new GridImageAdapter(images, this);
            gridView.setAdapter(adapter);
        }
    }
}