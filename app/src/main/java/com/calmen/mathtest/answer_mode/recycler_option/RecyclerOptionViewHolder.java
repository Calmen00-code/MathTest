package com.calmen.mathtest.answer_mode.recycler_option;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;


public class RecyclerOptionViewHolder extends RecyclerView.ViewHolder {
    TextView answer;
    Button chooseBtn;

    public RecyclerOptionViewHolder(@NonNull View itemView) {
        super(itemView);

        answer = itemView.findViewById(R.id.answerOptionView);
        chooseBtn = itemView.findViewById(R.id.chooseOptionBtn);
    }
}
