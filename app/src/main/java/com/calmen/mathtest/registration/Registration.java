/***
 * @phoneNumbers is the returned list from either Register By choosing contact method
 *               or manually input contact method
 */
package com.calmen.mathtest.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
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
import com.calmen.mathtest.registration.email.EmailRegistration;
import com.calmen.mathtest.registration.phone_number.RegistrationPhoneNumber;
import com.calmen.mathtest.registration.profile_picture.ProfilePictureRegistration;
import com.calmen.mathtest.shared.Validation;

import java.util.ArrayList;

public class Registration extends AppCompatActivity {

    public static final int REQUEST_REGISTRATION_PHONE = 1;
    public static final int REQUEST_REGISTRATION_EMAIL = 2;

    EditText firstNameEditTxt, lastNameEditTxt, studentIDEditTxt;
    Button phoneNoBtn, emailBtn, profilePicBtn, confirmRegBtn;
    ArrayList<PhoneNumber> phoneNumbers;
    ArrayList<Email> emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

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
                    Toast.makeText(Registration.this, "First name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (lastNameEditTxt.getText().toString().equals("")) {
                    Toast.makeText(Registration.this, "Last name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (studentIDEditTxt.getText().toString().equals("")) {
                    Toast.makeText(Registration.this, "Student ID is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    int studentID = Integer.parseInt(studentIDEditTxt.getText().toString());
                    if (Validation.isStudentIDExist(studentID, view.getContext())) {
                        Toast.makeText(Registration.this, "Student ID has been taken!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        if (Validation.maximumPhoneNumberReached(studentID, view.getContext())) {
                            Toast.makeText(Registration.this, "Maximum " +
                                            Validation.MAXIMUM_PHONE_NUMBER + " numbers has been allocated!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(Registration.this,
                                    RegistrationPhoneNumber.class);
                            intent.putExtra("ID", studentID);
                            startActivityForResult(intent, REQUEST_REGISTRATION_PHONE);
                        }
                    }
                }
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstNameEditTxt.getText().toString().equals("")) {
                    Toast.makeText(Registration.this, "First name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (lastNameEditTxt.getText().toString().equals("")) {
                    Toast.makeText(Registration.this, "Last name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (studentIDEditTxt.getText().toString().equals("")) {
                    Toast.makeText(Registration.this, "Student ID is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    int studentID = Integer.parseInt(studentIDEditTxt.getText().toString());
                    if (Validation.isStudentIDExist(studentID, view.getContext())) {
                        Toast.makeText(Registration.this, "Student ID already exist!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(Registration.this, EmailRegistration.class);
                        intent.putExtra("ID", Integer.parseInt(studentIDEditTxt.getText().toString()));
                        startActivityForResult(intent, REQUEST_REGISTRATION_EMAIL);
                    }
                }
            }
        });

        profilePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, ProfilePictureRegistration.class);
                startActivity(intent);
            }
        });

        confirmRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstNameEditTxt.getText().toString().equals("")) {
                    Toast.makeText(Registration.this, "First name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (lastNameEditTxt.getText().toString().equals("")) {
                    Toast.makeText(Registration.this, "Last name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (studentIDEditTxt.getText().toString().equals("")) {
                    Toast.makeText(Registration.this, "Student ID is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: Confirm registration here

                    // Store the returned phoneNumberList into DB
                    PhoneNumberList phoneNumberList = new PhoneNumberList();
                    phoneNumberList.load(view.getContext());
                    for (PhoneNumber number: phoneNumbers) {
                        phoneNumberList.addPhoneNo(number, view.getContext());
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_REGISTRATION_PHONE && resultCode == RESULT_OK) {
            phoneNumbers = (ArrayList<PhoneNumber>) data.getSerializableExtra("phoneNumberList");

            // FIXME: for testing only (REMOVED when implemented Confirm Button)
            // Store the returned phoneNumberList into DB
            PhoneNumberList phoneNumberList = new PhoneNumberList();
            if (phoneNumbers != null) {
                for (PhoneNumber number: phoneNumbers) {
                    phoneNumberList.addPhoneNo(number, this);
                }

                for (PhoneNumber phoneNumber: phoneNumberList.getPhoneNumbers(this)) {
                    System.out.print(phoneNumber.getPhoneNo() + ", ");
                }
                System.out.println();
            }
            // FIXME: for testing only
        } else if (requestCode == REQUEST_REGISTRATION_EMAIL && resultCode == RESULT_OK) {
            emails = (ArrayList<Email>) data.getSerializableExtra("Email");

            // FIXME: for testing only (REMOVED when implemented Confirm Button)
            // Store the returned phoneNumberList into DB
            EmailList emailList = new EmailList();
            if (emails != null) {
                for (Email email: emails) {
                    emailList.addEmail(email, this);
                }

                for (Email email : emailList.getEmails(this)) {
                    System.out.print(email.getEmail() + ", ");
                }
                System.out.println();
            }
            // FIXME: for testing only
        } else {
            System.out.println("FAILED REGISTRATION");
        }
    }

}