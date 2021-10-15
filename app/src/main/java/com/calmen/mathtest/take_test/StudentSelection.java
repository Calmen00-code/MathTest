package com.calmen.mathtest.take_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;
import com.calmen.mathtest.take_test.recyler_view_student_test.StudentTestRecyclerAdapter;

import java.util.ArrayList;

public class StudentSelection extends AppCompatActivity {

    ArrayList<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_selection);

        loadStudents();
        RecyclerView rv = findViewById(R.id.listViewRecyclerTest);
        rv.setLayoutManager(new LinearLayoutManager(this));
        StudentTestRecyclerAdapter listRecyclerAdapter = new StudentTestRecyclerAdapter(students, this);
        rv.setAdapter(listRecyclerAdapter);
    }

    public void loadStudents() {
        StudentList studentList = new StudentList();
        students = studentList.getStudents(this);
    }
}