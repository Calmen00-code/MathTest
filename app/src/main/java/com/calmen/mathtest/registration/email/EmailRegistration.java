package com.calmen.mathtest.registration.email;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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

import java.util.ArrayList;

public class EmailRegistration extends AppCompatActivity {

    Button addEmailBtn;
    EditText inputEmailTxt;
    private int studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_registration);

        addEmailBtn = findViewById(R.id.addEmailBtn);
        inputEmailTxt = findViewById(R.id.emailTextInput);

        studentID = getIntent().getIntExtra("ID", -1);

        addEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputEmailTxt.getText().toString().equals("")) {
                    Toast.makeText(EmailRegistration.this, "Email is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    EmailList emailList = new EmailList();
                    emailList.addEmail(new Email(inputEmailTxt.getText().toString(),
                            studentID), view.getContext());

                    Intent intent = new Intent();
                    intent.putExtra("Email", emailList);
                    setResult(EmailRegistration.RESULT_OK, intent);
                    ((Activity) view.getContext()).finish();
                }
            }
        });
    }
}