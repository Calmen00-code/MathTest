package com.calmen.mathtest.edit_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.calmen.mathtest.R;
import com.calmen.mathtest.edit_list.recycler_edit_student.StudentEditListRecyclerAdapter;
import com.calmen.mathtest.edit_list.recycler_edit_student_phoneno.PhoneNumberRecyclerAdapter;
import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.PhoneNumberList;
import com.calmen.mathtest.models.Student;

import java.util.ArrayList;

public class EditPhoneNo extends AppCompatActivity {

    ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
    Student currentStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone_no);

        currentStudent = (Student) getIntent().getSerializableExtra("CurrentStudent");

        loadNumbers();
        RecyclerView rv = findViewById(R.id.listEditPhoneNumberRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        PhoneNumberRecyclerAdapter listRecyclerAdapter =
                new PhoneNumberRecyclerAdapter(phoneNumbers, this);
        rv.setAdapter(listRecyclerAdapter);
    }

    public void loadNumbers() {
        PhoneNumberList phoneNumberList = new PhoneNumberList();
        phoneNumbers = phoneNumberList.getPhoneNumbersByID(currentStudent.getId(), this);
    }
}