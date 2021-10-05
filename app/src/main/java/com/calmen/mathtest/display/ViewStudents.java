package com.calmen.mathtest.display;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;

import java.util.ArrayList;

public class ViewStudents extends AppCompatActivity {

    ArrayList<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        loadStudents();
        System.out.println("VIEWING STUDENT");
        for (Student student: students) {
            System.out.println(student.getFirstname() + " " + student.getLastname());
            ArrayList<PhoneNumber> phoneNumbers = student.getPhoneNumberList()
                    .getPhoneNumbersByID(student.getId(), this);
            for (PhoneNumber phoneNumber : phoneNumbers) {
                System.out.print(phoneNumber.getPhoneNo() + ", ");
            }
            System.out.println();

            ArrayList<Email> emails = student.getEmailList().
                    getEmailsByID(student.getId(), this);
            for (Email email: emails) {
                System.out.print(email.getEmail() + ", ");
            }
            System.out.println();
            System.out.println(student.getImage());
        }
    }

    public void loadStudents() {
        StudentList studentList = new StudentList();
        students = studentList.getStudents(this);
    }
}