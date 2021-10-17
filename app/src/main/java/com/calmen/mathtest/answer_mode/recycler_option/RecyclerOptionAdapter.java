package com.calmen.mathtest.answer_mode.recycler_option;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.online_service.LoadTestQuestion;

import java.util.ArrayList;

public class RecyclerOptionAdapter extends RecyclerView.Adapter<RecyclerOptionViewHolder> {

    ArrayList<String> options;
    Context context;

    public RecyclerOptionAdapter(ArrayList<String> inOptions, Context inContext) {
        this.options = inOptions;
        this.context = inContext;
    }

    @NonNull
    @Override
    public RecyclerOptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.option_entry, parent, false);
        RecyclerOptionViewHolder recyclerOptionViewHolder = new RecyclerOptionViewHolder(view);
        return recyclerOptionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerOptionViewHolder holder, int position) {
        String singleOption = options.get(position);

        holder.answer.setText(singleOption);

        holder.chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoadTestQuestion) context).getAnswer().setText(singleOption);
            }
        });
    }

    @Override
    public int getItemCount() {
        return options.size();
    }
}
