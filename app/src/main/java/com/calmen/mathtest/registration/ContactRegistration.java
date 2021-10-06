package com.calmen.mathtest.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
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
import com.calmen.mathtest.shared.Conversion;
import com.calmen.mathtest.shared.Validation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ContactRegistration extends AppCompatActivity {
    public static final int REQUEST_CONTACT = 4;
    public static final int REQUEST_READ_CONTACT_PERMISSION = 5;

    Button pickContactBtn;
    EditText studentIDInput;
    private int contactId;
    private int studentID;
    private ArrayList<PhoneNumber> phoneNumbers;
    private ArrayList<Email> emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_registration);

        studentIDInput = findViewById(R.id.studentIDContactReg);
        pickContactBtn = findViewById(R.id.pickContactBtn);

        pickContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (studentIDInput.getText().toString().equals("")) {
                    Toast.makeText(ContactRegistration.this, "Student ID is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    studentID = Integer.parseInt(studentIDInput.getText().toString());
                    if (Validation.isStudentIDExist(studentID, view.getContext())) {
                        Toast.makeText(ContactRegistration.this, "Student ID has been taken!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        pickContactButtonPressed();
                    }
                }
            }
        });
    }

    public void pickContactButtonPressed() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CONTACT);
    }

    /***
     * Retrieve all the numbers from selected contact
     * @return phoneNumberList back to caller using Intent
     */
    public void selectContact() {
        Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Uri emailUri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;

        String[] queryNames = new String[] {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
        };

        String[] queryNumbers = new String[] {
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        String[] queryEmails = new String[] {
                ContactsContract.CommonDataKinds.Email.ADDRESS
        };

        String[] queryProfilePic = new String[] {
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI
        };

        // Define the search conditions
        String whereClauseFields = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?";

        String[] whereValuesFields = new String[] {
                String.valueOf(this.contactId)
        };

        Cursor cursorName = getContentResolver().query(
                contactUri, queryNames, whereClauseFields, whereValuesFields, null);

        Cursor cursorNumber = getContentResolver().query(
                contactUri, queryNumbers, whereClauseFields, whereValuesFields, null);

        Cursor cursorEmail = getContentResolver().query(
                emailUri, queryEmails, whereClauseFields, whereValuesFields, null);

        Cursor cursorProfilePic = getContentResolver().query(
                contactUri, queryProfilePic, whereClauseFields, whereValuesFields, null);

        try {
            String firstname, lastname, phoneNo, email;
            PhoneNumberList phoneNumberList = new PhoneNumberList();
            EmailList emailList = new EmailList();

            // Traverse name
            // --------------------------
            cursorName.moveToFirst();
            do {
                String name = cursorName.getString(0);
                String[] nameSplit = name.split(" ");
                firstname = nameSplit[0];
                lastname = nameSplit[1];
            } while (cursorName.moveToNext());

            // Traverse number
            // --------------------------
            cursorNumber.moveToFirst();
            phoneNumbers = new ArrayList<>();
            do {
                phoneNo = cursorNumber.getString(0);
                phoneNumberList.addPhoneNo(new PhoneNumber(phoneNo, studentID), this);
            } while (cursorNumber.moveToNext());

            // Traverse email
            // --------------------------
            cursorEmail.moveToFirst();
            emails = new ArrayList<>();
            do {
                email = cursorEmail.getString(0);
                emailList.addEmail(new Email(email, studentID), this);
            } while (cursorEmail.moveToNext());

            // Traverse profile pic
            // --------------------------
            cursorProfilePic.moveToFirst();
            String picURI = cursorProfilePic.getString(0);
            System.out.println("picURI: " + picURI);

            StudentList studentList = new StudentList();
            studentList.addStudent(new Student(firstname, lastname, studentID, picURI,
                    emailList, phoneNumberList), this);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cursorName.close();
            cursorNumber.close();
            cursorEmail.close();
            cursorProfilePic.close();
            finish();
        }
    }

    /***
     * This function acts as an initialization that initialize the contact ID which represents
     * the user in the contact.
     *
     * It will also grant permission to access to phone contact for first
     * time load.
     *
     * @phoneNumberList will be returned back to the caller using Intent
     *                        which will be used to identify if the user cancel registration so that
     *                        phoneNumbers will only be added into DB when user select Confirm.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CONTACT && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            // Query the special ID of the contact created by the system
            String[] queryFields = new String[]{
                    ContactsContract.Contacts._ID
            };
            Cursor cursor = getContentResolver().query(
                    contactUri, queryFields, null, null, null);
            try {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    this.contactId = cursor.getInt(0); // Contact ID in the first entry

                    // Request permission then read the contact
                    if (ContextCompat.checkSelfPermission(ContactRegistration.this,
                            Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ContactRegistration.this,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                REQUEST_READ_CONTACT_PERMISSION);
                    } else {
                        selectContact();
                    }
                }
            } finally {
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_CONTACT_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ContactRegistration.this,
                        "Contact Reading Permission Granted", Toast.LENGTH_SHORT).show();
                selectContact();
            }
        }
    }
}