package com.calmen.mathtest.test_history.send_email.recycler_view_email;

import static com.calmen.mathtest.test_history.send_email.SendEmail.EMAIL_TITLE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Email;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.test_history.recycler_test_history.RecyclerTestViewHolder;

import java.util.ArrayList;

public class RecyclerEmailAdapter extends RecyclerView.Adapter<RecyclerEmailViewHolder> {
    ArrayList<Email> emails;
    ArrayList<Student> students;
    Context context;

    public RecyclerEmailAdapter(ArrayList<Email> inEmails, ArrayList<Student> inStudents, Context inContext) {
        this.emails = inEmails;
        this.students = inStudents;
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
                String [] mailto = {email.getEmail()};
                String subject = EMAIL_TITLE;
                String mailbody = getResult();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, mailto);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, mailbody);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    public String getResult() {
        String result = "";

        for (Student student: students) {
            result += student.getFirstname() + " " + student.getLastname() + ": " + student.getScore();
            result += "\n";
        }
        return result;
    }
}
