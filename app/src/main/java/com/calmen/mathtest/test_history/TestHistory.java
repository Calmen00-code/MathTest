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
import com.calmen.mathtest.test_history.send_email.SendEmail;
import com.calmen.mathtest.view_list.recycler_view_student.StudentListRecyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestHistory extends AppCompatActivity {
    public static final String LOW_TO_HIGH = "LowToHigh";
    public static final String HIGH_TO_LOW = "HighToLow";

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
                Intent intent = new Intent(view.getContext(), ViewTestHistory.class);
                intent.putExtra("Mode", LOW_TO_HIGH);
                view.getContext().startActivity(intent);
            }
        });

        highToLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ViewTestHistory.class);
                intent.putExtra("Mode", HIGH_TO_LOW);
                view.getContext().startActivity(intent);
            }
        });

        sendRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SendEmail.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}