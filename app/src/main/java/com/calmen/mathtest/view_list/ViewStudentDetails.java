package com.calmen.mathtest.view_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.calmen.mathtest.R;

public class ViewStudentDetails extends AppCompatActivity {
    TextView dateView, scoreView, timeView, timeSpentView;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_details);

        dateView = findViewById(R.id.dateView);
        scoreView = findViewById(R.id.scoreView);
        timeView = findViewById(R.id.timeView);
        timeSpentView = findViewById(R.id.timeSpentView);
        done = findViewById(R.id.doneBtn);

        String date = getIntent().getStringExtra("Date");
        int score = getIntent().getIntExtra("Score", 0);
        String time = getIntent().getStringExtra("Time");
        String timeSpent = getIntent().getStringExtra("TimeSpent");

        dateView.setText(date);
        scoreView.setText(score);
        timeView.setText(time);
        timeSpentView.setText(timeSpent);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}