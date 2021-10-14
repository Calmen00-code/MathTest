package com.calmen.mathtest.edit_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.calmen.mathtest.R;

public class EditSingleStudent extends AppCompatActivity {

    Button firstname, lastname, phoneno, email, photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_student);

        firstname = findViewById(R.id.firstnameEditBtn);
        lastname = findViewById(R.id.lastnameEditBtn);
        phoneno = findViewById(R.id.phoneNoEditBtn);
        email = findViewById(R.id.emailEditBtn);
        photo = findViewById(R.id.photoEditBtn);

        firstname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditSingleStudent.this, EditName.class);
                intent.putExtra("MODE", EditName.FIRST_NAME);
            }
        });

        lastname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditSingleStudent.this, EditName.class);
                intent.putExtra("MODE", EditName.LAST_NAME);
            }
        });
    }
}