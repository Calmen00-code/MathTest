package com.calmen.mathtest.take_test.recyler_view_student_test;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;

public class StudentTestViewHolder extends RecyclerView.ViewHolder {

    TextView fullNameViewTest;
    Button takeTestBtn;
    ImageView studentImageViewTest;

    public StudentTestViewHolder(@NonNull View itemView) {
        super(itemView);
        fullNameViewTest = itemView.findViewById(R.id.fullnameViewTest);
        takeTestBtn = itemView.findViewById(R.id.takeTestStudentBtn);
        studentImageViewTest = itemView.findViewById(R.id.viewImageStudentTest);
    }
}
