package com.example.hp.eu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.hp.eu.R;
import com.example.hp.eu.common.MyUtils;
import com.example.hp.eu.fragments.ReceiverHomeFragment;
import com.example.hp.eu.fragments.ProfileFragment;
import com.example.hp.eu.fragments.ReceiverRequestsFragment;

public class ReceiverHomeActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private String label;
    FrameLayout frameHome;
    private TextView tv_Fragment_Name, tv_Filters;
    private ImageView action_logout;
    private SearchView action_search;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    MyUtils.replaceFragment(frameHome.getId(), new ReceiverHomeFragment(), ReceiverHomeActivity.this);
                    label = "Home";
                    return true;
                case R.id.provider_navigation_requests:
                    /*item.setIcon(R.drawable.ic_favorite_black_24dp);
                     */
                    MyUtils.replaceFragment(frameHome.getId(), new ReceiverRequestsFragment(), ReceiverHomeActivity.this);
                    label = "My Requests";
                    return true;
                case R.id.provider_navigation_profile:
                    MyUtils.replaceFragment(frameHome.getId(), new ProfileFragment(), ReceiverHomeActivity.this);
                    label = "My Profile";
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home_receiver);

        tv_Filters = findViewById(R.id.tv_filter);
        mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.provider_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        frameHome = findViewById(R.id.frame_home);

        action_logout= findViewById(R.id.action_logout);
        action_search=findViewById(R.id.action_search);
        ReceiverHomeFragment receiverHomeFragment = new ReceiverHomeFragment();
        MyUtils.addFragment(frameHome.getId(), receiverHomeFragment, ReceiverHomeActivity.this);

        action_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout= new Intent(ReceiverHomeActivity.this, LoginActivity.class);
                startActivity(logout);
                finishAffinity();
            }
        });

        action_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action_logout.setVisibility(View.GONE);
            }
        });



    }

}
