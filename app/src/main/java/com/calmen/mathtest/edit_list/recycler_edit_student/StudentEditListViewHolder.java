package com.calmen.mathtest.edit_list.recycler_edit_student;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;

public class StudentEditListViewHolder extends RecyclerView.ViewHolder {
    TextView fullNameEdit;
    Button editStudentBtn;
    ImageView studentImageEdit;

    public StudentEditListViewHolder(@NonNull View itemView) {
        super(itemView);
        fullNameEdit = itemView.findViewById(R.id.fullnameEditView);
        editStudentBtn = itemView.findViewById(R.id.editStudentBtn);
        studentImageEdit = itemView.findViewById(R.id.editImageStudent);
    }
}
