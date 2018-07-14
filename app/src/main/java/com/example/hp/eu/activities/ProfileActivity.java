package com.example.hp.eu.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
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
import com.example.hp.eu.model.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    //constant to track image chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "eu";
    public static Uri fileUri;
    public static final int PICK_PHOTO = 1001;
    public static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 1002;
    public static final int CAMERA_PERMISSION_CODE = 101;
    public static final int CAMERA_GALLARY_CODE = 102;
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
                /*Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                */openGallery(ProfileActivity.this);
                /*intent.setAction(Intent.ACTION_GET_CONTENT);//
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            */}
        });

//        getData();

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
            et_fname.setError("Please Enter First Name");
        } else if (TextUtils.isEmpty(et_lname.getText().toString().trim())) {
            is_Valid = false;
            et_lname.setError("Please Enter Last Name");
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
        } else if (city.matches("Select City")) {
            is_Valid = false;
            Toast.makeText(this, "Please Select Your City", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(is_Type)) {
            is_Valid = false;
            Toast.makeText(this, "Please Select User Type", Toast.LENGTH_LONG).show();
        } else if (filePath == null) {
            Toast.makeText(this, "Please Enter image", Toast.LENGTH_SHORT).show();
        }        else if (checkIncome) {

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

    public void openGallery(Activity context) {
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_GALLARY_CODE);
                } else {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_GALLARY_CODE);
                }
            } else {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.putExtra("return-data", true);
                context.startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "openGallery " + e.getMessage());
        }
    }

   /* public void openCamera(Activity context) {
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_PERMISSION_CODE);
                } else {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_PERMISSION_CODE);
                }
            } else {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                fileUri = getOutputMediaFileUri(context, MEDIA_TYPE_IMAGE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                context.startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
            }
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "openCamera " + e.getMessage());
        }
    }*/

    public Uri getOutputMediaFileUri(Context context, int type) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", getOutputMediaFile(type));
        } else
            return Uri.fromFile(getOutputMediaFile(type));

    }

    private static File getOutputMediaFile(int type) {
        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (requestCode == CAMERA_GALLARY_CODE) {
                if (grantResults.length == 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery(this);
                } else
                    Toast.makeText(this, "System does not have permission. Check your permissions", Toast.LENGTH_LONG).show();
            }

        }
    }


    /*

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                filePath = data.getData();
                try {

                    Bitmap bitmap = BitmapFactory.decodeByteArray(MyApplication.byteArray, 0, MyApplication.byteArray.length);
                    image_profile.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (requestCode == 101 && resultCode == RESULT_OK) {
                if (MyApplication.byteArray != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(MyApplication.byteArray, 0, MyApplication.byteArray.length);
                    image_profile.setImageBitmap(bitmap);
                    MyApplication.byteArray = null;
                }
            }
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "onActivityResult " + e.getMessage());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
*/




    public String getFileExtension(Uri uri) {
        Context context = getApplicationContext();
        ContentResolver cR = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public void addToDatabase() {
//        //checking if file is available

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            //displaying progress dialog while image is uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Submitting");


            //getting the storage reference
            if (filePath != null) {
                StorageReference sRef = mStorageRef.child(DBHelperModel.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(filePath));
                progressDialog.show();
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
                                    UserModel userModel= new UserModel();



/*
                                    UserProfileController insertData = new UserProfileController();
                                    insertData.insertUserProfile(ProfileActivity.this, "1", "1", gender + " " + et_fname.getText().toString(), et_lname.getText().toString(),
                                            et_phoneNumber.getText().toString(), et_Email.getText().toString(), et_password.getText().toString(), "0", "1");

                                    UserController insertData1 = new UserController();
                                    insertData1.insertUser(ProfileActivity.this, "1", "1", is_Type, filePath.toString(), et_address.getText().toString()
                                            , city, et_income.getText().toString(), "1", "1");
*/
                                    Intent intent_Kid_Provider = new Intent(ProfileActivity.this, KidDetailsReceiverActivity.class);
                                    intent_Kid_Provider.putExtra("MOBILE_NUMBER", et_phoneNumber.getText().toString().trim());
                                    intent_Kid_Provider.putExtra("USER_TYPE", is_Type);
                                    startActivity(intent_Kid_Provider);


                                } else if (is_Type == "Provider") {
                                    UserProfileController insertData = new UserProfileController();
                                    insertData.insertUserProfile(ProfileActivity.this, "1", "1", gender + " " + et_fname.getText().toString(), et_lname.getText().toString(),
                                            et_phoneNumber.getText().toString(), et_Email.getText().toString(), et_password.getText().toString(), "0", "1");

                                    UserController insertData1 = new UserController();
                                    insertData1.insertUser(ProfileActivity.this,null);

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
                progressDialog.dismiss();
            }
        } else {
            Toast.makeText(this, "Connect the Internet and Try again", Toast.LENGTH_LONG).show();
        }

    }
}
