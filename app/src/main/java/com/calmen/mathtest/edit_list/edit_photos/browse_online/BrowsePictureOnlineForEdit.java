package com.calmen.mathtest.edit_list.edit_photos.browse_online;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.online_service.LoadImagePixabay;
import com.calmen.mathtest.registration.profile_picture.ProfilePictureRegistration;

public class BrowsePictureOnlineForEdit extends AppCompatActivity {
    public static final int REQUEST_EDIT_IMAGE_ONLINE = 1;

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
                    ((Activity) view.getContext()).startActivityForResult(intent, REQUEST_EDIT_IMAGE_ONLINE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT_IMAGE_ONLINE && resultCode == RESULT_OK) {
            byte[] image = data.getByteArrayExtra("profileImage");
            if (image != null) {
                Intent intent = new Intent();
                intent.putExtra("profileImage", image);
                setResult(BrowsePictureOnlineForEdit.RESULT_OK, intent);
            }
            finish();
        }
    }
}