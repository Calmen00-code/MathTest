package com.calmen.mathtest.edit_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.calmen.mathtest.R;

public class EditName extends AppCompatActivity {
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";

    Button confirmEditName;
    EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        inputName = findViewById(R.id.editNameInputTxt);
        confirmEditName = findViewById(R.id.confirmEditName);
        String mode = getIntent().getStringExtra("MODE");

        if (mode.equals(FIRST_NAME)) {

        } else {

        }

    }
}