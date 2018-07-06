package com.example.hp.eu.controllers;

import android.content.ContentValues;
import android.content.Context;

import static com.example.hp.eu.common.Constants.COLUMN_ADDRESS;
import static com.example.hp.eu.common.Constants.COLUMN_CITY;
import static com.example.hp.eu.common.Constants.COLUMN_ID;
import static com.example.hp.eu.common.Constants.COLUMN_INCOME;
import static com.example.hp.eu.common.Constants.COLUMN_IS_ACTIVE;
import static com.example.hp.eu.common.Constants.COLUMN_IS_DELETE;
import static com.example.hp.eu.common.Constants.COLUMN_USER_ID;
import static com.example.hp.eu.common.Constants.COLUMN_USER_PROFILE_IMAGE;
import static com.example.hp.eu.common.Constants.COLUMN_USER_TYPE;
import static com.example.hp.eu.common.Constants.CONTENT_USER;

public class UserController {
    public static void insertUser(Context context, String id, String user_id, String user_type, String user_profile_image, String address,
                                        String city, String income, String is_delete, String is_active) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, id);
            values.put(COLUMN_USER_ID, user_id);
            values.put(COLUMN_USER_TYPE, user_type);
            values.put(COLUMN_USER_PROFILE_IMAGE, user_profile_image);
            values.put(COLUMN_ADDRESS,address);
            values.put(COLUMN_CITY, city);
            values.put(COLUMN_INCOME, income);
            values.put(COLUMN_IS_DELETE, is_delete);
            values.put(COLUMN_IS_ACTIVE, is_active);

            context.getContentResolver().insert(CONTENT_USER, values);

        } catch (Exception ex) {

        }
    }
}
