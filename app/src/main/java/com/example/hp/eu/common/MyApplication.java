package com.example.hp.eu.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();
    public static SharedPreferences setting;
    private static MyApplication mInstance;
    public static String booking_ID = "";
    private RequestQueue mRequestQueue;
    public static String measurement_Type = "";

    public static synchronized MyApplication getInstance() {
        Log.e("mInstance", "==" + mInstance);
        return mInstance;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        setting = getSharedPreferences("com.raymond.stylist", MODE_PRIVATE);
        Log.e("initializeApp", "==" + FirebaseApp.initializeApp(getApplicationContext()));
// FirebaseApp.initializeApp(getApplicationContext());

// FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseApp.initializeApp(this);

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.e("getToken", "==" + token);


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e(TAG, "onLowMemory()");
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            if (getApplicationContext() != null)
                mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
// set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        RetryPolicy policy = new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        getRequestQueue().add(req);
    }


}