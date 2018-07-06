package com.example.hp.eu.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import static com.example.hp.eu.common.Constants.COLUMN_BOOK_ID;
import static com.example.hp.eu.common.Constants.COLUMN_DETAILS;
import static com.example.hp.eu.common.Constants.COLUMN_ID;
import static com.example.hp.eu.common.Constants.COLUMN_IS_ACTIVE;
import static com.example.hp.eu.common.Constants.COLUMN_IS_DELETE;
import static com.example.hp.eu.common.Constants.COLUMN_REQUEST_SENTBY_ID;
import static com.example.hp.eu.common.Constants.COLUMN_REQUEST_SENTTO_ID;
import static com.example.hp.eu.common.Constants.CONTENT_LOG;

public class LogController {
    public static void insertLog(Context context, String id,String book_id, String request_sendby_id, String request_sentto_id,
                                             String details, String is_delete, String is_active) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, id);
            values.put(COLUMN_BOOK_ID, book_id);
            values.put(COLUMN_REQUEST_SENTBY_ID, request_sendby_id);
            values.put(COLUMN_REQUEST_SENTTO_ID, request_sentto_id);
            values.put(COLUMN_DETAILS, details);
            values.put(COLUMN_IS_DELETE, is_delete);
            values.put(COLUMN_IS_ACTIVE, is_active);

            context.getContentResolver().insert(CONTENT_LOG, values);

        } catch (Exception ex) {

        }
    }

    public static void updateLog(Context context, String id, String size) {
        try {
            String selection = COLUMN_ID + " = '" + id + "'";
            Cursor cursor = context.getContentResolver().query(CONTENT_LOG, null, selection, null, null);
            if (cursor.getCount() > 0) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_REQUEST_SENTBY_ID, size);
                context.getContentResolver().update(CONTENT_LOG, values, selection, null);
            }
        } catch (Exception ex) {
        }
    }
}
