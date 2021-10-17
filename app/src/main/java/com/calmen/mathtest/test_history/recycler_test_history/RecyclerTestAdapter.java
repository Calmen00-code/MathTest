package com.calmen.mathtest.test_history.recycler_test_history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.view_list.recycler_view_student.StudentListViewHolder;

import java.util.ArrayList;

public class RecyclerTestAdapter extends RecyclerView.Adapter<RecyclerTestViewHolder> {
    ArrayList<Student> students;
    Context context;

    public RecyclerTestAdapter(ArrayList<Student> inStudents, Context inContext) {
        this.students = inStudents;
        this.context = inContext;
    }

    @NonNull
    @Override
    public RecyclerTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_test_entry, parent, false);
        final RecyclerTestViewHolder recyclerTestViewHolder = new RecyclerTestViewHolder(view);
        return recyclerTestViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTestViewHolder holder, int position) {
        Student student = students.get(position);

        holder.nameView.setText(student.getFirstname());
        holder.markView.setText(String.valueOf(student.getScore()));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
