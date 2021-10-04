/***
 * @emails stores the emails of all the students in the DB
 * @id from each Email class will identify which email the student belongs to
 *
 * @aggregate: Email
 * Communicate with DB whenever the app want to retrieve Email(s) of a single student from DB
 */

package com.calmen.mathtest.models;

import android.content.Context;

import com.calmen.mathtest.database.DBModel;

import java.io.Serializable;
import java.util.ArrayList;

public class EmailList implements Serializable {
    private DBModel dbModel;
    private ArrayList<Email> emails = new ArrayList<>();

    public EmailList() { }

    public void load (Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
        emails = dbModel.getAllEmails();
    }

    public ArrayList<Email> getEmails() {
        return this.emails;
    }

    public void addEmail(Email email) {
        emails.add(email);

        if (dbModel == null) {
            throw new NullPointerException("Database does not exist");
        } else {
            dbModel.addEmail(email);
        }
    }
}
