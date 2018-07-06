package com.example.hp.eu.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hp.eu.R;
import com.example.hp.eu.adapter.UpdateProfileAdapter;
import com.example.hp.eu.model.UpdateProfileModel;

import java.util.ArrayList;

public class EditKidDetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerview_ads;
    private UpdateProfileAdapter adapter;
    ArrayList<UpdateProfileModel> ads = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kid_details_lv);

        recyclerview_ads = findViewById(R.id.recyclerview_editkid);
        recyclerview_ads.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UpdateProfileAdapter(this, ads);
        recyclerview_ads.setAdapter(adapter);
    }
}
