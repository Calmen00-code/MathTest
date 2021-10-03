package com.calmen.mathtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.calmen.mathtest.registration.Registration;

public class MainActivity extends AppCompatActivity {

    Button registerStudent, takeTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerStudent = findViewById(R.id.studentRegistration);
        takeTest = findViewById(R.id.takeTest);

        registerStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });

        takeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: student taking test
            }
        });
    }
}