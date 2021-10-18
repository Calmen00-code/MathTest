package com.calmen.mathtest.test_history;

import static com.calmen.mathtest.test_history.TestHistory.HIGH_TO_LOW;
import static com.calmen.mathtest.test_history.TestHistory.LOW_TO_HIGH;

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

        String mode = getIntent().getStringExtra("Mode");
        loadStudents(mode);

        RecyclerView rv = findViewById(R.id.listRecyclerTestView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerTestAdapter recyclerTestAdapter = new RecyclerTestAdapter(students, this);
        rv.setAdapter(recyclerTestAdapter);
    }

    public void loadStudents(String mode) {
        StudentList studentList = new StudentList();
        students = studentList.getStudents(this);

        if (mode.equals(LOW_TO_HIGH)) {
            /***
             * @Sort
             * algorithm taken from StackOverflow
             * https://stackoverflow.com/questions/18895915/how-to-sort-an-array-of-objects-in-java
             */
             Collections.sort(students, new Comparator<Student>() {
                 @Override
                 public int compare(Student s1, Student s2) {
                     String score1 = "";
                     score1 += s1.getScore();
                     String score2 = "";
                     score2 += s2.getScore();
                     return score1.compareTo(score2);
                 }
             });
        } else if (mode.equals(HIGH_TO_LOW)) {
            /***
             * @Sort
             * algorithm taken from StackOverflow
             * https://stackoverflow.com/questions/18895915/how-to-sort-an-array-of-objects-in-java
             */
            Collections.sort(students, new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    String score1 = "";
                    score1 += s1.getScore();
                    String score2 = "";
                    score2 += s2.getScore();
                    return score2.compareTo(score1);
                }
            });
        }
    }
}