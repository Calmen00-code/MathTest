package com.calmen.mathtest.edit_list.recycler_edit_student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.edit_list.EditSingleStudent;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.shared.Conversion;

import java.util.ArrayList;

public class StudentEditListRecyclerAdapter extends RecyclerView.Adapter<StudentEditListViewHolder> {
    ArrayList<Student> students;
    Context context;

    public StudentEditListRecyclerAdapter(ArrayList<Student> inStudents, Context inContext) {
        this.students = inStudents;
        this.context = inContext;
    }

    @NonNull
    @Override
    public StudentEditListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.edit_student_entry, parent, false);
        final StudentEditListViewHolder studentListViewHolder = new StudentEditListViewHolder(view);
        return studentListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentEditListViewHolder holder, int position) {
        Student student = students.get(position);

        // Convert to bitmap as the student is storing image as byte[], which is BLOB type in DB
        // Check if the student has a profile picture, if student does not have any, it returns null
        if (student.getImage() != null) {
            Bitmap image = Conversion.convertImageFromByteToBitmap(student.getImage());
            holder.studentImageEdit.setImageBitmap(image);
        } else {
            System.out.println("photoURI: " + student.getPhotoURI());
            System.out.println("Name: " + student.getFirstname());
            holder.studentImageEdit.setImageURI(Uri.parse(student.getPhotoURI()));
        }

        holder.fullNameEdit.setText(student.getFirstname() + " " + student.getLastname());
        holder.editStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditSingleStudent.class);
                intent.putExtra("CurrentStudent", student);
                view.getContext().startActivity(intent);
                ((Activity) view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
