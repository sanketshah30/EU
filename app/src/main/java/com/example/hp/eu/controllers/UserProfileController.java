package com.example.hp.eu.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import static com.example.hp.eu.common.Constants.*;

public class UserProfileController {
    public void insertUserProfile(Context context, String id, String user_id, String first_name, String last_name, String phone_number, String email_id,
                                        String password, String is_delete, String is_active) {
        try {
            ContentValues values = new ContentValues();

            values.put(COLUMN_ID, id);
            values.put(COLUMN_USER_ID, user_id);
            values.put(COLUMN_USER_FIRST_NAME, first_name);
            values.put(COLUMN_USER_LAST_NAME, last_name);
            values.put(COLUMN_PHONE_NUMBER, phone_number);
            values.put(COLUMN_EMAIL_ID,email_id);
            values.put(COLUMN_PASSWORD, password);
            values.put(COLUMN_IS_DELETE, is_delete);
            values.put(COLUMN_IS_ACTIVE, is_active);

            context.getContentResolver().insert(CONTENT_USER_PROFILE, values);

        } catch (Exception ex) {

        }
    }



    public static void updateProfile(Context context, String id, String first_name,String last_name, String phone_number, String email_id,
                                     String password, String is_delete, String is_active) {
        try {
            String selection = COLUMN_ID + " = '" + id + "'";
            Cursor cursor = context.getContentResolver().query(CONTENT_USER_PROFILE, null, selection, null, null);
            if (cursor.getCount() > 0) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_USER_FIRST_NAME, first_name);
                values.put(COLUMN_USER_LAST_NAME, last_name);
                values.put(COLUMN_PHONE_NUMBER, phone_number);
                values.put(COLUMN_EMAIL_ID,email_id);
                values.put(COLUMN_PASSWORD, password);
                values.put(COLUMN_IS_DELETE, is_delete);
                values.put(COLUMN_IS_ACTIVE, is_active);

                context.getContentResolver().update(CONTENT_USER_PROFILE, values, selection, null);
            }
        } catch (Exception ex) {
        }
    }
}
