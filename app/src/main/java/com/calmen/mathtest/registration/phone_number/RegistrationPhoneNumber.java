package com.calmen.mathtest.registration.phone_number;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.calmen.mathtest.R;

public class RegistrationPhoneNumber extends AppCompatActivity {

    Button manualPhoneNoReg, contactPhoneNoReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_phone_number);

        manualPhoneNoReg = findViewById(R.id.manualInputPhoneBtn);
        contactPhoneNoReg = findViewById(R.id.chooseContactBtn);

        // studentId will be used to create the new PhoneNumber
        // which is PhoneNumber(String phoneNo, int id)
        int studentId = getIntent().getIntExtra("ID", -1);

        manualPhoneNoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationPhoneNumber.this, ManualInputPhoneNumber.class);
                intent.putExtra("ID", studentId);
                startActivity(intent);
            }
        });
    }
}