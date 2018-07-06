package com.example.hp.eu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hp.eu.R;
import com.example.hp.eu.adapter.SubjectAdapter;
import com.example.hp.eu.controllers.BooksController;
import com.example.hp.eu.controllers.KidController;
import com.example.hp.eu.controllers.MasterBoardController;
import com.example.hp.eu.model.SubjectModel;

import java.util.ArrayList;

public class KidDetailsProviderActivity extends AppCompatActivity {

    private TextView tv_SubmitandAddKid, tv_Submit;
    private EditText et_child, et_Description;
    private SubjectAdapter subjectAdapter;
    private RecyclerView recyclerview_ads;
    ArrayList<SubjectModel> array_Data = new ArrayList<SubjectModel>();

    private Spinner spinner_Board, spinner_school, spinner_Standard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_details_provider);
        recyclerview_ads = findViewById(R.id.rv_subjects);

        et_child = findViewById(R.id.et_child);
        spinner_school = findViewById(R.id.spinner_school);/*
        et_Description = findViewById(R.id.et_Description);*/
        spinner_Board = findViewById(R.id.spinner_Board);
        spinner_Standard = findViewById(R.id.spinner_Standard);

        tv_SubmitandAddKid = findViewById(R.id.tv_SubmitandAddKid);
        tv_Submit = findViewById(R.id.tv_Submit);

        tv_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KidController kidController = new KidController();
                BooksController booksController = new BooksController();
                MasterBoardController masterBoardController = new MasterBoardController();
                kidController.insertKid(KidDetailsProviderActivity.this, "1", "1", spinner_school.getSelectedItem().toString().trim(),
                        spinner_Board.getSelectedItem().toString().trim(), spinner_Standard.getSelectedItem().toString().trim(), null, null, "1", "0");
                masterBoardController.insertBoard(KidDetailsProviderActivity.this, "2", spinner_Board.getSelectedItem().toString().trim(), "0", "1");
                Intent homepage = new Intent(KidDetailsProviderActivity.this, ProviderHomeActivity.class);
                startActivity(homepage);
                finishAffinity();
            }
        });

        tv_SubmitandAddKid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addtoDatabase();
                KidController kidController = new KidController();
                BooksController booksController = new BooksController();
                MasterBoardController masterBoardController = new MasterBoardController();
                kidController.insertKid(KidDetailsProviderActivity.this, "1", "1", spinner_school.getSelectedItem().toString().trim(),
                        spinner_Board.getSelectedItem().toString().trim(), spinner_Standard.getSelectedItem().toString().trim(), null, null, "1", "0");
                masterBoardController.insertBoard(KidDetailsProviderActivity.this, "2", spinner_Board.getSelectedItem().toString().trim(), "0", "1");

                et_child.setText("");
                et_Description.setText("");

            }
        });


        recyclerview_ads.setLayoutManager(new GridLayoutManager(this, 3));
        subjectAdapter = new SubjectAdapter(this, array_Data);
        recyclerview_ads.setAdapter(subjectAdapter);
    }
}
