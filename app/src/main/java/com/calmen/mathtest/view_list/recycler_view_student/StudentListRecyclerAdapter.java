package com.calmen.mathtest.view_list.recycler_view_student;

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

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;

public class StudentListRecyclerAdapter extends RecyclerView.Adapter<StudentListViewHolder> {
    ArrayList<Student> students;

    public StudentListRecyclerAdapter(ArrayList<Student> inStudents) {
        this.students = inStudents;
    }

    @NonNull
    @Override
    public StudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_student_entry, parent, false);
        final StudentListViewHolder studentListViewHolder = new StudentListViewHolder(view);
        return studentListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListViewHolder holder, int position) {
        Student student = students.get(position);

        // Convert to bitmap as the student is storing image as byte[], which is BLOB type in DB
        // Check if the student has a profile picture, if student does not have any, it returns null
        if (student.getImage() != null) {
            Bitmap image = Conversion.convertImageFromByteToBitmap(student.getImage());
            holder.studentImageView.setImageBitmap(image);
        } else {
            holder.studentImageView.setImageURI(Uri.parse(student.getPhotoURI()));
        }

        holder.fullNameView.setText(student.getFirstname() + " " + student.getLastname());
        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
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
