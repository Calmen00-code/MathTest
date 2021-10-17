package com.calmen.mathtest.test_history.recycler_test_history;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;

public class RecyclerTestViewHolder extends RecyclerView.ViewHolder {
    TextView nameView, markView;

    public RecyclerTestViewHolder(@NonNull View itemView) {
        super(itemView);

        nameView = itemView.findViewById(R.id.nameTestView);
        markView = itemView.findViewById(R.id.markTestView);
    }
}
