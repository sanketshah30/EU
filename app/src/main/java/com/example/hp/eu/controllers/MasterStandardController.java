package com.example.hp.eu.controllers;

import android.content.ContentValues;
import android.content.Context;

import static com.example.hp.eu.common.Constants.COLUMN_ID;
import static com.example.hp.eu.common.Constants.COLUMN_IS_ACTIVE;
import static com.example.hp.eu.common.Constants.COLUMN_IS_DELETE;
import static com.example.hp.eu.common.Constants.COLUMN_STANDARD;
import static com.example.hp.eu.common.Constants.CONTENT_MASTER_STANDARD;

public class MasterStandardController {
    public static void insertStandard(Context context, String id,String standard,  String is_delete, String is_active) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, id);
            values.put(COLUMN_STANDARD, standard);
            values.put(COLUMN_IS_DELETE, is_delete);
            values.put(COLUMN_IS_ACTIVE, is_active);

            context.getContentResolver().insert(CONTENT_MASTER_STANDARD, values);

        } catch (Exception ex) {

        }
    }
}
