package com.example.hp.eu.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import static com.example.hp.eu.common.Constants.*;

public class BooksController {
    public static void insertKid(Context context, String id,String kid_id, String user_id, String city,String description, String board,
                                 String standard, String subject, String book_list, String is_delete, String is_active) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, id);
            values.put(COLUMN_KID_ID, kid_id);
            values.put(COLUMN_USER_ID, user_id);
            values.put(COLUMN_CITY, city);
            values.put(COLUMN_BOARD, board);
            values.put(COLUMN_DESCRIPTION, description);
            values.put(COLUMN_STANDARD, standard);
            values.put(COLUMN_SUBJECT, subject);
            values.put(COLUMN_BOOK_LIST, book_list);
            values.put(COLUMN_IS_DELETE, is_delete);
            values.put(COLUMN_IS_ACTIVE, is_active);

            context.getContentResolver().insert(CONTENT_BOOKS, values);

        } catch (Exception ex) {

        }
    }
    public static void updateBooks(Context context, String id, String book_list) {
        try {
            String selection = COLUMN_ID + " = '" + id + "'";
            Cursor cursor = context.getContentResolver().query(CONTENT_BOOKS, null, selection, null, null);
            if (cursor.getCount() > 0) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_BOOK_LIST, book_list);

                context.getContentResolver().update(CONTENT_BOOKS, values, selection, null);
            }
        } catch (Exception ex) {
        }
    }
}
