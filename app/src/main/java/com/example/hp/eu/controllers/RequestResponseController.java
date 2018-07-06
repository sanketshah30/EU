package com.example.hp.eu.controllers;

import android.content.ContentValues;
import android.content.Context;

import static com.example.hp.eu.common.Constants.*;

public class RequestResponseController {
    public static void insertRequestResponse(Context context, String id,String book_id, String requestor_id, String provider_id, String status,
                                             String note, String is_delete, String is_active) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, id);
            values.put(COLUMN_BOOK_ID, book_id);
            values.put(COLUMN_REQUESTOR_ID, requestor_id);
            values.put(COLUMN_PROVIDER_ID, provider_id);
            values.put(COLUMN_STATUS, status);
            values.put(COLUMN_NOTE, note);
            values.put(COLUMN_IS_DELETE, is_delete);
            values.put(COLUMN_IS_ACTIVE, is_active);

            context.getContentResolver().insert(CONTENT_REQUEST_RESPONSE, values);

        } catch (Exception ex) {

        }
    }
}
