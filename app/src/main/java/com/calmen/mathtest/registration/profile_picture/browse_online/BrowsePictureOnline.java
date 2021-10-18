package com.calmen.mathtest.registration.profile_picture.browse_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.online_service.LoadImagePixabay;
import com.calmen.mathtest.online_service.LoadImagePixabayCopy;

import java.io.Serializable;

public class BrowsePictureOnline extends AppCompatActivity implements Serializable {

    Button searchBtn;
    TextView searchImageInput;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_picture_online);

        searchBtn = findViewById(R.id.searchImageBtn);
        searchImageInput = findViewById(R.id.searchImageTxt);
        progressBar = findViewById(R.id.progressBarImage);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchImageInput.getText().toString().equals("")) {
                    Toast.makeText(BrowsePictureOnline.this, "Search Image is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setIndeterminate(true);
                    String searchVal = searchImageInput.getText().toString();

                    Intent intent = new Intent(view.getContext(), LoadImagePixabay.class);
                    intent.putExtra("searchVal", searchVal);
                    view.getContext().startActivity(intent);
                }
            }
        });
    }
}