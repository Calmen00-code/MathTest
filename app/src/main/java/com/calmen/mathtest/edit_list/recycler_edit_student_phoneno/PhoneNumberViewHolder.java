package com.calmen.mathtest.edit_list.recycler_edit_student_phoneno;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.mathtest.R;

public class PhoneNumberViewHolder extends RecyclerView.ViewHolder {

    Button editPhoneNo;

    public PhoneNumberViewHolder(@NonNull View itemView) {
        super(itemView);
        editPhoneNo = itemView.findViewById(R.id.editPhoneNoBtn);
    }
}
