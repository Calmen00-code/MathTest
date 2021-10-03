/***
 * @emails stores the emails of all the students in the DB
 * @id from each Email class will identify which email the student belongs to
 *
 * @aggregate: Email
 * Communicate with DB whenever the app want to retrieve Email(s) of a single student from DB
 */

package com.calmen.mathtest.models;

import java.util.ArrayList;

public class EmailList {
    private ArrayList<Email> emails = new ArrayList<>();

    public EmailList() { }

    public void addEmail(Email email) {
        emails.add(email);
    }
}
