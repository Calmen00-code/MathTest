package com.calmen.mathtest.edit_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.mathtest.R;
import com.calmen.mathtest.edit_list.recycler_edit_email.EmailRecyclerAdapter;
import com.calmen.mathtest.edit_list.recycler_edit_student_phoneno.PhoneNumberRecyclerAdapter;
import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.models.EmailList;
import com.calmen.mathtest.models.PhoneNumberList;
import com.calmen.mathtest.models.Student;

import java.util.ArrayList;

public class EditEmail extends AppCompatActivity {

    ArrayList<Email> emails = new ArrayList<>();
    Student currentStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_email);

        currentStudent = (Student) getIntent().getSerializableExtra("CurrentStudent");

        loadEmails();
        RecyclerView rv = findViewById(R.id.listEditEmailRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        EmailRecyclerAdapter listRecyclerAdapter =
                new EmailRecyclerAdapter(emails, this);
        rv.setAdapter(listRecyclerAdapter);
    }

    public void loadEmails() {
        EmailList emailList = new EmailList();
        emails = emailList.getEmailsByID(currentStudent.getId(), this);
    }
}