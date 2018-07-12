package com.example.hp.eu.controllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hp.eu.R;
import com.example.hp.eu.common.CallBackResult;
import com.example.hp.eu.common.MyApplication;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.hp.eu.common.Constants.COLUMN_DOCUMENT;
import static com.example.hp.eu.common.Constants.COLUMN_ID;
import static com.example.hp.eu.common.Constants.COLUMN_IS_ACTIVE;
import static com.example.hp.eu.common.Constants.COLUMN_IS_DELETE;
import static com.example.hp.eu.common.Constants.COLUMN_KID_BOARD;
import static com.example.hp.eu.common.Constants.COLUMN_KID_SCHOOL;
import static com.example.hp.eu.common.Constants.COLUMN_KID_STANDARD;
import static com.example.hp.eu.common.Constants.COLUMN_SCHOOL_FEES;
import static com.example.hp.eu.common.Constants.COLUMN_USER_ID;
import static com.example.hp.eu.common.Constants.CONTENT_KID;
import static com.example.hp.eu.common.Constants.URL_KID;
import static com.example.hp.eu.common.Constants.URL_NEW_USER_DATA;

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


    public static void KidApiCall(final Activity context, final String parent_id, final String kid_name, final String school,
                                  final String standard, final String board, final String school_fees, final String document, final CallBackResult callBackResult) {
        //  final String token  =  FirebaseInstanceId.getInstance().getToken();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_KID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response_String) {
                Log.e("KidApiCall", " loginResponce " + response_String);
                JSONObject response = null;
                try {
                    response = new JSONObject(response_String);
                    if (response.getString("success").matches("1")) {
                        callBackResult.onSuccess("");

                    } else if (response.getString("success").matches("0")) {
                        callBackResult.onError(response.getString("msg"));
                    }

                } catch (Exception e) {
                    callBackResult.onError(e.getMessage());
                    Log.e("UserProfileController", "KidApiCall error " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackResult.onError(error.getMessage());

                Log.e("UserProfileController", "KidApiCall voley error " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.fcf_Preference), Context.MODE_PRIVATE);
                params.put("parent_id", parent_id);
                params.put("kid_name", kid_name);
                params.put("school", school);
                params.put("document", document);
                params.put("standard", standard);
                params.put("board", board);
                params.put("school_fees", school_fees);
                params.put("fcm_id",  MyApplication.setting.getString("UUID", ""));

                Log.e("paramsssslloign", "===" + params.toString());

                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(strReq, "KidApiCall");
    }
}
