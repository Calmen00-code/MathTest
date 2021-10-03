package com.calmen.mathtest.registration.phone_number;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.PhoneNumberList;

import java.util.ArrayList;

public class RegistrationPhoneNumber extends AppCompatActivity {

    public static final int REQUEST_CONTACT = 2;
    public static final int REQUEST_READ_CONTACT_PERMISSION = 3;
    Button manualPhoneNoReg, contactPhoneNoReg;
    private int studentId;
    private int contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_phone_number);

        manualPhoneNoReg = findViewById(R.id.manualInputPhoneBtn);
        contactPhoneNoReg = findViewById(R.id.chooseContactBtn);

        // studentId will be used to create the new PhoneNumber
        // which is PhoneNumber(String phoneNo, int id)
        studentId = getIntent().getIntExtra("ID", -1);

        manualPhoneNoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationPhoneNumber.this, ManualInputPhoneNumber.class);
                intent.putExtra("ID", studentId);
                startActivity(intent);
                finish();
            }
        });

        contactPhoneNoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickContactButtonPressed();
            }
        });
    }

    public void pickContactButtonPressed() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CONTACT);
    }

    public void selectContact() {
        Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] queryNumbers = new String[] {
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        // Define the search conditions
        String whereClauseContactNo = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?";

        String[] whereValuesContactNo = new String[] {
                String.valueOf(this.contactId)
        };

        Cursor cursorNumber = getContentResolver().query(
                contactUri, queryNumbers, whereClauseContactNo, whereValuesContactNo, null);

        try {
            PhoneNumberList phoneNumberList = new PhoneNumberList();
            phoneNumberList.load(this);

            cursorNumber.moveToFirst();
            do {
                String phoneNo = cursorNumber.getString(0);
                PhoneNumber phoneNumber = new PhoneNumber(phoneNo, studentId);
                phoneNumberList.addPhoneNo(phoneNumber);
            } while (cursorNumber.moveToNext());

            // FIXME: For testing ONLY
            phoneNumberList.load(this);
            ArrayList<PhoneNumber> phoneNumbers = phoneNumberList.getPhoneNumbers();
            System.out.println("Phone Numbers DB");
            for (PhoneNumber number : phoneNumbers) {
                System.out.print(number.getPhoneNo() + ", ");
            }
            System.out.println();
            // FIXME: End for testing
        } finally {
            cursorNumber.close();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CONTACT && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
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
                    if(ContextCompat.checkSelfPermission(RegistrationPhoneNumber.this,
                            Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(RegistrationPhoneNumber.this,
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
                Toast.makeText(RegistrationPhoneNumber.this,
                        "Contact Reading Permission Granted", Toast.LENGTH_SHORT).show();
                selectContact();
            }
        }
    }

}