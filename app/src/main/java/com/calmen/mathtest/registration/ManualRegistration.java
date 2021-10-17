package com.calmen.mathtest.registration;

import static com.calmen.mathtest.registration.Registration.REQUEST_REGISTRATION_EMAIL;
import static com.calmen.mathtest.registration.Registration.REQUEST_REGISTRATION_PHONE;
import static com.calmen.mathtest.registration.Registration.REQUEST_REGISTRATION_PICTURE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.models.EmailList;
import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.PhoneNumberList;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;
import com.calmen.mathtest.registration.email.EmailRegistration;
import com.calmen.mathtest.registration.phone_number.PhoneNumberRegistration;
import com.calmen.mathtest.registration.profile_picture.ProfilePictureRegistration;
import com.calmen.mathtest.shared.Validation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ManualRegistration extends AppCompatActivity {

    EditText firstNameEditTxt, lastNameEditTxt, studentIDEditTxt;
    Button phoneNoBtn, emailBtn, profilePicBtn, confirmRegBtn;
    ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
    ArrayList<Email> emails = new ArrayList<>();
    byte[] image = null; // use when option for browse photo online or take photo selected
    String imageURI;     // use when option for browse photo selected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_registration);

        firstNameEditTxt = findViewById(R.id.firstNameEditTxt);
        lastNameEditTxt = findViewById(R.id.lastNameEditTxt);
        studentIDEditTxt = findViewById(R.id.studentIDInput);
        phoneNoBtn = findViewById(R.id.addPhoneNo);
        emailBtn = findViewById(R.id.addEmail);
        profilePicBtn = findViewById(R.id.addPhoto);
        confirmRegBtn = findViewById(R.id.confirmReg);

        phoneNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstNameEditTxt.getText().toString().equals("")) {
                    Toast.makeText(ManualRegistration.this, "First name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (lastNameEditTxt.getText().toString().equals("")) {
                    Toast.makeText(ManualRegistration.this, "Last name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (studentIDEditTxt.getText().toString().equals("")) {
                    Toast.makeText(ManualRegistration.this, "Student ID is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    int studentID = Integer.parseInt(studentIDEditTxt.getText().toString());
                    if (Validation.isStudentIDExist(studentID, view.getContext())) {
                        Toast.makeText(ManualRegistration.this, "Student ID has been taken!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        if (Validation.maximumPhoneNumberReached(studentID, view.getContext())) {
                            Toast.makeText(ManualRegistration.this, "Maximum " +
                                            Validation.MAXIMUM_PHONE_NUMBER + " numbers has been allocated!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(ManualRegistration.this,
                                    PhoneNumberRegistration.class);
                            intent.putExtra("ID", studentID);
                            intent.putExtra("phoneNumbers", phoneNumbers);
                            ((Activity) view.getContext()).startActivityForResult(intent, REQUEST_REGISTRATION_PHONE);
                        }
                    }
                }
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstNameEditTxt.getText().toString().equals("")) {
                    Toast.makeText(ManualRegistration.this, "First name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (lastNameEditTxt.getText().toString().equals("")) {
                    Toast.makeText(ManualRegistration.this, "Last name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (studentIDEditTxt.getText().toString().equals("")) {
                    Toast.makeText(ManualRegistration.this, "Student ID is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    int studentID = Integer.parseInt(studentIDEditTxt.getText().toString());
                    if (Validation.isStudentIDExist(studentID, view.getContext())) {
                        Toast.makeText(ManualRegistration.this, "Student ID already exist!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(ManualRegistration.this,
                                EmailRegistration.class);
                        intent.putExtra("ID", Integer.parseInt(studentIDEditTxt.getText().toString()));
                        intent.putExtra("emails", emails);
                        ((Activity) view.getContext()).startActivityForResult(intent, REQUEST_REGISTRATION_EMAIL);
                    }
                }
            }
        });

        profilePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManualRegistration.this, ProfilePictureRegistration.class);
                ((Activity) view.getContext()).startActivityForResult(intent, REQUEST_REGISTRATION_PICTURE);
            }
        });

        confirmRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstNameEditTxt.getText().toString().equals("")) {
                    Toast.makeText(ManualRegistration.this, "First name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (lastNameEditTxt.getText().toString().equals("")) {
                    Toast.makeText(ManualRegistration.this, "Last name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (studentIDEditTxt.getText().toString().equals("")) {
                    Toast.makeText(ManualRegistration.this, "Student ID is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    int studentID = Integer.parseInt(studentIDEditTxt.getText().toString());

                    if (Validation.isStudentIDExist(studentID, view.getContext())) {
                        Toast.makeText(ManualRegistration.this, "Student ID has been taken!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        String firstname = firstNameEditTxt.getText().toString();
                        String lastname = lastNameEditTxt.getText().toString();
                        int id = Integer.parseInt(studentIDEditTxt.getText().toString());

                        // Store the returned phoneNumberList into DB
                        PhoneNumberList phoneNumberList = new PhoneNumberList();
                        for (PhoneNumber number : phoneNumbers) {
                            phoneNumberList.addPhoneNo(number, view.getContext());
                            System.out.println("Number saved in DB: " + number.getPhoneNo());
                        }

                        // Store the returned emailList into DB
                        EmailList emailList = new EmailList();
                        for (Email email : emails) {
                            emailList.addEmail(email, view.getContext());
                            System.out.println("Email saved in DB: " + email.getEmail());
                        }

                        System.out.println("PhoneNo in DB");
                        for (PhoneNumber displayNumber: phoneNumberList.getPhoneNumbers(view.getContext())) {
                            System.out.println(displayNumber.getPhoneNo());
                        }

                        System.out.println("Email in DB");
                        for (Email displayEmail: emailList.getEmails(view.getContext())) {
                            System.out.println(displayEmail.getEmail());
                        }

                        StudentList studentList = new StudentList();
                        try{
                            if (image != null) {
                                System.out.println("image in confirm (byte[]): " + image);
                                // take photo OR browse photo online selected
                                studentList.addStudent(new Student(firstname, lastname, id, "", 0, "", "",
                                        image, emailList, phoneNumberList), view.getContext());
                            } else {
                                // browse photo internally selected
                                System.out.println("ImageURI: " + imageURI);
                                studentList.addStudent(new Student(firstname, lastname, id, "", 0, "", "", imageURI,
                                        emailList, phoneNumberList), view.getContext());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_REGISTRATION_PHONE && resultCode == RESULT_OK) {
            phoneNumbers = (ArrayList<PhoneNumber>) data.getSerializableExtra("phoneNumbers");

            System.out.println("RETURNED PHONE NUMBERS");
            for (PhoneNumber phoneNumber : phoneNumbers) {
                System.out.println(phoneNumber.getPhoneNo() + ", ");
            }
        } else if (requestCode == REQUEST_REGISTRATION_EMAIL && resultCode == RESULT_OK) {
            emails = (ArrayList<Email>) data.getSerializableExtra("Email");

            System.out.println("RETURNED EMAILS");
            for (Email email : emails) {
                System.out.println(email.getEmail() + ", ");
            }
        } else if (requestCode == REQUEST_REGISTRATION_PICTURE && resultCode == RESULT_OK) {
            image = (byte[]) data.getSerializableExtra("profileImage");
            System.out.println("image (byte[]): " + image);
            imageURI = data.getStringExtra("imageURI");
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