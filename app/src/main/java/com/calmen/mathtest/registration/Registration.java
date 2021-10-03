package com.calmen.mathtest.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.registration.phone_number.RegistrationPhoneNumber;

public class Registration extends AppCompatActivity {

    EditText firstNameEditTxt, lastNameEditTxt, studentIDEditTxt;
    Button phoneNoBtn, emailBtn, profilePicBtn, confirmRegBtn;

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
                    Intent intent = new Intent(Registration.this, RegistrationPhoneNumber.class);
                    intent.putExtra("ID", Integer.parseInt(studentIDEditTxt.getText().toString()));
                    startActivity(intent);
                }
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Register email here
            }
        });

        profilePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: profile picture selection here
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
                }
            }
        });
    }
}