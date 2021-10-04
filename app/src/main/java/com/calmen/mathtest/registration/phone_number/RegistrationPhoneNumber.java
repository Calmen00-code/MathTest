/***
 * @studentId is used to reference the phone number belongs to which student
 * @contactId is used to reference the contact entry in the contact app of the phone
 * @phoneNumbers is the list which will be returned to caller so that caller can use it to
 *               store the phone number(s) in DB when registration is confirmed
 */
package com.calmen.mathtest.registration.phone_number;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
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

    public static final int REQUEST_CONTACT = 3;
    public static final int REQUEST_READ_CONTACT_PERMISSION = 4;
    public static final int REQUEST_MANUAL_INPUT = 5;
    Button manualPhoneNoReg, contactPhoneNoReg;
    private int studentId;
    private int contactId;
    ArrayList<PhoneNumber> phoneNumbers;

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
                startActivityForResult(intent, REQUEST_MANUAL_INPUT);
                /*
                Intent intentReturn = new Intent();
                intentReturn.putExtra("phoneNumberList", phoneNumbers);
                setResult(RegistrationPhoneNumber.RESULT_OK, intentReturn);
                ((Activity) view.getContext()).finish();
                */
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

        try {
            cursorName.moveToFirst();
            do {
                String name = cursorName.getString(0);
                String[] nameSplit = name.split(" ");
                String firstname = nameSplit[0];
                String lastname = nameSplit[1];
                System.out.print(firstname + "," + lastname );
            } while (cursorName.moveToNext());
            System.out.println();

            cursorNumber.moveToFirst();
            do {
                String phoneNo = cursorNumber.getString(0);
                System.out.print(phoneNo + ", ");
            } while (cursorNumber.moveToNext());
            System.out.println();

            cursorEmail.moveToFirst();
            do {
                String email = cursorEmail.getString(0);
                System.out.print(email + ",");
            } while (cursorEmail.moveToNext());
            System.out.println();

        } finally {
            cursorName.close();
            cursorNumber.close();
            cursorEmail.close();
            Intent intentReturn = new Intent();
            intentReturn.putExtra("phoneNumberList", phoneNumbers);
            setResult(RegistrationPhoneNumber.RESULT_OK, intentReturn);
            finish();
        }
    }

    /***
     * @param requestCode could be REQUEST_CONTACT or REQUEST_MANUAL_INPUT
     *                    REQUEST_CONTACT if the contact is added from contact entry
     *                    REQUEST_MANUAL_INPUT if the contact is added by manually typed
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
                    if (ContextCompat.checkSelfPermission(RegistrationPhoneNumber.this,
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
        } else if (requestCode == REQUEST_MANUAL_INPUT && resultCode == RESULT_OK) {
            boolean isManual = data.getBooleanExtra("isManual", false);

            if (isManual) {
                phoneNumbers = (ArrayList<PhoneNumber>) data.getSerializableExtra("phoneNumberList");

                // return the phone number back to the caller
                Intent intentReturn = new Intent();
                intentReturn.putExtra("phoneNumberList", phoneNumbers);
                setResult(RegistrationPhoneNumber.RESULT_OK, intentReturn);
                finish();

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