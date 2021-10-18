package com.calmen.mathtest.test_history.send_email.recycler_view_email;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;

public class RecyclerEmailViewHolder extends RecyclerView.ViewHolder {
    TextView emailView;
    Button selectEmail;

    public RecyclerEmailViewHolder(@NonNull View itemView) {
        super(itemView);

        emailView = itemView.findViewById(R.id.emailSendView);
        selectEmail = itemView.findViewById(R.id.selectEmailBtn);
    }
}
