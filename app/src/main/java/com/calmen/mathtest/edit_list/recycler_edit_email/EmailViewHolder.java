package com.calmen.mathtest.edit_list.recycler_edit_email;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;

public class EmailViewHolder extends RecyclerView.ViewHolder {

    Button editEmailBtn;
    TextView editEmailView;

    public EmailViewHolder(@NonNull View itemView) {
        super(itemView);

        editEmailBtn = itemView.findViewById(R.id.editEmailBtn);
        editEmailView = itemView.findViewById(R.id.editEmailView);
    }
}
