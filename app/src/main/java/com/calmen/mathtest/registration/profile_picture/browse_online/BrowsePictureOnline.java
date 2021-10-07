package com.calmen.mathtest.registration.profile_picture.browse_online;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.mathtest.R;

public class BrowsePictureOnline extends AppCompatActivity {

    Button searchBtn;
    TextView searchImageInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_picture_online);

        searchBtn = findViewById(R.id.searchImageBtn);
        searchImageInput = findViewById(R.id.searchImageTxt);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchImageInput.getText().toString().equals("")) {
                    Toast.makeText(BrowsePictureOnline.this, "Search Image is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
    }
}