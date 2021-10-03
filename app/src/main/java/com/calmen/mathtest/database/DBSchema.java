package com.calmen.mathtest.database;

public class DBSchema {
    public static class StudentTable {
        public static final String NAME = "STUDENTS";
        public static class Cols {
            public static final String FIRST_NAME = "firstname";
            public static final String LAST_NAME = "lastname";
            public static final String PHOTO_URL = "photourl";
            public static final String ID = "id";
        }
    }

    /***
     * @reference by ID in StudentTable
     * Storing each student's phone number(s)
     */
    public static class PhoneNumberTable {
        public static final String NAME = "PHONE_NUMBER";
        public static class Cols {
            public static final String PHONE_NO = "phoneno";
            public static final String ID = "id";
        }
    }

    /***
     * @reference by ID in StudentTable
     * Storing each student's phone email(s)
     */
    public static class EmailTable {
        public static final String NAME = "EMAIL";
        public static class Cols {
            public static final String EMAIL = "email";
            public static final String ID = "id";
        }
    }
}
