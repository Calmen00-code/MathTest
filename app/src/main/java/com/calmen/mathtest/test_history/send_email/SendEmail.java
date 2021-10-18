package com.calmen.mathtest.test_history.send_email;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.models.EmailList;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;
import com.calmen.mathtest.test_history.recycler_test_history.RecyclerTestAdapter;
import com.calmen.mathtest.test_history.send_email.recycler_view_email.RecyclerEmailAdapter;

import java.util.ArrayList;

public class SendEmail extends AppCompatActivity {
    ArrayList<Email> emails;
    ArrayList<Student> students;
    public static final String EMAIL_TITLE = "Test Result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        loadStudents();
        loadEmails();

        RecyclerView rv = findViewById(R.id.listViewEmailRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerEmailAdapter recyclerEmailAdapter = new RecyclerEmailAdapter(emails, students, this);
        rv.setAdapter(recyclerEmailAdapter);
    }

    public void loadEmails() {
        EmailList emailList = new EmailList();
        emails = emailList.getEmails(this);
    }

    public void loadStudents() {
        StudentList studentList = new StudentList();
        students = studentList.getStudents(this);
    }
}