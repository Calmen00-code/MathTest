package com.calmen.mathtest.delete_student.recycler_delete_student_view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;
import com.calmen.mathtest.shared.Conversion;
import com.calmen.mathtest.view_list.recycler_view_student.StudentListViewHolder;

import java.util.ArrayList;

public class DeleteStudentRecyclerAdapter extends RecyclerView.Adapter<DeleteStudentViewHolder> {
    ArrayList<Student> students;
    Context context;

    public DeleteStudentRecyclerAdapter(ArrayList<Student> inStudents, Context inContext) {
        this.students = inStudents;
        this.context = inContext;
    }

    @NonNull
    @Override
    public DeleteStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.delete_student_entry, parent, false);
        final DeleteStudentViewHolder deleteStudentViewHolder = new DeleteStudentViewHolder(view);
        return deleteStudentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteStudentViewHolder holder, int position) {
        Student student = students.get(position);

        holder.studentImageView.setImageBitmap(Conversion.convertImageFromByteToBitmap(student.getImage()));
        holder.fullNameViewDel.setText(student.getFirstname() + " "  + student.getLastname());
        holder.deleteStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentList studentList = new StudentList();

                studentList.removeStudent(student, view.getContext());
                ((Activity) view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
