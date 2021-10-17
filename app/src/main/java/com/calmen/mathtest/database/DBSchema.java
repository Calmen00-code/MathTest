package com.calmen.mathtest.database;

public class DBSchema {
    public static class StudentTable {
        public static final String NAME = "STUDENTS";
        public static class Cols {
            public static final String FIRST_NAME = "firstname";
            public static final String LAST_NAME = "lastname";
            public static final String ID = "id";
            public static final String PROFILE_PICTURE = "profilepic";
            public static final String MARK = "mark";
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

    /***
     * Temporary store the online retrieve Images
     *
     * Storing in DB as passing the byte[][] images from one activity to another
     * will cause TransactionTooLargeException
     */
    public static class OnlineImageTable {
        public static final String NAME = "ONLINE_IMAGE";
        public static class Cols {
            public static final String PICTURE = "picture";
        }
    }
}
