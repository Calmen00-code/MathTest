package com.calmen.mathtest.registration.phone_number;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.PhoneNumberList;

public class ManualInputPhoneNumber extends AppCompatActivity {

    EditText phoneNoInput;
    Button addPhoneNoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input_phone_number);

        phoneNoInput = findViewById(R.id.phoneNumberInput);
        addPhoneNoBtn = findViewById(R.id.manualAddPhoneNumberBtn);

        int studentId = getIntent().getIntExtra("ID", -1);

        addPhoneNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNoInput.getText().toString().equals("")) {
                    Toast.makeText(ManualInputPhoneNumber.this, "Phone number is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    PhoneNumberList phoneNumberList = new PhoneNumberList();
                    PhoneNumber phoneNumber = new PhoneNumber(
                            phoneNoInput.getText().toString(), studentId);
                    phoneNumberList.addPhoneNo(phoneNumber);
                }
            }
        });

    }
}