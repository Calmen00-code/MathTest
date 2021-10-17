package com.calmen.mathtest.edit_list;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;

public class EditNameAttribute extends AppCompatActivity {
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";

    Button confirmEditName;
    EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name_attribute);

        inputName = findViewById(R.id.editNameInputTxt);
        confirmEditName = findViewById(R.id.confirmEditName);
        String mode = getIntent().getStringExtra("MODE");
        Student currentStudent = (Student) getIntent().getSerializableExtra("CurrentStudent");

        confirmEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mode.equals(FIRST_NAME)) {
                    String firstname = inputName.getText().toString();
                    if (firstname.equals("")) {
                        Toast.makeText(EditNameAttribute.this, "Name is empty!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Student newStudent = new Student(
                                firstname, currentStudent.getLastname(), currentStudent.getId(), "", 0, "", "",
                                currentStudent.getImage(), currentStudent.getEmailList(),
                                currentStudent.getPhoneNumberList()
                        );
                        StudentList studentList = new StudentList();
                        studentList.updateStudentName(view.getContext(), newStudent);
                    }
                } else {
                    String lastname = inputName.getText().toString();
                    if (inputName.getText().toString().equals("")) {
                        Toast.makeText(EditNameAttribute.this, "Name is empty!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Student newStudent = new Student(
                                currentStudent.getFirstname(), lastname, currentStudent.getId(), "", 0, "", "",
                                currentStudent.getImage(), currentStudent.getEmailList(),
                                currentStudent.getPhoneNumberList()
                        );
                        StudentList studentList = new StudentList();
                        studentList.updateStudentName(view.getContext(), newStudent);
                    }
                }
                ((Activity) view.getContext()).finish();
            }
        });
    }
}