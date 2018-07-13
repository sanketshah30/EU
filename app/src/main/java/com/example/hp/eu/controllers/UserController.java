package com.example.hp.eu.controllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hp.eu.R;
import com.example.hp.eu.common.CallBackResult;
import com.example.hp.eu.common.MyApplication;
import com.example.hp.eu.model.UserModel;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.hp.eu.common.Constants.*;

public class UserController {
    public static void insertUser(Context context, String id, String user_id, String user_type, String user_profile_image, String address,
                                  String city, String income, String is_delete, String is_active) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, id);
            values.put(COLUMN_USER_ID, user_id);
            values.put(COLUMN_USER_TYPE, user_type);
            values.put(COLUMN_USER_PROFILE_IMAGE, user_profile_image);
            values.put(COLUMN_ADDRESS, address);
            values.put(COLUMN_CITY, city);
            values.put(COLUMN_INCOME, income);
            values.put(COLUMN_IS_DELETE, is_delete);
            values.put(COLUMN_IS_ACTIVE, is_active);

            context.getContentResolver().insert(CONTENT_USER, values);

        } catch (Exception ex) {

        }
    }

    public static void loginApiCall(final Activity context, final String mobile_number, final CallBackResult callBackResult) {
        //  final String token  =  FirebaseInstanceId.getInstance().getToken();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_GET_LOGIN_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response_String) {
                Log.e("loginApiCall", " loginResponce " + response_String);
                JSONObject response = null;
                try {
                    response = new JSONObject(response_String);
                    if (response.getString("success").matches("1")) {
                        JSONObject jsonObject = response.getJSONObject("data");
                        UserModel userModel= new UserModel();
                        userModel.setFirst_name(jsonObject.getString("first_name"));
                        userModel.setLast_name(jsonObject.getString("last_name"));
                        userModel.setPhone_number(jsonObject.getString("phone_number"));
                        userModel.setEmail_id(jsonObject.getString("email_id"));
                        userModel.setRequestor_provider(jsonObject.getString("requestor_provider"));
                        userModel.setProfile_image(jsonObject.getString("profile_image"));

                        callBackResult.onSuccess(userModel);

                    } else if (response.getString("success").matches("0")) {
                        callBackResult.onError(response.getString("msg"));
                    }

                } catch (Exception e) {
                    callBackResult.onError(e.getMessage());
                    Log.e("LoginController", "loginApiCall error " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackResult.onError(error.getMessage());

                Log.e("LoginController", "loginApiCall voley error " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.fcf_Preference), Context.MODE_PRIVATE);
                params.put("mobile_number", mobile_number);
                params.put("fcm_id", MyApplication.setting.getString("UUID", ""));

                Log.e("paramsssslloign", "===" + params.toString());

                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(strReq, "loginApiCall");
    }


}
