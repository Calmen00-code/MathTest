package com.calmen.mathtest.test_history.send_email.recycler_view_email;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.test_history.recycler_test_history.RecyclerTestViewHolder;

import java.util.ArrayList;

public class RecyclerEmailAdapter extends RecyclerView.Adapter<RecyclerEmailViewHolder> {
    ArrayList<Email> emails;
    Context context;

    public RecyclerEmailAdapter(ArrayList<Email> inEmails, Context inContext) {
        this.emails = inEmails;
        this.context = inContext;
    }

    @NonNull
    @Override
    public RecyclerEmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.email_view_entry, parent, false);
        final RecyclerEmailViewHolder recyclerEmailViewHolder = new RecyclerEmailViewHolder(view);
        return recyclerEmailViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerEmailViewHolder holder, int position) {
        Email email = emails.get(position);

        holder.emailView.setText(email.getEmail());
        holder.selectEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }
}
