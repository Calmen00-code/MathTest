/***
 * @phoneNumbers is the returned list from either Register By choosing contact method
 *               or manually input contact method
 */
package com.calmen.mathtest.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.models.PhoneNumber;

import java.util.ArrayList;

public class Registration extends AppCompatActivity {

    public static final int REQUEST_REGISTRATION_PHONE = 1;
    public static final int REQUEST_REGISTRATION_EMAIL = 2;
    public static final int REQUEST_REGISTRATION_PICTURE = 3;

    Button pickContactBtn, manualInputBtn;
    ArrayList<PhoneNumber> phoneNumbers;
    ArrayList<Email> emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        pickContactBtn = findViewById(R.id.pickContactRegBtn);
        manualInputBtn = findViewById(R.id.manualInputBtn);

        pickContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, ContactRegistration.class);
                startActivity(intent);
                finish();
            }
        });

        manualInputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, ManualRegistration.class);
                startActivity(intent);
                finish();
            }
        });
    }
}