package com.calmen.mathtest.registration.phone_number;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.PhoneNumberList;
import com.calmen.mathtest.registration.email.EmailRegistration;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PhoneNumberRegistration extends AppCompatActivity {

    EditText manualInputPhoneNo;
    Button confirmManualPhoneNo;
    ArrayList<PhoneNumber> phoneNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_phone_number);

        manualInputPhoneNo = findViewById(R.id.phoneNumberManual);
        confirmManualPhoneNo = findViewById(R.id.confirmPhoneNoBtn);
        int studentID = getIntent().getIntExtra("ID", -1);
        phoneNumbers = (ArrayList<PhoneNumber>) getIntent().getSerializableExtra("phoneNumbers");

        confirmManualPhoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (manualInputPhoneNo.getText().toString().equals("")) {
                    Toast.makeText(PhoneNumberRegistration.this, "Phone number is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    phoneNumbers.add(new PhoneNumber(manualInputPhoneNo.getText().toString(), studentID));

                    Intent intent = new Intent();
                    intent.putExtra("phoneNumbers", phoneNumbers);
                    setResult(PhoneNumberRegistration.RESULT_OK, intent);
                    ((Activity) view.getContext()).finish();
                }
            }
        });
    }
}