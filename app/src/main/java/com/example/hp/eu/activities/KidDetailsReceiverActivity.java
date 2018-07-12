package com.example.hp.eu.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.eu.R;
import com.example.hp.eu.controllers.KidController;

import java.io.File;

public class KidDetailsReceiverActivity extends AppCompatActivity {

    public static final int PICK_FILE_REQUEST = 1;
    private TextView tv_SubmitandAddKid, tv_Submit, tv_Upload, tvFileName;
    private EditText et_child, et_childfees;
    private Spinner spinner_School, spinner_Board, spinner_Standard;
    private String uriString;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_details_receiver);

        tv_Upload = findViewById(R.id.tv_upload);
        et_child = findViewById(R.id.et_child);
        et_childfees = findViewById(R.id.et_childfees);
        spinner_School = findViewById(R.id.spinner_school);
        spinner_Board = findViewById(R.id.spinner_Board);
        spinner_Standard = findViewById(R.id.spinner_Standard);
        tvFileName = findViewById(R.id.tv_file_name);
        tv_SubmitandAddKid = findViewById(R.id.tv_SubmitandAddKid);
        tv_Submit = findViewById(R.id.tv_Submit);

        tv_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KidController kidController = new KidController();
                kidController.insertKid(KidDetailsReceiverActivity.this, "1", "1", spinner_School.getSelectedItem().toString().trim(),
                        spinner_Board.getSelectedItem().toString().trim(), spinner_Standard.getSelectedItem().toString().trim(),
                        et_childfees.getText().toString(), uriString, "1", "0");

                Toast.makeText(KidDetailsReceiverActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                Intent homepage = new Intent(KidDetailsReceiverActivity.this, ReceiverHomeActivity.class);
                startActivity(homepage);
                finishAffinity();
            }
        });

        tv_SubmitandAddKid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KidController kidController = new KidController();
                kidController.insertKid(KidDetailsReceiverActivity.this, "1", "1", spinner_School.getSelectedItem().toString().trim(),
                        spinner_Board.getSelectedItem().toString().trim(), spinner_Standard.getSelectedItem().toString().trim(), et_childfees.getText().toString(), uriString, "1", "0");
                et_child.setText("");
                et_childfees.setText("");
                uri = null;
                tvFileName.setText("");

            }
        });

        tv_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_FILE_REQUEST);


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK) {
            uri = data.getData();
            uriString = uri.toString();
            File myFile = new File(uriString);
            String path = myFile.getAbsolutePath();
            String displayName = null;
            if (uriString.startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor = getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            } else if (uriString.startsWith("file://")) {
                displayName = myFile.getName();
            }

            tvFileName.setText(/*returnCursor.getString(nameIndex)*/ displayName);
        }
    }
}

