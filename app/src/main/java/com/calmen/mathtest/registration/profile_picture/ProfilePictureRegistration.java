package com.calmen.mathtest.registration.profile_picture;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.calmen.mathtest.R;

public class ProfilePictureRegistration extends AppCompatActivity {

    public static final int REQUEST_THUMBNAIL = 1;
    Button takePhoto, browsePhoto, browsePhotoOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture_registration);

        takePhoto = findViewById(R.id.takeLivePhotoBtn);
        browsePhoto = findViewById(R.id.browsePhotoBtn);
        browsePhotoOnline = findViewById(R.id.browsePhotoOnlineBtn);

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_THUMBNAIL);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_THUMBNAIL) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            if (image != null) {
                Intent intent = new Intent();
                intent.putExtra("profileImage", image);
                setResult(ProfilePictureRegistration.RESULT_OK, intent);
            }
            finish();
        }
    }
}