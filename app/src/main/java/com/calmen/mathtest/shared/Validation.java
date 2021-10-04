package com.calmen.mathtest.shared;

import android.content.Context;

import com.calmen.mathtest.models.PhoneNumber;
import com.calmen.mathtest.models.PhoneNumberList;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;

import java.util.ArrayList;

public class Validation {
    public static final int MAXIMUM_PHONE_NUMBER = 10;

    /***
     * @param studentID is used to determine the phone number(s) allocated to the student
     * @return true if there are already 10 numbers allocated to the student and false otherwise
     */
    public static boolean maximumPhoneNumberReached(int studentID, Context context) {
        PhoneNumberList phoneNumberList = new PhoneNumberList();
        ArrayList<PhoneNumber> phoneNumbers = phoneNumberList.getPhoneNumbersByID(
                studentID, context);
        System.out.println("size: " + phoneNumbers.size());
        if (phoneNumbers.size() >= MAXIMUM_PHONE_NUMBER)
            return true;
        else
            return false;
    }

    /***
     * @return true if studentID is existed in DB and false otherwise
     */
    public static boolean isStudentIDExist(int studentID, Context context) {
        StudentList studentList = new StudentList();
        ArrayList<Student> students = studentList.getStudents(context);
        for (Student student : students) {
            if (student.getId() == studentID) {
                return true;
            }
        }
        return false;
    }
}
