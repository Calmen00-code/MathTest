package com.calmen.mathtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;
import com.calmen.mathtest.registration.Registration;

import java.util.ArrayList;

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

                // FIXME: For testing purpose only
                System.out.println("DEBUG MODE");
                StudentList studentList = new StudentList();
                ArrayList<Student> students = studentList.getStudents(view.getContext());
                for (Student student : students) {
                    System.out.println(student.getFirstname() + " " + student.getLastname());

                    ArrayList<PhoneNumber> phoneNumbers = student.getPhoneNumberList()
                            .getPhoneNumbers(view.getContext());
                    for (PhoneNumber number : phoneNumbers) {
                        System.out.println(number.getPhoneNo() + ", ");
                    }

                    ArrayList<Email> emails = student.getEmailList().getEmails(view.getContext());
                    for (Email email : emails) {
                        System.out.println(email.getEmail() + ", ");
                    }

                    System.out.println(student.getPhotoURL());
                    System.out.println("---------------------------------------");
                }
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