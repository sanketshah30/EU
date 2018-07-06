package com.example.hp.eu.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hp.eu.R;
import com.example.hp.eu.adapter.RequestorProfileAdapter;
import com.example.hp.eu.model.RequestorProfileModel;

import java.util.ArrayList;

public class ReceiverProfileActivity extends AppCompatActivity {
    private RecyclerView recyclerview_ads;
    ArrayList<RequestorProfileModel> ads = new ArrayList<>();
    private RequestorProfileAdapter requestorProfileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestor_profile);

        recyclerview_ads = findViewById(R.id.recyclerview_receiver_kids);
        recyclerview_ads.setLayoutManager(new LinearLayoutManager(this));
        requestorProfileAdapter = new RequestorProfileAdapter(this, ads);
        recyclerview_ads.setAdapter(requestorProfileAdapter);

    }
}
