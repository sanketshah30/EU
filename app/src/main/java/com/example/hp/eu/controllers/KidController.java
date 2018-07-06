package com.example.hp.eu.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import static com.example.hp.eu.common.Constants.*;

public class KidController {
    public static void insertKid(Context context, String id, String user_id, String kid_school, String kid_board, String kid_standard, String school_fees,
                                 String document, String is_delete, String is_active) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, id);
            values.put(COLUMN_USER_ID, user_id);
            values.put(COLUMN_KID_SCHOOL, kid_school);
            values.put(COLUMN_KID_BOARD, kid_board);
            values.put(COLUMN_KID_STANDARD, kid_standard);
            values.put(COLUMN_SCHOOL_FEES, school_fees);
            values.put(COLUMN_DOCUMENT, document);
            values.put(COLUMN_IS_DELETE, is_delete);
            values.put(COLUMN_IS_ACTIVE, is_active);

            context.getContentResolver().insert(CONTENT_KID, values);

        } catch (Exception ex) {

        }
    }

    public static void updateKid(Context context, String id, String kid_standard) {
        try {
            String selection = COLUMN_ID + " = '" + id + "'";
            Cursor cursor = context.getContentResolver().query(CONTENT_KID, null, selection, null, null);
            if (cursor.getCount() > 0) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_KID_STANDARD, kid_standard);

                context.getContentResolver().update(CONTENT_KID, values, selection, null);
            }
        } catch (Exception ex) {
        }
    }


    public static void deleteKid(Context context, String id, String kid_standard) {
        try {
            String selection = COLUMN_ID + " = '" + id + "'";
            Cursor cursor = context.getContentResolver().query(CONTENT_KID, null, selection, null, null);
            if (cursor.getCount() > 0) {
                context.getContentResolver().delete(CONTENT_KID, selection, null);
            }
        } catch (Exception ex) {
        }
    }
}
