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

import static com.example.hp.eu.common.Constants.COLUMN_BOOK_ID;
import static com.example.hp.eu.common.Constants.COLUMN_DETAILS;
import static com.example.hp.eu.common.Constants.COLUMN_ID;
import static com.example.hp.eu.common.Constants.COLUMN_IS_ACTIVE;
import static com.example.hp.eu.common.Constants.COLUMN_IS_DELETE;
import static com.example.hp.eu.common.Constants.COLUMN_REQUEST_SENTBY_ID;
import static com.example.hp.eu.common.Constants.COLUMN_REQUEST_SENTTO_ID;
import static com.example.hp.eu.common.Constants.CONTENT_LOG;
import static com.example.hp.eu.common.Constants.URL_FILTER;
import static com.example.hp.eu.common.Constants.URL_GET_LOGIN_DATA;
import static com.example.hp.eu.common.Constants.URL_NEW_USER_DATA;
import static com.example.hp.eu.common.Constants.URL_REQUEST_FILTER;
import static com.example.hp.eu.common.Constants.URL_UPDATE_PROFILE;

public class LogController {
    public static void insertLog(Context context, String id, String book_id, String request_sendby_id, String request_sentto_id,
                                 String details, String is_delete, String is_active) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, id);
            values.put(COLUMN_BOOK_ID, book_id);
            values.put(COLUMN_REQUEST_SENTBY_ID, request_sendby_id);
            values.put(COLUMN_REQUEST_SENTTO_ID, request_sentto_id);
            values.put(COLUMN_DETAILS, details);
            values.put(COLUMN_IS_DELETE, is_delete);
            values.put(COLUMN_IS_ACTIVE, is_active);

            context.getContentResolver().insert(CONTENT_LOG, values);

        } catch (Exception ex) {

        }
    }

    public static void updateLog(Context context, String id, String size) {
        try {
            String selection = COLUMN_ID + " = '" + id + "'";
            Cursor cursor = context.getContentResolver().query(CONTENT_LOG, null, selection, null, null);
            if (cursor.getCount() > 0) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_REQUEST_SENTBY_ID, size);
                context.getContentResolver().update(CONTENT_LOG, values, selection, null);
            }
        } catch (Exception ex) {
        }
    }

    //Filter API
    public static void filterApiCall(final Activity context, final String id, final String user_id, final String status, final String school,
                                     final String standard, final String board_name, final CallBackResult callBackResult) {
        //  final String token  =  FirebaseInstanceId.getInstance().getToken();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FILTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response_String) {
                Log.e("filterApiCall", " loginResponce " + response_String);
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
                    Log.e("LoginController", "filterApiCall error " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackResult.onError(error.getMessage());

                Log.e("LoginController", "filterApiCall voley error " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.fcf_Preference), Context.MODE_PRIVATE);
                params.put("parent_id", id);
                params.put("subject_id", user_id);
                params.put("subject_name", status);
                params.put("school", school);
                params.put("standard", standard);
                params.put("board_name", board_name);
                params.put("fcm_id", MyApplication.setting.getString("UUID", ""));

                Log.e("paramsssslloign", "===" + params.toString());

                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(strReq, "filterApiCall");
    }


    //Request Filter API
    public static void requestFilterApiCall(final Activity context, final String id, final String user_id,final String status, final CallBackResult callBackResult) {
        //  final String token  =  FirebaseInstanceId.getInstance().getToken();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_REQUEST_FILTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response_String) {
                Log.e("requestFilterApiCall", " loginResponce " + response_String);
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
                    Log.e("LoginController", "requestFilterApiCall error " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackResult.onError(error.getMessage());

                Log.e("LoginController", "requestFilterApiCall voley error " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.fcf_Preference), Context.MODE_PRIVATE);
                params.put("id", id);
                params.put("user_id", user_id);
                params.put("status", status);
                params.put("fcm_id",  MyApplication.setting.getString("UUID", ""));

                Log.e("paramsssslloign", "===" + params.toString());

                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(strReq, "requestFilterApiCall");
    }


    //Update Profile
    public static void UpdateUserApiCall(final Activity context,final String user_id, final String first_name, final String last_name, final String phone_number,
                                      final String email_id, final String password, final String profile_image,
                                      final String address, final String city, final CallBackResult callBackResult) {
        //  final String token  =  FirebaseInstanceId.getInstance().getToken();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_UPDATE_PROFILE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response_String) {
                Log.e("UpdateUserApiCall", " loginResponce " + response_String);
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
                    Log.e("UserProfileController", "UpdateUserApiCall error " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackResult.onError(error.getMessage());

                Log.e("UserProfileController", "UpdateUserApiCall voley error " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.fcf_Preference), Context.MODE_PRIVATE);
                params.put("user_id", user_id);
                params.put("first_name", first_name);
                params.put("last_name", last_name);
                params.put("phone_number", phone_number);
                params.put("email_id", email_id);
                params.put("password", password);
                params.put("profile_image", profile_image);
                params.put("address", address);
                params.put("city", city);
                params.put("fcm_id",  MyApplication.setting.getString("UUID", ""));

                Log.e("paramsssslloign", "===" + params.toString());

                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(strReq, "UpdateUserApiCall");
    }


    //Approval Page
    public static void ApprovalPageApiCall(final Activity context,final String provider_id, final String requestor_id,
                                           final CallBackResult callBackResult) {
        //  final String token  =  FirebaseInstanceId.getInstance().getToken();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_UPDATE_PROFILE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response_String) {
                Log.e("ApprovalPageApiCall", " loginResponce " + response_String);
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
                    Log.e("UserProfileController", "ApprovalPageApiCall error " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackResult.onError(error.getMessage());

                Log.e("UserProfileController", "ApprovalPageApiCall voley error " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.fcf_Preference), Context.MODE_PRIVATE);
                params.put("provider_id", provider_id);
                params.put("requestor_id", requestor_id);
                params.put("fcm_id",  MyApplication.setting.getString("UUID", ""));

                Log.e("paramsssslloign", "===" + params.toString());

                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(strReq, "UpdateUserApiCall");
    }

}
