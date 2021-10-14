package com.calmen.mathtest.edit_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.mathtest.R;
import com.calmen.mathtest.edit_list.recycler_edit_student.StudentEditListRecyclerAdapter;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;
import com.calmen.mathtest.view_list.recycler_view_student.StudentListRecyclerAdapter;

import java.util.ArrayList;

public class EditStudents extends AppCompatActivity {

    ArrayList<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_students);

        loadStudents();
        RecyclerView rv = findViewById(R.id.listEditRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        StudentEditListRecyclerAdapter listRecyclerAdapter =
                new StudentEditListRecyclerAdapter(students, this);
        rv.setAdapter(listRecyclerAdapter);
    }

    public void loadStudents() {
        StudentList studentList = new StudentList();
        students = studentList.getStudents(this);
    }
}