package com.example.hp.eu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.hp.eu.common.Constants.*;

public class MyDbHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "com.example.hp.eu";
    public static int DATABASE_VERSION = 2;


    public static String DATABASE_USER_INFO = "create table " + USER_PROFILE_TABLE + "( " + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_FIRST_NAME + " text, "
            + COLUMN_USER_LAST_NAME + " text, "
            + COLUMN_PHONE_NUMBER + " text, "
            + COLUMN_EMAIL_ID + " text, "
            + COLUMN_PASSWORD + " text, "
            + COLUMN_IS_DELETE + " text, "
            + COLUMN_IS_ACTIVE + " text );";

    public static String DATABASE_USERS = "create table " + USER_TABLE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_ID + " text, "
            + COLUMN_USER_TYPE + " text, "
            + COLUMN_USER_PROFILE_IMAGE + " text, "
            + COLUMN_ADDRESS + " text, "
            + COLUMN_CITY + " text, "
            + COLUMN_INCOME + " text, "
            + COLUMN_IS_DELETE + " text, "
            + COLUMN_IS_ACTIVE + " text );";

    public static String DATABASE_KID = "create table " + KID_TABLE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_ID + " text, "
            + COLUMN_KID_SCHOOL + " text, "
            + COLUMN_KID_BOARD + " text, "
            + COLUMN_KID_STANDARD + " text, "
            + COLUMN_SCHOOL_FEES + " text, "
            + COLUMN_DOCUMENT + " text, "
            + COLUMN_IS_DELETE + " text, "
            + COLUMN_IS_ACTIVE + " text );";

    public static String DATABASE_BOOKS = "create table " + BOOKS_TABLE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_KID_ID + " text, "
            + COLUMN_USER_ID + " text, "
            + COLUMN_DESCRIPTION + " text, "
            + COLUMN_BOARD + " text, "
            + COLUMN_STANDARD + " text, "
            + COLUMN_CITY + " text, "
            + COLUMN_SUBJECT + " text, "
            + COLUMN_BOOK_LIST + " text, "
            + COLUMN_IS_DELETE + " text, "
            + COLUMN_IS_ACTIVE + " text );";

    public static String DATABASE_REQUEST_RESPONSE = "create table " + REQUEST_RESPONSE_TABLE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BOOK_ID + " text, "
            + COLUMN_REQUESTOR_ID + " text, "
            + COLUMN_PROVIDER_ID + " text, "
            + COLUMN_STATUS + " text, "
            + COLUMN_NOTE + " text, "
            + COLUMN_IS_DELETE + " text, "
            + COLUMN_IS_ACTIVE + " text );";

    public static String DATABASE_MASTER_BOARD = "create table " + MASTER_BOARD_TABLE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BOARD + " text, "
            + COLUMN_IS_DELETE + " text, "
            + COLUMN_IS_ACTIVE + " text );";

    public static String DATABASE_MASTER_SCHOOL = "create table " + MASTER_SCHOOL_TABLE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SCHOOL + " text, "
            + COLUMN_IS_DELETE + " text, "
            + COLUMN_IS_ACTIVE + " text );";

    public static String DATABASE_MASTER_STANDARD = "create table " + MASTER_STANDARD_TABLE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STANDARD + " text, "
            + COLUMN_IS_DELETE + " text, "
            + COLUMN_IS_ACTIVE + " text );";

    public static String DATABASE_MASTER_SUBJECT = "create table " + MASTER_SUBJECT_TABLE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SUBJECT + " text, "
            + COLUMN_IS_DELETE + " text, "
            + COLUMN_IS_ACTIVE + " text );";

    public static String DATABASE_LOG = "create table " + LOG_TABLE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BOOK_ID + " text, "
            + COLUMN_REQUEST_SENTBY_ID + " text, "
            + COLUMN_REQUEST_SENTTO_ID + " text, "
            + COLUMN_DETAILS + " text, "
            + COLUMN_IS_DELETE + " text, "
            + COLUMN_IS_ACTIVE + " text );";


    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_USER_INFO);
        db.execSQL(DATABASE_USERS);
        db.execSQL(DATABASE_KID);
        db.execSQL(DATABASE_BOOKS);
        db.execSQL(DATABASE_REQUEST_RESPONSE);
        db.execSQL(DATABASE_MASTER_BOARD);
        db.execSQL(DATABASE_MASTER_SCHOOL);
        db.execSQL(DATABASE_MASTER_STANDARD);
        db.execSQL(DATABASE_MASTER_SUBJECT);
        db.execSQL(DATABASE_LOG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_PROFILE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + KID_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BOOKS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + REQUEST_RESPONSE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MASTER_BOARD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MASTER_SCHOOL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MASTER_STANDARD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MASTER_SUBJECT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LOG_TABLE);
        onCreate(db);
    }
}
