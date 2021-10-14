package com.calmen.mathtest.edit_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.mathtest.R;

public class EditNumber extends AppCompatActivity {

    Button confirmEditPhoneNo;
    EditText phoneNoInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_number);

        phoneNoInput = findViewById(R.id.phoneNoInput);
        confirmEditPhoneNo = findViewById(R.id.confirmEditPhoneNo);

        confirmEditPhoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNoInput.getText().toString().equals("")) {
                    Toast.makeText(EditNumber.this, "Phone Number is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    
                }
            }
        });
    }
}