package com.calmen.mathtest.edit_list.edit_photos.browse_online;

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

public class BrowsePictureOnlineForEdit extends AppCompatActivity {
    Button searchBtn;
    TextView searchImageInput;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_picture_online_for_edit);

        searchBtn = findViewById(R.id.searchImageBtnForEdit);
        searchImageInput = findViewById(R.id.searchImageTxtForEdit);
        progressBar = findViewById(R.id.progressBarImageForEdit);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchImageInput.getText().toString().equals("")) {
                    Toast.makeText(BrowsePictureOnlineForEdit.this, "Search Image is empty!",
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