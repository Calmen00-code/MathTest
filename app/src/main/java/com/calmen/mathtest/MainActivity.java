package com.calmen.mathtest;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.calmen.mathtest.delete_student.DeleteStudent;
import com.calmen.mathtest.edit_list.EditStudents;
import com.calmen.mathtest.take_test.StudentSelection;
import com.calmen.mathtest.test_history.TestHistory;
import com.calmen.mathtest.view_list.ViewStudents;
import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.models.EmailList;
import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.PhoneNumberList;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;
import com.calmen.mathtest.registration.Registration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button registerStudent, takeTest, viewStudent, editStudent, deleteStudent, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerStudent = findViewById(R.id.studentRegistration);
        takeTest = findViewById(R.id.takeTest);
        viewStudent = findViewById(R.id.viewStudentBtn);
        editStudent = findViewById(R.id.editStudentBtn);
        deleteStudent = findViewById(R.id.deleteStudentBtn);
        history = findViewById(R.id.testHistoryBtn);

        registerStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);

                // FIXME: For testing purpose only
                StudentList studentList = new StudentList();
                PhoneNumberList phoneNumberList = new PhoneNumberList();
                EmailList emailList = new EmailList();

                ArrayList<Student> students = studentList.getStudents(view.getContext());
                for (Student student : students) {
                    System.out.println(student.getFirstname() + " " + student.getLastname());

                    ArrayList<PhoneNumber> phoneNumbersDisplay = phoneNumberList.getPhoneNumbersByID(
                            student.getId(), view.getContext());
                    for (PhoneNumber number : phoneNumbersDisplay) {
                        System.out.print(number.getPhoneNo() + ", ");
                    }
                    System.out.println();

                    ArrayList<Email> emailsDisplay = emailList.getEmailsByID(student.getId(), view.getContext());
                    for (Email emailDisplay : emailsDisplay) {
                        System.out.print(emailDisplay.getEmail() + ", ");
                    }
                    System.out.println();

                    System.out.println("image byte: " + student.getImage());
                    System.out.println("---------------------------------------");
                }
            }
        });

        viewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewStudents.class);
                startActivity(intent);
            }
        });

        editStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditStudents.class);
                startActivity(intent);
            }
        });

        deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DeleteStudent.class);
                startActivity(intent);
            }
        });

        takeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentSelection.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestHistory.class);
                startActivity(intent);
            }
        });
    }
}