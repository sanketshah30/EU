package com.example.hp.eu.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.eu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private EditText et_PhoneNumber;
    private EditText et_Otp;
    private FirebaseAuth auth;
    private String verification_code;
    private TextView tv_SendOtp, tv_register, verify;
    private CheckBox cbremember;
    private ImageView icon_lock;
    boolean connected = false;
    private RelativeLayout layout_noInternetLayout;


    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    /***** SHAREDPREFERENCES INSTANCES AND STRING FOR THE PATH *****/
    SharedPreferences prefsNagSetting;
    private static final String NAG_PREFS = "socially_you_nag_prefs";

    /* THE EDITOR */
    SharedPreferences.Editor editor;
    private String NAG_SETTING;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        /*
        loading = findViewById(R.id.progress);
        card_view = findViewById(R.id.card_view);*/
        et_PhoneNumber = findViewById(R.id.et_PhoneNumber);
        et_Otp = findViewById(R.id.et_Otp);
        tv_SendOtp = findViewById(R.id.tv_SendOtp);
        verify = findViewById(R.id.verify_textview);
        cbremember = findViewById(R.id.cbremember_me);
        auth = FirebaseAuth.getInstance();
//        tv_register = findViewById(R.id.register_textview);
        icon_lock = findViewById(R.id.icon_lock);
        layout_noInternetLayout = findViewById(R.id.layout_no_internet);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            layout_noInternetLayout.setVisibility(View.GONE);
            prefsNagSetting = getApplicationContext().getSharedPreferences(NAG_PREFS, Context.MODE_PRIVATE);

//getSupportActionBar().hide();
            mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                    Log.e("phoneAuthCredential", "=" + phoneAuthCredential);
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Log.e("FirebaseException", "=" + e);

                }

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    verification_code = s;
                    Toast.makeText(getApplicationContext(), "Code sent to the number " + et_PhoneNumber.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            };

/*
            tv_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_register = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(intent_register);
                }
            });
*/
            cbremember.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // EDITOR INSTANCE TO SAVE THE NAG SETTING
                    editor = prefsNagSetting.edit();

                    // GET THE NAG SETTING CHECKBOX
                    if (cbremember.isChecked()) {

                        editor.putBoolean(NAG_SETTING, true);
                    } else {
                        editor.putBoolean(NAG_SETTING, false);
                    }

                    editor.commit();
                }
            });
        } else {
            connected = false;
        }
    }


    @Override
    public void onRefresh() {

    }

    public void send_sms(View v) {
        et_Otp.setVisibility(View.VISIBLE);
        verify.setVisibility(View.VISIBLE);
        tv_SendOtp.setVisibility(View.GONE);
        icon_lock.setVisibility(View.VISIBLE);
        String phonenumber = et_PhoneNumber.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phonenumber, 60, TimeUnit.SECONDS, this, mCallback
        );
    }

    public void signInWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User signed in successfully", Toast.LENGTH_SHORT).show();
                            Intent profile = new Intent(LoginActivity.this, ProfileActivity.class);
                            profile.putExtra("phone_Number", et_PhoneNumber.getText().toString());
                            startActivity(profile);
                            finishAffinity();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect OTP.", Toast.LENGTH_SHORT).show();
                            et_Otp.getText().clear();
                            et_Otp.requestFocus();
                        }

                    }
                });
    }

    public void verify(View v) {
        String input_code = et_Otp.getText().toString();
        if (verification_code != null) {
            verifyPhoneNumber(verification_code, input_code);
        }

    }

    public void verifyPhoneNumber(String verification_code, String input_code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_code, input_code);
        signInWithPhone(credential);
    }
}