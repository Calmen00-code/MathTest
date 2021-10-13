package com.calmen.mathtest.registration.phone_number;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.PhoneNumberList;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ManualInputPhoneNumber extends AppCompatActivity {

    EditText phoneNoInput;
    Button addPhoneNoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_phone_number);

        phoneNoInput = findViewById(R.id.phoneNumberManual);
        addPhoneNoBtn = findViewById(R.id.confirmPhoneNoBtn);

        int studentId = getIntent().getIntExtra("ID", -1);

        addPhoneNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNoInput.getText().toString().equals("")) {
                    Toast.makeText(ManualInputPhoneNumber.this, "Phone number is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    PhoneNumber phoneNumber = new PhoneNumber(
                            phoneNoInput.getText().toString(), studentId);
                    ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
                    phoneNumbers.add(phoneNumber);

                    Intent intent = new Intent();
                    intent.putExtra("phoneNumberList", phoneNumbers);
                    // intent.putExtra("isManual", true);
                    setResult(ManualInputPhoneNumber.RESULT_OK, intent);

                    ((Activity) view.getContext()).finish();
                }
            }
        });

    }
}