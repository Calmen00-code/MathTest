package com.calmen.mathtest.test_history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;
import com.calmen.mathtest.test_history.recycler_test_history.RecyclerTestAdapter;
import com.calmen.mathtest.view_list.recycler_view_student.StudentListRecyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestHistory extends AppCompatActivity {
    Button lowToHigh, highToLow, sendRecord;
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_history);

        lowToHigh = findViewById(R.id.sortLowToHigh);
        highToLow = findViewById(R.id.sortHighToLow);
        sendRecord = findViewById(R.id.sendRecord);


        StudentList studentList = new StudentList();
        students = studentList.getStudents(this);

        lowToHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /***
                 * @Sort
                 * algorithm taken from StackOverflow
                 * https://stackoverflow.com/questions/18895915/how-to-sort-an-array-of-objects-in-java
                 */
                /*
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        String score1 = "";
                        score1 += s1.getScore();
                        String score2 = "";
                        score2 += s2.getScore();
                        return score1.compareTo(score2);
                    }
                }); */

                for (Student displayStudent: students) {
                    System.out.println("The score: " + displayStudent.getScore());
                }

                Intent intent = new Intent(view.getContext(), ViewTestHistory.class);
                // FIXME: when this was comment out, the activity can be passed
                // intent.putExtra("Students", students);
                System.out.println("STARTED ACTIVITY BEFORE");
                view.getContext().startActivity(intent);
                System.out.println("STARTED ACTIVITY AFTER");
                //((Activity) view.getContext()).finish();
            }
        });

        highToLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}