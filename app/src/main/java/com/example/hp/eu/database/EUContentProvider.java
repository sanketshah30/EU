package com.example.hp.eu.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.hp.eu.common.Constants;

import static com.example.hp.eu.common.Constants.PATH_BOOKS;
import static com.example.hp.eu.common.Constants.PATH_KID;
import static com.example.hp.eu.common.Constants.PATH_LOG;
import static com.example.hp.eu.common.Constants.PATH_MASTER_BOARD;
import static com.example.hp.eu.common.Constants.PATH_MASTER_SCHOOL;
import static com.example.hp.eu.common.Constants.PATH_MASTER_STANDARD;
import static com.example.hp.eu.common.Constants.PATH_MASTER_SUBJECT;
import static com.example.hp.eu.common.Constants.PATH_REQUEST_RESPONSE;
import static com.example.hp.eu.common.Constants.PATH_USER;
import static com.example.hp.eu.common.Constants.PATH_USER_PROFILE;


public class EUContentProvider extends ContentProvider {

    private static final int CODE_USER_PROFILE = 1;
    private static final int CODE_USER = 2;
    private static final int CODE_KID = 3;
    private static final int CODE_BOOKS = 4;
    private static final int CODE_REQUEST_RESPONSE = 5;
    private static final int CODE_MASTER_BOARD = 6;
    private static final int CODE_MASTER_SCHOOL = 7;
    private static final int CODE_MASTER_STANDARD = 8;
    private static final int CODE_MASTER_SUBJECT = 9;
    private static final int CODE_LOG = 10;


    private MyDbHelper helper;
    private SQLiteDatabase database;
    private UriMatcher matcher;

    @Override
    public boolean onCreate() {
        helper = new MyDbHelper(getContext());
        database = helper.getWritableDatabase();
        matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(Constants.AUTHORITY, PATH_USER_PROFILE, CODE_USER_PROFILE);
        matcher.addURI(Constants.AUTHORITY, PATH_USER, CODE_USER);
        matcher.addURI(Constants.AUTHORITY, PATH_KID, CODE_KID);
        matcher.addURI(Constants.AUTHORITY, PATH_BOOKS, CODE_BOOKS);
        matcher.addURI(Constants.AUTHORITY, PATH_REQUEST_RESPONSE, CODE_REQUEST_RESPONSE);
        matcher.addURI(Constants.AUTHORITY, PATH_MASTER_BOARD, CODE_MASTER_BOARD);
        matcher.addURI(Constants.AUTHORITY, PATH_MASTER_SCHOOL, CODE_MASTER_SCHOOL);
        matcher.addURI(Constants.AUTHORITY, PATH_MASTER_STANDARD, CODE_MASTER_STANDARD);
        matcher.addURI(Constants.AUTHORITY, PATH_MASTER_SUBJECT, CODE_MASTER_SUBJECT);
        matcher.addURI(Constants.AUTHORITY, PATH_LOG, CODE_LOG);


        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int code = matcher.match(uri);
        Cursor cursor = null;

        if (code == CODE_USER_PROFILE) {
            cursor = database.query(Constants.USER_PROFILE_TABLE, projection, selection, null, null, null, sortOrder);
        }
        if (code == CODE_USER) {
            cursor = database.query(Constants.USER_TABLE, projection, selection, null, null, null, sortOrder);
        }

        if (code == CODE_KID) {
            cursor = database.query(Constants.KID_TABLE, projection, selection, null, null, null, sortOrder);
        }
        if (code == CODE_BOOKS) {
            cursor = database.query(Constants.BOOKS_TABLE, projection, selection, null, null, null, sortOrder);
        }
        if (code == CODE_REQUEST_RESPONSE) {
            cursor = database.query(Constants.REQUEST_RESPONSE_TABLE, projection, selection, null, null, null, sortOrder);
        }
        if (code == CODE_MASTER_BOARD) {
            cursor = database.query(Constants.MASTER_BOARD_TABLE, projection, selection, null, null, null, sortOrder);
        }
        if (code == CODE_MASTER_SCHOOL) {
            cursor = database.query(Constants.MASTER_SCHOOL_TABLE, projection, selection, null, null, null, sortOrder);
        }
        if (code == CODE_MASTER_STANDARD) {
            cursor = database.query(Constants.MASTER_STANDARD_TABLE, projection, selection, null, null, null, sortOrder);
        }
        if (code == CODE_MASTER_SUBJECT) {
            cursor = database.query(Constants.MASTER_SUBJECT_TABLE, projection, selection, null, null, null, sortOrder);
        }
        if (code == CODE_LOG) {
            cursor = database.query(Constants.LOG_TABLE, projection, selection, null, null, null, sortOrder);
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int code = matcher.match(uri);
        float insert = 0;
        if (code == CODE_USER_PROFILE) {
            database.insert(Constants.USER_PROFILE_TABLE, null, values);
        }
        if (code == CODE_USER) {
            database.insert(Constants.USER_TABLE, null, values);
        }
        if (code == CODE_KID) {
            database.insert(Constants.KID_TABLE, null, values);
        }
        if (code == CODE_BOOKS) {
            database.insert(Constants.BOOKS_TABLE, null, values);
        }
        if (code == CODE_REQUEST_RESPONSE) {
            database.insert(Constants.REQUEST_RESPONSE_TABLE, null, values);
        }
        if (code == CODE_MASTER_BOARD) {
            database.insert(Constants.MASTER_BOARD_TABLE, null, values);
        }
        if (code == CODE_MASTER_SCHOOL) {
            database.insert(Constants.MASTER_SCHOOL_TABLE, null, values);
        }
        if (code == CODE_MASTER_STANDARD) {
            database.insert(Constants.MASTER_STANDARD_TABLE, null, values);
        }
        if (code == CODE_MASTER_SUBJECT) {
            database.insert(Constants.MASTER_SUBJECT_TABLE, null, values);
        }
        if (code == CODE_LOG) {
            database.insert(Constants.LOG_TABLE, null, values);
        }

        return null;
    }

    @Override
    public int delete(Uri uri, String where, String[] whereargs) {
        int code = matcher.match(uri);
        int delete = 0;

        if (code == CODE_USER_PROFILE) {
            delete = database.delete(Constants.USER_PROFILE_TABLE, where, whereargs);
        }

        if (code == CODE_USER) {
            delete = database.delete(Constants.USER_TABLE, where, whereargs);
        }

        if (code == CODE_KID) {
            delete = database.delete(Constants.KID_TABLE, where, whereargs);
        }

        if (code == CODE_BOOKS) {
            delete = database.delete(Constants.BOOKS_TABLE, where, whereargs);
        }

        if (code == CODE_REQUEST_RESPONSE) {
            delete = database.delete(Constants.REQUEST_RESPONSE_TABLE, where, whereargs);
        }

        if (code == CODE_MASTER_BOARD) {
            delete = database.delete(Constants.COLUMN_BOARD, where, whereargs);
        }

        if (code == CODE_MASTER_SCHOOL) {
            delete = database.delete(Constants.MASTER_SCHOOL_TABLE, where, whereargs);
        }

        if (code == CODE_MASTER_STANDARD) {
            delete = database.delete(Constants.MASTER_STANDARD_TABLE, where, whereargs);
        }

        if (code == CODE_MASTER_SUBJECT) {
            delete = database.delete(Constants.MASTER_SUBJECT_TABLE, where, whereargs);
        }

        if (code == CODE_LOG) {
            delete = database.delete(Constants.LOG_TABLE, where, whereargs);
        }
        return delete;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int code = matcher.match(uri);
        int row = 0;

        if (code == CODE_USER_PROFILE) {
            row = database.update(Constants.USER_PROFILE_TABLE, values, selection, selectionArgs);
        }
        if (code == CODE_USER) {
            row = database.update(Constants.USER_TABLE, values, selection, selectionArgs);
        }
        if (code == CODE_KID) {
            row = database.update(Constants.KID_TABLE, values, selection, selectionArgs);
        }
        if (code == CODE_BOOKS) {
            row = database.update(Constants.BOOKS_TABLE, values, selection, selectionArgs);
        }
        if (code == CODE_REQUEST_RESPONSE) {
            row = database.update(Constants.REQUEST_RESPONSE_TABLE, values, selection, selectionArgs);
        }
        if (code == CODE_MASTER_BOARD) {
            row = database.update(Constants.MASTER_BOARD_TABLE, values, selection, selectionArgs);
        }
        if (code == CODE_MASTER_SCHOOL) {
            row = database.update(Constants.MASTER_SCHOOL_TABLE, values, selection, selectionArgs);
        }
        if (code == CODE_MASTER_STANDARD) {
            row = database.update(Constants.MASTER_STANDARD_TABLE, values, selection, selectionArgs);
        }
        if (code == CODE_MASTER_SUBJECT) {
            row = database.update(Constants.MASTER_SUBJECT_TABLE, values, selection, selectionArgs);
        }
        if (code == CODE_LOG) {
            row = database.update(Constants.LOG_TABLE, values, selection, selectionArgs);
        }
        return row;
    }
}
