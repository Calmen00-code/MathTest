package com.calmen.mathtest.delete_student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.mathtest.R;
import com.calmen.mathtest.delete_student.recycler_delete_student_view.DeleteStudentRecyclerAdapter;
import com.calmen.mathtest.edit_list.recycler_edit_student.StudentEditListRecyclerAdapter;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;

import java.util.ArrayList;

public class DeleteStudent extends AppCompatActivity {
    ArrayList<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);

        loadStudents();
        RecyclerView rv = findViewById(R.id.listDeleteRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DeleteStudentRecyclerAdapter listRecyclerAdapter =
                new DeleteStudentRecyclerAdapter(students, this);
        rv.setAdapter(listRecyclerAdapter);
    }

    public void loadStudents() {
        StudentList studentList = new StudentList();
        students = studentList.getStudents(this);
    }
}