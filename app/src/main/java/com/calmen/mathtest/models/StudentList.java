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

    public void updateStudentName(Context context, Student updateStudent) {
        if (dbModel == null)
            load(context);

        for (Student student : students) {
            if (student.getId() == updateStudent.getId()) {
                student = updateStudent;
                dbModel.updateStudentName(student, student.getId());
            }
        }
    }

    public void updateStudentProfilePictureByByte(Context context, Student updateStudent) {
        if (dbModel == null)
            load(context);

        for (Student student : students) {
            if (student.getId() == updateStudent.getId()) {
                student = updateStudent;
                dbModel.updateStudentPictureByByte(student, student.getId());
            }
        }
    }

    public void updateStudentProfilePictureByURI(Context context, Student updateStudent) throws IOException {
        if (dbModel == null)
            load(context);

        for (Student student : students) {
            if (student.getId() == updateStudent.getId()) {
                student = updateStudent;
                dbModel.updateStudentPictureByUri(student, student.getId(), context);
            }
        }
    }

    public void updateStudentTestResult(Context context, Student updateStudent) throws IOException {
        if (dbModel == null)
            load(context);

        for (Student student : students) {
            if (student.getId() == updateStudent.getId()) {
                student = updateStudent;
                dbModel.updateStudentTestResult(student, student.getId(), context);
            }
        }
    }

    public void removeStudent(Student student, Context context) {
        if (dbModel == null)
            load(context);

        dbModel.removeStudent(student);
    }


    /***
     * @param id the reference which determines the number to be retrieved
     * @param context needed for load(context) to communicate with DB
     * @return student allocated to the particular student determine by ID
     */
    public Student getStudentByID(int id, Context context) {
        if (dbModel == null) {
            load(context);
        }

        for (Student student: students) {

            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}