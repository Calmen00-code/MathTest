package com.calmen.mathtest.edit_list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.calmen.mathtest.R;
import com.calmen.mathtest.edit_list.edit_photos.browse_online.BrowsePictureOnlineForEdit;
import com.calmen.mathtest.registration.profile_picture.ProfilePictureRegistration;
import com.calmen.mathtest.shared.Conversion;

import java.io.IOException;

public class EditPhoto extends AppCompatActivity {

    public static final int REQUEST_THUMBNAIL = 1;
    public static final int REQUEST_BROWSE_PHOTO = 2;
    public static final int REQUEST_BROWSE_PHOTO_ONLINE = 3;
    Button takePhoto, browsePhoto, browsePhotoOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);

        takePhoto = findViewById(R.id.takeLivePhotoBtnForEdit);
        browsePhoto = findViewById(R.id.browsePhotoBtnForEdit);
        browsePhotoOnline = findViewById(R.id.browsePhotoOnlineBtnForEdit);

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                ((Activity) view.getContext()).startActivityForResult(intent, REQUEST_THUMBNAIL);
            }
        });

        browsePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /***
                 * Below code referred is created by JMRboosties at
                 * https://stackoverflow.com/questions/5309190/android-pick-images-from-gallery
                 */
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                // intent.setType("image/*");
                // intent.setAction(Intent.ACTION_GET_CONTENT);
                // startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_BROWSE_PHOTO);
                ((Activity) view.getContext()).startActivityForResult(intent, REQUEST_BROWSE_PHOTO);
            }
        });

        browsePhotoOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPhoto.this, BrowsePictureOnlineForEdit.class);
                ((Activity) view.getContext()).startActivityForResult(intent, REQUEST_BROWSE_PHOTO_ONLINE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_THUMBNAIL) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            System.out.println("image in EditProfile: " + image);
            if (image != null) {
                Intent intent = new Intent();
                try {
                    intent.putExtra("profileImage", Conversion.getBitmapAsByteArray(image));
                    System.out.println("image in EditProfile (2): " + image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setResult(EditPhoto.RESULT_OK, intent);
            }
            finish();
        } else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_BROWSE_PHOTO) {
            Uri imageURI = data.getData();
            System.out.println(imageURI.toString());
            if (imageURI != null) {
                Intent intent = new Intent();
                intent.putExtra("imageURI", imageURI.toString());
                setResult(EditPhoto.RESULT_OK, intent);
            }
            finish();
        } else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_BROWSE_PHOTO_ONLINE) {
            byte[] image = data.getByteArrayExtra("profileImage");
            if (image != null) {
                Intent intent = new Intent();
                intent.putExtra("profileImage", image);
                setResult(ProfilePictureRegistration.RESULT_OK, intent);
            }
            finish();
        }
    }
}