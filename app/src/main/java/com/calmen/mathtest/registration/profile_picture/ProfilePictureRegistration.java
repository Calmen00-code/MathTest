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
import com.calmen.mathtest.shared.Conversion;

import java.io.IOException;

public class ProfilePictureRegistration extends AppCompatActivity {

    public static final int REQUEST_THUMBNAIL = 1;
    public static final int REQUEST_BROWSE_PHOTO = 2;
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

        browsePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent();
                // TODO: implement browse photo internal here
                // startActivityForResult(intent, REQUEST_BROWSE_PHOTO);
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
                try {
                    intent.putExtra("profileImage", Conversion.getBitmapAsByteArray(image));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setResult(ProfilePictureRegistration.RESULT_OK, intent);
            }
            finish();
        } else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_BROWSE_PHOTO) {
            String imageURI = data.getStringExtra("data");
            if (imageURI != null) {
                Intent intent = new Intent();
                intent.putExtra("imageURI", imageURI);
                setResult(ProfilePictureRegistration.RESULT_OK, intent);
            }
            finish();

        }
    }
}