package com.fluidsoft.fluidsoft.tgconnect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JobsActivity extends AppCompatActivity {
    EditText etDesignation, etExperience, etMinimumSalary;
    Button btnSubmit, btn_resume;
    private final Jsonparse jsonParser = new Jsonparse(this);
    String deviceId;
    String designation, experience, salary;
    private Bitmap bitmap;
    private static final int REQUEST_CODE_JOB = 1;
    String encodedResume,uresume;
    ProgressDialog pd;


//    TextView tv_certificates, tv_resume;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fitness Jobs");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");

//finding ID from Layout
        btn_resume = (Button) findViewById(R.id.btn_job_resume);
        btn_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadResumefromGallery();
            }
        });

//        tv_certificates = (TextView) findViewById(R.id.tv_job_certificates);
        etDesignation = (EditText) findViewById(R.id.et_job_designation);
        etExperience = (EditText) findViewById(R.id.et_job_experience);
        etMinimumSalary = (EditText) findViewById(R.id.et_job_salary);
        etDesignation.setPressed(false);
        etExperience.setPressed(false);
        etMinimumSalary.setPressed(false);


        etDesignation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etDesignation.setBackground(etDesignation.hasFocus() ? getResources().getDrawable(R.drawable.rounded_edit_text) : getResources().getDrawable(R.drawable.red_rounded_edit_text));
                etDesignation.setTextColor(etDesignation.hasFocus() ? getResources().getColor(R.color.app_color) : getResources().getColor(R.color.red));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etExperience.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etExperience.setBackground(etExperience.hasFocus() ? getResources().getDrawable(R.drawable.rounded_edit_text) : getResources().getDrawable(R.drawable.red_rounded_edit_text));
                etExperience.setTextColor(etExperience.hasFocus() ? getResources().getColor(R.color.app_color) : getResources().getColor(R.color.red));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etMinimumSalary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etMinimumSalary.setBackground(etMinimumSalary.hasFocus() ? getResources().getDrawable(R.drawable.rounded_edit_text) : getResources().getDrawable(R.drawable.red_rounded_edit_text));
                etMinimumSalary.setTextColor(etMinimumSalary.hasFocus() ? getResources().getColor(R.color.app_color) : getResources().getColor(R.color.red));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etDesignation.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFocusChange(View v, boolean hasFocus) {


                if (hasFocus) {
                    etDesignation.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etDesignation, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });

        btn_resume.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                btn_resume.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));

                loadResumefromGallery();

            }
        });


        etMinimumSalary.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFocusChange(View v, boolean hasFocus) {


                if (hasFocus) {
                    etMinimumSalary.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etMinimumSalary, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });

        etExperience.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFocusChange(View v, boolean hasFocus) {


                if (hasFocus) {
                    etExperience.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etExperience, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });

        btnSubmit = (Button) findViewById(R.id.btn_job_submit);


        etDesignation.setTypeface(typeface);
        etExperience.setTypeface(typeface);
        etMinimumSalary.setTypeface(typeface);
        btn_resume.setTypeface(typeface);
//        tv_certificates.setTypeface(typeface);
        btnSubmit.setTypeface(typeface);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDesignation.length() < 0 || etDesignation.getText().toString().equals("")) {
                    etDesignation.setBackground(getResources().getDrawable(R.drawable.red_rounded_edit_text));
                    etDesignation.setTextColor(getResources().getColor(R.color.red));
                } else if (etExperience.length() < 1 || etExperience.getText().toString().equals("")) {
                    etExperience.setBackground(getResources().getDrawable(R.drawable.red_rounded_edit_text));
                    etExperience.setTextColor(getResources().getColor(R.color.red));
                } else if (etMinimumSalary.length() < 1 || etMinimumSalary.getText().toString().equals("")) {
                    etMinimumSalary.setBackground(getResources().getDrawable(R.drawable.red_rounded_edit_text));
                    etMinimumSalary.setTextColor(getResources().getColor(R.color.red));
                } else {


                    designation = etDesignation.getText().toString();
                    experience = etExperience.getText().toString();
                    salary = etMinimumSalary.getText().toString();
                    deviceId = DeviceID.generateID(JobsActivity.this);


                    new jobPage().execute();

                   /* Intent intent = new Intent(JobsActivity.this, JobSuccess.class);
                    startActivity(intent);
*/
                }
            }
        });
    }

    private class jobPage extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(JobsActivity.this);
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {

            ArrayList<NameValuePair> param = new ArrayList<>();
            param.add(new BasicNameValuePair("Designation", designation));
            param.add(new BasicNameValuePair("Experience", experience));
            param.add(new BasicNameValuePair("Salary", salary));
            param.add(new BasicNameValuePair("DeviceID", deviceId));
            param.add(new BasicNameValuePair("Resume",encodedResume));

            Log.i("param", "param:" + param);


            try {

                //------------------>>
                HttpGet httppost = new HttpGet(UserInformation.job);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);


                JSONObject jobj = jsonParser.makeHttpRequest(UserInformation.job, "POST", param);


                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    Intent intent = new Intent(JobsActivity.this, JobSuccess.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(JobsActivity.this, "Error in submit", Toast.LENGTH_LONG);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;


        }

        @Override
        protected void onPostExecute(Boolean result) {

                pd.dismiss();

        }
    }
    public void loadResumefromGallery(){

        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("bmp_Image", bitmap);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_JOB);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        InputStream stream = null;
        if (requestCode == REQUEST_CODE_JOB && resultCode == Activity.RESULT_OK){
            try {
                // recyle unused bitmaps
                if (bitmap != null) {
                    bitmap.recycle();
                }
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                encodedResume = Base64.encodeToString(byteArray, Base64.DEFAULT);



                Log.i("resumeeeeeeee", "" + encodedResume);
                btn_resume.setText(("Uploaded"));

//                imagephoto.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }else {

        }
    }
}
