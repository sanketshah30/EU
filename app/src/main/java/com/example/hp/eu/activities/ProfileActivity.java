package com.example.hp.eu.activities;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.eu.R;
import com.example.hp.eu.common.MyUtils;
import com.example.hp.eu.controllers.UserController;
import com.example.hp.eu.controllers.UserProfileController;
import com.example.hp.eu.model.DBHelperModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    //constant to track image chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private EditText et_fname, et_lname, et_phoneNumber, et_password, et_address, et_Email, et_ConfirmPassword, et_income;
    private ImageView image_profile;
    private boolean checkIncome = false;
    private RadioButton rb_receive, rb_upload;
    private String is_Type = "", Sincome, city, gender;
    private TextView tv_Submit;
    private View input_Layout_Income;
    private Spinner spinner_city, spinner_gender;
    //Uri to store file
    private Uri filePath;
    private Toolbar myToolbar;
    String image;
    //Firebase objects
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        et_fname = findViewById(R.id.et_Fname);
        et_lname = findViewById(R.id.et_Lname);
        et_phoneNumber = findViewById(R.id.et_PhoneNumber);
        et_Email = findViewById(R.id.et_Email);
        et_password = findViewById(R.id.et_Password);
        et_ConfirmPassword = findViewById(R.id.et_ConfirmPassword);
        et_address = findViewById(R.id.et_Address);
        et_income = findViewById(R.id.et_income);
        image_profile = findViewById(R.id.imageview_profile);
        tv_Submit = findViewById(R.id.tv_Submit);
        rb_receive = findViewById(R.id.Rb_receive);
        rb_upload = findViewById(R.id.Rb_upload);
        spinner_city = findViewById(R.id.spinner_city);
        spinner_gender = findViewById(R.id.spinner_gender);
        input_Layout_Income = findViewById(R.id.input_Layout_Income);
        Sincome = et_income.getText().toString();
        String phone_Number = getIntent().getStringExtra("phone_Number");
        et_phoneNumber.setText(phone_Number);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(DBHelperModel.DATABASE_PATH_UPLOADS);


        tv_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification();
            }
        });

        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        getData();

        rb_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb_receive.isChecked()) {
                    checkIncome = true;
                    input_Layout_Income.setVisibility(View.VISIBLE);
                    et_income.setVisibility(View.VISIBLE);
                }
            }
        });


        rb_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb_upload.isChecked()) {
                    checkIncome = false;
                    input_Layout_Income.setVisibility(View.GONE);
                }
            }
        });


    }

    private void getData() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();
        Query query = reference1.child("Users").orderByChild("address").equalTo("505");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Log.e("dataSnapshot", "==" + dataSnapshot.getValue());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void verification() {
        MyUtils myUtils = new MyUtils();
        boolean is_Valid = true;
        city = spinner_city.getSelectedItem().toString();
        gender = spinner_gender.getSelectedItem().toString();
        if (rb_receive.isChecked()) {
            is_Type = "Receiver";
        } else if (rb_upload.isChecked()) {
            is_Type = "Provider";
        }
        if (TextUtils.isEmpty(et_fname.getText().toString().trim())) {
            is_Valid = false;
            et_fname.setError("Please Enter Name");
        } else if (TextUtils.isEmpty(et_lname.getText().toString().trim())) {
            is_Valid = false;
            et_lname.setError("Please Enter Phone Number");
        } else if (TextUtils.isEmpty(et_phoneNumber.getText().toString().trim())) {
            is_Valid = false;
            et_phoneNumber.setError("Please Enter Phone Number");
        } else if (et_phoneNumber.getText().toString().trim().length() < 10) {
            is_Valid = false;
            et_phoneNumber.setError("Please Enter Valid Phone Number");
        } else if (TextUtils.isEmpty(et_Email.getText().toString().trim())) {
            is_Valid = false;
            et_Email.setError("Please Enter Email");
        } else if (!myUtils.emailValidator(et_Email.getText().toString().trim())) {
            is_Valid = false;
            et_Email.setError("Please Enter Valid Email");
        } else if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
            is_Valid = false;
            et_password.setError("Please Enter Password");
        } else if (TextUtils.isEmpty(et_ConfirmPassword.getText().toString().trim())) {
            is_Valid = false;
            et_ConfirmPassword.setError("Confirm your Password");
        } else if (!et_password.getText().toString().trim().matches(et_ConfirmPassword.getText().toString().trim())) {
            is_Valid = false;
            et_ConfirmPassword.setError("Please Enter Correct Password");
        } else if (TextUtils.isEmpty(et_address.getText().toString().trim())) {
            is_Valid = false;
            et_address.setError("Please Enter Complete Address");
        } else if (TextUtils.isEmpty(city)) {
            is_Valid = false;
            Toast.makeText(this, "Please Select Your City", Toast.LENGTH_LONG).show();
        }/*else if ( city="Select City"){
            is_Valid=false;
            Toast.makeText(this, "Please Select Your City", Toast.LENGTH_LONG).show();
        }*/ else if (TextUtils.isEmpty(is_Type)) {
            is_Valid = false;
            Toast.makeText(this, "Please Select An Option", Toast.LENGTH_LONG).show();
        } else if (filePath == null) {
            Toast.makeText(this, "Please Enter image", Toast.LENGTH_SHORT).show();
        } else if (checkIncome) {

            if (TextUtils.isEmpty(et_income.getText().toString().trim())) {
                is_Valid = false;
                et_income.setError("Please Enter your total Income");
            } else {
                if (et_income.getText().toString().trim().matches("0")) {
                    is_Valid = false;
                    et_income.setError("Please Enter your total Income");
                }
            }
        } else {

            is_Valid = true;

        }
        if (is_Valid) {
            addToDatabase();
        }

    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_provider_logout) {
            Intent login = new Intent(this, LoginActivity.class);
            Toast.makeText(this, "Logged out Successfully", Toast.LENGTH_LONG).show();
            startActivity(login);
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image_profile.setImageBitmap(bitmap);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byte[] bArray = bos.toByteArray();
                image = bArray.toString();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public void addToDatabase() {
//        //checking if file is available
/*        Bitmap image = BitmapFactory.decodeResource(getResources(),
                R.id.imageview_profile);
       final String image_db= image.toString();*/

        //displaying progress dialog while image is uploading
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Submitting");
        progressDialog.show();

        //getting the storage reference
        StorageReference sRef = mStorageRef.child(DBHelperModel.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(filePath));
        if (filePath != null) {

        //adding the file to reference
        sRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //dismissing the progress dialog
                        progressDialog.dismiss();

                        //displaying success toast
                        Toast.makeText(getApplicationContext(), "Success! ", Toast.LENGTH_LONG).show();


                        if (is_Type == "Receiver") {


                            UserProfileController insertData = new UserProfileController();
                            insertData.insertUserProfile(ProfileActivity.this, "1", "1", gender + " " + et_fname.getText().toString(), et_lname.getText().toString(),
                                    et_phoneNumber.getText().toString(), et_Email.getText().toString(), et_password.getText().toString(), "0", "1");

                            UserController insertData1 = new UserController();
                            insertData1.insertUser(ProfileActivity.this, "1", "1", is_Type, image, et_address.getText().toString()
                                    , city, et_income.getText().toString(), "1", "1");
                            Intent intent_Kid_Provider = new Intent(ProfileActivity.this, KidDetailsReceiverActivity.class);
                            intent_Kid_Provider.putExtra("MOBILE_NUMBER", et_phoneNumber.getText().toString().trim());
                            intent_Kid_Provider.putExtra("USER_TYPE", is_Type);
                            startActivity(intent_Kid_Provider);


                        } else if (is_Type == "Provider") {
                            UserProfileController insertData = new UserProfileController();
                            insertData.insertUserProfile(ProfileActivity.this, "1", "1", gender + " " + et_fname.getText().toString(), et_lname.getText().toString(),
                                    et_phoneNumber.getText().toString(), et_Email.getText().toString(), et_password.getText().toString(), "0", "1");

                            UserController insertData1 = new UserController();
                            insertData1.insertUser(ProfileActivity.this, "1", "1", is_Type, image, et_address.getText().toString()
                                    , city, et_income.getText().toString(), "1", "1");

                            Intent intent_Kid_Provider = new Intent(ProfileActivity.this, KidDetailsProviderActivity.class);
                            intent_Kid_Provider.putExtra("MOBILE_NUMBER", et_phoneNumber.getText().toString().trim());
                            intent_Kid_Provider.putExtra("USER_TYPE", is_Type);
                            startActivity(intent_Kid_Provider);

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        //displaying the upload progress
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploading " + ((int) progress) + "%...");
                    }
                });
        } else {
            image_profile.setImageResource(R.drawable.background);
        }
    }

}
