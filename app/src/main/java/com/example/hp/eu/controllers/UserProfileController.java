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

import static com.example.hp.eu.common.Constants.COLUMN_EMAIL_ID;
import static com.example.hp.eu.common.Constants.COLUMN_ID;
import static com.example.hp.eu.common.Constants.COLUMN_IS_ACTIVE;
import static com.example.hp.eu.common.Constants.COLUMN_IS_DELETE;
import static com.example.hp.eu.common.Constants.COLUMN_PASSWORD;
import static com.example.hp.eu.common.Constants.COLUMN_PHONE_NUMBER;
import static com.example.hp.eu.common.Constants.COLUMN_USER_FIRST_NAME;
import static com.example.hp.eu.common.Constants.COLUMN_USER_ID;
import static com.example.hp.eu.common.Constants.COLUMN_USER_LAST_NAME;
import static com.example.hp.eu.common.Constants.CONTENT_USER_PROFILE;
import static com.example.hp.eu.common.Constants.URL_GET_LOGIN_DATA;
import static com.example.hp.eu.common.Constants.URL_NEW_USER_DATA;

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

    public static void newUserApiCall(final Activity context, final String first_name, final String last_name, final String mobile_number,
                                      final String email_id, final String password, final String requestor_provider, final String profile_image,
                                      final String address, final String city, final String income, final CallBackResult callBackResult) {
        //  final String token  =  FirebaseInstanceId.getInstance().getToken();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_NEW_USER_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response_String) {
                Log.e("newUserApiCall", " loginResponce " + response_String);
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
                    Log.e("UserProfileController", "newUserApiCall error " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackResult.onError(error.getMessage());

                Log.e("UserProfileController", "newUserApiCall voley error " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.fcf_Preference), Context.MODE_PRIVATE);
                params.put("first_name", first_name);
                params.put("last_name", last_name);
                params.put("mobile_number", mobile_number);
                params.put("email_id", email_id);
                params.put("password", password);
                params.put("requestor_provider", requestor_provider);
                params.put("profile_image", profile_image);
                params.put("address", address);
                params.put("city", city);
                params.put("income", income);
                params.put("fcm_id",  MyApplication.setting.getString("UUID", ""));

                Log.e("paramsssslloign", "===" + params.toString());

                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(strReq, "newUserApiCall");
    }
}
