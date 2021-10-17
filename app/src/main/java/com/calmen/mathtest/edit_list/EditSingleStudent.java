package com.calmen.mathtest.edit_list;

import static com.calmen.mathtest.registration.Registration.REQUEST_REGISTRATION_EMAIL;
import static com.calmen.mathtest.registration.Registration.REQUEST_REGISTRATION_PHONE;
import static com.calmen.mathtest.registration.Registration.REQUEST_REGISTRATION_PICTURE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;

import java.io.IOException;
import java.util.ArrayList;

public class EditSingleStudent extends AppCompatActivity {

    Button firstname, lastname, phoneno, email, photo;
    public static final int REQUEST_EDIT_PHONE = 1;
    Student currentStudent;
    byte[] image = null; // use when option for browse photo online or take photo selected
    String imageURI;     // use when option for browse photo selected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_student);

        firstname = findViewById(R.id.firstnameEditBtn);
        lastname = findViewById(R.id.lastnameEditBtn);
        phoneno = findViewById(R.id.phoneNoEditBtn);
        email = findViewById(R.id.emailEditBtn);
        photo = findViewById(R.id.photoEditBtn);
        currentStudent = (Student) getIntent().getSerializableExtra("CurrentStudent");

        firstname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditSingleStudent.this, EditNameAttribute.class);
                intent.putExtra("MODE", EditNameAttribute.FIRST_NAME);
                intent.putExtra("CurrentStudent", currentStudent);
                view.getContext().startActivity(intent);
                ((Activity) view.getContext()).finish();
            }
        });

        lastname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditSingleStudent.this, EditNameAttribute.class);
                intent.putExtra("MODE", EditNameAttribute.LAST_NAME);
                intent.putExtra("CurrentStudent", currentStudent);
                view.getContext().startActivity(intent);
                ((Activity) view.getContext()).finish();
            }
        });

        phoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditSingleStudent.this, EditPhoneNo.class);
                intent.putExtra("CurrentStudent", currentStudent);
                view.getContext().startActivity(intent);
                ((Activity) view.getContext()).finish();
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditSingleStudent.this, EditEmail.class);
                intent.putExtra("CurrentStudent", currentStudent);
                view.getContext().startActivity(intent);
                ((Activity) view.getContext()).finish();
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditSingleStudent.this, EditPhoto.class);
                intent.putExtra("CurrentStudent", currentStudent);
                ((Activity) view.getContext()).startActivityForResult(intent, REQUEST_EDIT_PHONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT_PHONE && resultCode == RESULT_OK) {
            image = (byte[]) data.getSerializableExtra("profileImage");
            System.out.println("image (byte[]): " + image);

            imageURI = data.getStringExtra("imageURI");

            StudentList studentList = new StudentList();
            if (image != null) {
                System.out.println("image in edit (byte[]): " + image);
                // take photo OR browse photo online selected
                studentList.updateStudentProfilePictureByByte(this,
                        new Student(currentStudent.getFirstname(),
                        currentStudent.getLastname(), currentStudent.getId(), "", 0, "", "",
                        image, currentStudent.getEmailList(),
                        currentStudent.getPhoneNumberList()));
            } else {
                // browse photo internally selected
                System.out.println("ImageURI: " + imageURI);
                try {
                    studentList.updateStudentProfilePictureByURI(this,
                            new Student(currentStudent.getFirstname(),
                                    currentStudent.getLastname(), currentStudent.getId(), "", 0, "", "",
                                    imageURI, currentStudent.getEmailList(),
                                    currentStudent.getPhoneNumberList()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            finish();
        } else {
            System.out.println("FAILED REGISTRATION");
            if (resultCode == RESULT_OK) {
                System.out.println("RESULT IS OK");
            } else {
                System.out.println("RESULT IS NOT OK");
            }
        }
    }

}