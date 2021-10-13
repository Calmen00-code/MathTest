package com.calmen.mathtest.view_list.grid_view_image;

import androidx.appcompat.app.AppCompatActivity;

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
        // images = (byte[][]) getIntent().getSerializableExtra("images");
        imagesURL = getIntent().getStringArrayExtra("imagesURL");

        if (imagesURL == null) {
            System.out.println("imagesURL is null in GridView");
        } else {
            GridImageAdapter adapter = new GridImageAdapter(imagesURL, this);
            gridView.setAdapter(adapter);
        }
    }
}