package com.calmen.mathtest.registration;

import static com.calmen.mathtest.registration.Registration.REQUEST_REGISTRATION_EMAIL;
import static com.calmen.mathtest.registration.Registration.REQUEST_REGISTRATION_PHONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.calmen.mathtest.registration.phone_number.PhoneNumberRegistration;
import com.calmen.mathtest.registration.profile_picture.ProfilePictureRegistration;
import com.calmen.mathtest.shared.Validation;

import java.util.ArrayList;

public class ManualRegistration extends AppCompatActivity {

    EditText firstNameEditTxt, lastNameEditTxt, studentIDEditTxt;
    Button phoneNoBtn, emailBtn, profilePicBtn, confirmRegBtn;
    ArrayList<PhoneNumber> phoneNumbers;
    ArrayList<Email> emails;

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
                        startActivityForResult(intent, REQUEST_REGISTRATION_EMAIL);
                    }
                }
            }
        });

        profilePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManualRegistration.this, ProfilePictureRegistration.class);
                startActivity(intent);
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

                        // Store the returned phoneNumberList into DB
                        PhoneNumberList phoneNumberList = new PhoneNumberList();
                        for (PhoneNumber number : phoneNumbers) {
                            phoneNumberList.addPhoneNo(number, view.getContext());
                        }

                        // Store the returned emailList into DB
                        EmailList emailList = new EmailList();
                        for (Email email : emails) {
                            emailList.addEmail(email, view.getContext());
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
            phoneNumbers = (ArrayList<PhoneNumber>) (((PhoneNumberList) data
                    .getSerializableExtra("phoneNumberList")).getPhoneNumbers(this));

            System.out.println("RETURNED PHONE NUMBERS");
            for (PhoneNumber phoneNumber : phoneNumbers) {
                System.out.println(phoneNumber.getPhoneNo() + ", ");
            }
        } else if (requestCode == REQUEST_REGISTRATION_EMAIL && resultCode == RESULT_OK) {
            emails = (ArrayList<Email>) (((EmailList) data
                    .getSerializableExtra("Email")).getEmails(this));
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