/***
 * @emails stores the emails of all the students in the DB
 * @id from each Email class will identify which email the student belongs to
 *
 * @aggregate: Email
 * Communicate with DB whenever the app want to retrieve Email(s) of a single student from DB
 */

package com.calmen.mathtest.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.calmen.mathtest.database.DBModel;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class EmailList implements Serializable {
    transient DBModel dbModel;
    private ArrayList<Email> emails = new ArrayList<>();

    public EmailList() { }

    public void load (Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
        this.emails = dbModel.getAllEmails();
    }

    public ArrayList<Email> getEmails(Context context) {
        if (dbModel == null) {
            load(context);
        }
        return this.emails;
    }

    /***
     * Method overloading
     * This is used to register a new email of a student
     * @param context is used to load the DB
     */
    public void addEmail(Email email, Context context) {
        emails.add(email);

        if (dbModel == null) {
            load(context);
        }
        dbModel.addEmail(email);
    }

    /***
     * Method overloading
     * This is used by the DBCursor to retrieve the email(s) list of a student
     */
    public void addEmail(Email email) {
        emails.add(email);
    }

    /***
     * @param id the reference which determines the number to be retrieved
     * @param context needed for load(context) to communicate with DB
     * @return all the email(s) allocated to the particular student determine by ID
     */
    public ArrayList<Email> getEmailsByID(int id, Context context) {
        ArrayList<Email> retEmails = new ArrayList<>();
        if (dbModel == null) {
            load(context);
        }

        for (Email email : emails) {
            if (email.getId() == id) {
                retEmails.add(email);
            }
        }
        return retEmails;
    }
}
