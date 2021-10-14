package com.calmen.mathtest.edit_list;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.models.EmailList;
import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.PhoneNumberList;

public class EditEmailAttribute extends AppCompatActivity {
    Button confirmEditEmail;
    EditText emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_email_attribute);

        emailInput = findViewById(R.id.editEmailInput);
        confirmEditEmail = findViewById(R.id.confirmEditEmail);
        Email currEmail = (Email) getIntent().getSerializableExtra("CurrentEmail");

        confirmEditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailInput.getText().toString().equals("")) {
                    Toast.makeText(EditEmailAttribute.this, "Phone Number is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Email email = new Email(emailInput.getText().toString(), currEmail.getId());

                    EmailList emailList = new EmailList();
                    emailList.updateStudentEmail(view.getContext(), currEmail, email);
                    ((Activity) view.getContext()).finish();
                }
            }
        });
    }
}