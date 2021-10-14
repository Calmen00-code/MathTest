package com.calmen.mathtest.view_list.recycler_view_student;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;

public class StudentListViewHolder extends RecyclerView.ViewHolder {
    TextView fullNameView;
    Button viewBtn;
    ImageView studentImageView;

    public StudentListViewHolder(@NonNull View itemView) {
        super(itemView);
        fullNameView = itemView.findViewById(R.id.fullnameView);
        viewBtn = itemView.findViewById(R.id.viewStudentBtn);
        studentImageView = itemView.findViewById(R.id.viewImageStudent);
    }
}
