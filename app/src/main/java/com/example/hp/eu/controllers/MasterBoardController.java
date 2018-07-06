package com.example.hp.eu.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import static com.example.hp.eu.common.Constants.COLUMN_BOARD;
import static com.example.hp.eu.common.Constants.COLUMN_ID;
import static com.example.hp.eu.common.Constants.COLUMN_IS_ACTIVE;
import static com.example.hp.eu.common.Constants.COLUMN_IS_DELETE;
import static com.example.hp.eu.common.Constants.CONTENT_MASTER_BOARD;

public class MasterBoardController {
    public static void insertBoard(Context context, String id,String board,  String is_delete, String is_active) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, id);
            values.put(COLUMN_BOARD, board);
            values.put(COLUMN_IS_DELETE, is_delete);
            values.put(COLUMN_IS_ACTIVE, is_active);

            context.getContentResolver().insert(CONTENT_MASTER_BOARD, values);

        } catch (Exception ex) {

        }
    }
    public static void updateMasterBoard(Context context, String id, String board) {
        try {
            String selection = COLUMN_ID + " = '" + id + "'";
            Cursor cursor = context.getContentResolver().query(CONTENT_MASTER_BOARD, null, selection, null, null);
            if (cursor.getCount() > 0) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_BOARD, board);

                context.getContentResolver().update(CONTENT_MASTER_BOARD, values, selection, null);
            }
        } catch (Exception ex) {
        }
    }
}
