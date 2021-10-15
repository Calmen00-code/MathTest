package com.calmen.mathtest.take_test.recyler_view_student_test;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.shared.Conversion;
import com.calmen.mathtest.view_list.recycler_view_student.StudentListViewHolder;

import java.util.ArrayList;

public class StudentTestRecyclerAdapter extends RecyclerView.Adapter<StudentTestViewHolder> {
    ArrayList<Student> students;
    Context context;

    public StudentTestRecyclerAdapter(ArrayList<Student> inStudents, Context inContext) {
        this.students = inStudents;
        this.context = inContext;
    }

    @NonNull
    @Override
    public StudentTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.student_view_test_entry, parent, false);
        final StudentTestViewHolder studentTestViewHolder = new StudentTestViewHolder(view);
        return studentTestViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentTestViewHolder holder, int position) {
        Student student = students.get(position);

        // Convert to bitmap as the student is storing image as byte[], which is BLOB type in DB
        // Check if the student has a profile picture, if student does not have any, it returns null
        if (student.getImage() != null) {
            Bitmap image = Conversion.convertImageFromByteToBitmap(student.getImage());
            holder.studentImageViewTest.setImageBitmap(image);
        } else {
            holder.studentImageViewTest.setImageURI(Uri.parse(student.getPhotoURI()));
        }

        holder.fullNameViewTest.setText(student.getFirstname() + " " + student.getLastname());
        holder.takeTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: action for viewing student details here
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
