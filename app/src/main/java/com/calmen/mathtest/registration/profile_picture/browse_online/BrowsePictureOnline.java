package com.calmen.mathtest.registration.profile_picture.browse_online;

import static com.calmen.mathtest.registration.profile_picture.ProfilePictureRegistration.REQUEST_BROWSE_PIC_ONLINE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.online_service.LoadImagePixabay;
import com.calmen.mathtest.registration.profile_picture.ProfilePictureRegistration;
import com.calmen.mathtest.shared.Conversion;

import java.io.IOException;
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
                    ((Activity) view.getContext()).startActivityForResult(intent, REQUEST_BROWSE_PIC_ONLINE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_BROWSE_PIC_ONLINE && resultCode == RESULT_OK) {
            // byte[] image = (Bitmap) data.getExtras().get("profileImage");
            byte[] image = data.getByteArrayExtra("profileImage");
            if (image != null) {
                Intent intent = new Intent();
                intent.putExtra("profileImage", image);
                System.out.println("Browse Photo Online: " + image);
                setResult(BrowsePictureOnline.RESULT_OK, intent);
            }
            finish();
        } else {
            System.out.println("FAILED TO LOAD IMAGE FROM Browse Picture Online");
            if (resultCode == RESULT_OK) {
                System.out.println("RESULT IS OK");
            } else {
                System.out.println("RESULT IS NOT OK");
            }
        }
    }
}