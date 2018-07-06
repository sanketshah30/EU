package com.example.hp.eu.common;

import android.net.Uri;

public class Constants {
    //USER PROFILE TABLE
    public static final String COLUMN_USER_ID = "COLUMN_USER_ID";
    public static final String COLUMN_USER_FIRST_NAME = "COLUMN_USER_FIRST_NAME";
    public static final String COLUMN_USER_LAST_NAME = "COLUMN_USER_LAST_NAME";
    public static final String COLUMN_PHONE_NUMBER = "COLUMN_PHONE_NUMBER";
    public static final String COLUMN_EMAIL_ID = "COLUMN_EMAIL_ID";
    public static final String COLUMN_PASSWORD = "COLUMN_PASSWORD";


    public static final String COLUMN_IS_DELETE = "COLUMN_IS_DELETE";
    public static final String COLUMN_IS_ACTIVE = "COLUMN_IS_ACTIVE";

    //USER TABLE
    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String COLUMN_USER_TYPE = "COLUMN_USER_TYPE";
    public static final String COLUMN_USER_PROFILE_IMAGE = "COLUMN_USER_PROFILE_IMAGE";
    public static final String COLUMN_ADDRESS = "COLUMN_ADDRESS";
    public static final String COLUMN_CITY = "COLUMN_CITY";
    public static final String COLUMN_INCOME = "COLUMN_INCOME";

    //KID TABLE
    public static final String COLUMN_KID_SCHOOL = "COLUMN_KID_SCHOOL";
    public static final String COLUMN_KID_BOARD = "COLUMN_KID_BOARD";
    public static final String COLUMN_KID_STANDARD = "COLUMN_KID_STANDARD";
    public static final String COLUMN_SCHOOL_FEES = "COLUMN_SCHOOL_FEES";
    public static final String COLUMN_DOCUMENT = "COLUMN_DOCUMENT";

    //BOOK TABLE
    public static final String COLUMN_KID_ID = "COLUMN_KID_ID";
    public static final String COLUMN_DESCRIPTION = "COLUMN_DESCRIPTION";
    public static final String COLUMN_BOARD = "COLUMN_BOARD_NAME";
    public static final String COLUMN_STANDARD = "COLUMN_STANDARD";
    public static final String COLUMN_SUBJECT = "COLUMN_SUBJECT";
    public static final String COLUMN_BOOK_LIST = "COLUMN_BOOK_LST";

    // REQUEST RESPONSE TABLE
    public static final String COLUMN_BOOK_ID = "COLUMN_BOOK_ID";
    public static final String COLUMN_REQUESTOR_ID = "COLUMN_REQUESTOR_ID";
    public static final String COLUMN_PROVIDER_ID = "COLUMN_PROVIDER_ID";
    public static final String COLUMN_STATUS = "COLUMN_STATUS";
    public static final String COLUMN_NOTE = "COLUMN_NOTE";

    //MASTER_SCHOOL
    public static final String COLUMN_SCHOOL = "COLUMN_SCHOOL";

    //LOG TABLE
    public static final String COLUMN_REQUEST_SENTBY_ID = "COLUMN_REQUEST_SENTBY_ID";
    public static final String COLUMN_REQUEST_SENTTO_ID = "COLUMN_REQUEST_SENTTO_ID";
    public static final String COLUMN_DETAILS = "COLUMN_DETAILS";

    //TABLES
    public static final String USER_PROFILE_TABLE = "USER_PROFILE_TABLE";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String KID_TABLE = "KID_TABLE";
    public static final String BOOKS_TABLE = "BOOKS_TABLE";
    public static final String REQUEST_RESPONSE_TABLE = "REQUEST_RESPONSE_TABLE";
    public static final String MASTER_BOARD_TABLE = "MASTER_BOARD_TABLE";
    public static final String MASTER_SCHOOL_TABLE = "MASTER_SCHOOL_TABLE";
    public static final String MASTER_STANDARD_TABLE = "MASTER_STANDARD_TABLE";
    public static final String MASTER_SUBJECT_TABLE = "MASTER_SUBJECT_TABLE";
    public static final String LOG_TABLE = "LOG_TABLE";

    public static final String CONETNT_PROTOCOL = "content://";
    public static final String AUTHORITY = "com.example.hp.eu";

    public static final String PATH_USER_PROFILE = "PATH_USER_PROFILE";
    public static final String PATH_USER = "PATH_USER";
    public static final String PATH_KID = "PATH_KID";
    public static final String PATH_BOOKS = "PATH_BOOKS";
    public static final String PATH_REQUEST_RESPONSE = "PATH_REQUEST_RESPONSE";
    public static final String PATH_MASTER_BOARD = "PATH_MASTER_BOARD";
    public static final String PATH_MASTER_SCHOOL = "PATH_MASTER_SCHOOL";
    public static final String PATH_MASTER_STANDARD = "PATH_MASTER_STANDARD";
    public static final String PATH_MASTER_SUBJECT = "PATH_MASTER_SUBJECT";
    public static final String PATH_LOG = "PATH_LOG";

    public static final Uri CONTENT_USER_PROFILE = Uri.parse(CONETNT_PROTOCOL + AUTHORITY + "/" + PATH_USER_PROFILE);
    public static final Uri CONTENT_USER = Uri.parse(CONETNT_PROTOCOL + AUTHORITY + "/" + PATH_USER);
    public static final Uri CONTENT_KID = Uri.parse(CONETNT_PROTOCOL + AUTHORITY + "/" + PATH_KID);
    public static final Uri CONTENT_BOOKS = Uri.parse(CONETNT_PROTOCOL + AUTHORITY + "/" + PATH_BOOKS);
    public static final Uri CONTENT_REQUEST_RESPONSE = Uri.parse(CONETNT_PROTOCOL + AUTHORITY + "/" + PATH_REQUEST_RESPONSE);
    public static final Uri CONTENT_MASTER_BOARD = Uri.parse(CONETNT_PROTOCOL + AUTHORITY + "/" + PATH_MASTER_BOARD);
    public static final Uri CONTENT_MASTER_SCHOOL = Uri.parse(CONETNT_PROTOCOL + AUTHORITY + "/" + PATH_MASTER_SCHOOL);
    public static final Uri CONTENT_MASTER_STANDARD = Uri.parse(CONETNT_PROTOCOL + AUTHORITY + "/" + PATH_MASTER_STANDARD);
    public static final Uri CONTENT_MASTER_SUBJECT = Uri.parse(CONETNT_PROTOCOL + AUTHORITY + "/" + PATH_MASTER_SUBJECT);
    public static final Uri CONTENT_LOG = Uri.parse(CONETNT_PROTOCOL + AUTHORITY + "/" + PATH_LOG);

}
