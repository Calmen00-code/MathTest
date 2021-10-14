package com.calmen.mathtest.edit_list.recycler_edit_student_phoneno;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.edit_list.recycler_edit_student.StudentEditListViewHolder;
import com.calmen.mathtest.models.PhoneNumber;

import java.util.ArrayList;

public class PhoneNumberRecyclerAdapter extends RecyclerView.Adapter<PhoneNumberViewHolder> {
    ArrayList<PhoneNumber> phoneNumbers;
    Context context;

    public PhoneNumberRecyclerAdapter(ArrayList<PhoneNumber> inPhoneNumbers, Context inContext) {
        this.phoneNumbers = inPhoneNumbers;
        this.context = inContext;
    }

    @NonNull
    @Override
    public PhoneNumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.edit_phoneno_entry, parent, false);
        final PhoneNumberViewHolder phoneNumberViewHolder = new PhoneNumberViewHolder(view);
        return phoneNumberViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneNumberViewHolder holder, int position) {
        PhoneNumber phoneNumber = phoneNumbers.get(position);

        holder.phoneNoView.setText(phoneNumber.getPhoneNo());
        holder.editPhoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneNumbers.size();
    }
}
