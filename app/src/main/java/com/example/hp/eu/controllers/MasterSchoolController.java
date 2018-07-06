package com.example.hp.eu.controllers;

import android.content.ContentValues;
import android.content.Context;

import static com.example.hp.eu.common.Constants.COLUMN_ID;
import static com.example.hp.eu.common.Constants.COLUMN_IS_ACTIVE;
import static com.example.hp.eu.common.Constants.COLUMN_IS_DELETE;
import static com.example.hp.eu.common.Constants.COLUMN_SCHOOL;
import static com.example.hp.eu.common.Constants.CONTENT_MASTER_SCHOOL;

public class MasterSchoolController {
    public static void insertSchool(Context context, String id,String school,  String is_delete, String is_active) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, id);
            values.put(COLUMN_SCHOOL, school);
            values.put(COLUMN_IS_DELETE, is_delete);
            values.put(COLUMN_IS_ACTIVE, is_active);

            context.getContentResolver().insert(CONTENT_MASTER_SCHOOL, values);

        } catch (Exception ex) {

        }
    }
}
