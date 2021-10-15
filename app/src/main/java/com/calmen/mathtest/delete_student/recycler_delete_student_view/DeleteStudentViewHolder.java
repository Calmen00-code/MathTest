package com.calmen.mathtest.delete_student.recycler_delete_student_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.view_list.recycler_view_student.StudentListViewHolder;

import java.util.ArrayList;

public class DeleteStudentViewHolder extends RecyclerView.ViewHolder {

    TextView fullNameViewDel;
    Button deleteStudentBtn;
    ImageView studentImageView;

    public DeleteStudentViewHolder(@NonNull View itemView) {
        super(itemView);
        fullNameViewDel = itemView.findViewById(R.id.fullnameDeleteView);
        deleteStudentBtn = itemView.findViewById(R.id.deleteStudentBtn);
        studentImageView = itemView.findViewById(R.id.deleteImageStudent);
    }
}