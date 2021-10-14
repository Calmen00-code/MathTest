package com.calmen.mathtest.edit_list.recycler_edit_email;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.edit_list.EditEmailAttribute;
import com.calmen.mathtest.edit_list.EditNumberAttribute;
import com.calmen.mathtest.edit_list.recycler_edit_student_phoneno.PhoneNumberViewHolder;
import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.models.PhoneNumber;

import java.util.ArrayList;

public class EmailRecyclerAdapter extends RecyclerView.Adapter<EmailViewHolder> {
    ArrayList<Email> emails;
    Context context;

    public EmailRecyclerAdapter(ArrayList<Email> inEmails, Context inContext) {
        this.emails = inEmails;
        this.context = inContext;
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.edit_email_entry, parent, false);
        final EmailViewHolder emailViewHolder = new EmailViewHolder(view);
        return emailViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder holder, int position) {
        Email email = emails.get(position);

        holder.editEmailView.setText(email.getEmail());
        holder.editEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditEmailAttribute.class);
                intent.putExtra("CurrentEmail", email);
                view.getContext().startActivity(intent);
                ((Activity) view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }
}
