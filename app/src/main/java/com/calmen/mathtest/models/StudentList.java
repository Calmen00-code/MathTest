package com.calmen.mathtest.models;

import android.content.Context;

import com.calmen.mathtest.database.DBModel;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class StudentList implements Serializable {
    transient DBModel dbModel;
    private ArrayList<Student> students = new ArrayList<>();

    public StudentList() { }

    public void load (Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
        students = dbModel.getAllStudents(context);
    }

    /***
     * @param context is used in DBCursor getStudent(context)
     *                for retrieval of student list from database
     */
    public ArrayList<Student> getStudents(Context context) {
        if (dbModel == null)
            load(context);
        return dbModel.getAllStudents(context);
    }

    public void addStudent(Student student, Context context) throws IOException {
        students.add(student);

        if (dbModel == null)
            load(context);
        dbModel.addStudent(student);
    }
}