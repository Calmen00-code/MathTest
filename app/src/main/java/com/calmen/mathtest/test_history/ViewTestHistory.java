package com.calmen.mathtest.test_history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;
import com.calmen.mathtest.test_history.recycler_test_history.RecyclerTestAdapter;
import com.calmen.mathtest.view_list.recycler_view_student.StudentListRecyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViewTestHistory extends AppCompatActivity {
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_history);

        System.out.println("VIEW TEST HISTORY!");

        StudentList studentList = new StudentList();
        students = studentList.getStudents(this);

        RecyclerView rv = findViewById(R.id.listRecyclerTestView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerTestAdapter recyclerTestAdapter = new RecyclerTestAdapter(students, this);
        rv.setAdapter(recyclerTestAdapter);
    }
}