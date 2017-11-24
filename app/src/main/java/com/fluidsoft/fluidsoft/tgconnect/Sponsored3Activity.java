package com.fluidsoft.fluidsoft.tgconnect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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


public class Sponsored3Activity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnuploadphoto, btnuploadid;
    Button btnSubmit;
    Spinner spinLevel, spinCity, spinCategory, spinId;
    public String[] levelArray = new String[]{" Level", "Club", "State", "National", "International"};
    public String[] cityArraay = new String[]{" City", "Gurugram", "Delhi", "Jaipur", "Goa", "Noida", "Punjab", "Mumbai", "Banglore"};
    public String[] categoryArray = new String[]{" Category", "Underprivileged", "Handicapped", "Needy"};
    public String[] iDArray = new String[]{" ID For Identification", "Driving Licence", "PAN Card", "Aadhar Card", "Voter ID Card", "Passport"};
    String message;
    String status;
    private final Jsonparse jsonParser = new Jsonparse(this);
    InputStream inputStream = null;
    String level1, id1, cat1, city1;
    private static int RESULT_LOAD = 1;
    String img_Decodable_Str;
    ImageView imagephoto;
    private Bitmap bitmap,bitmap1;
    private static final int REQUEST_CODE = 1;
    private static final int REQUEST_CODEE = 2;
    String encodedimage,encodedid;
    String deviceId;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsored3);

       /* toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        getSupportActionBar().setTitle("Sponsored Athlete");

        spinLevel = (Spinner) findViewById(R.id.spinner_select_level);
        spinCity = (Spinner) findViewById(R.id.spinner_select_city);
        spinCategory = (Spinner) findViewById(R.id.spinner_select_category);
        spinId = (Spinner) findViewById(R.id.spinner_select_id_for_identification);
        btnuploadphoto=(Button)findViewById(R.id.imageview_upload_photo);
        btnuploadid=(Button)findViewById(R.id.imageview_upload_id);

        final SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.spinner_value_layout, levelArray);
        spinLevel.setAdapter(adapter);

        final SpinnerAdapter adapter1 = new SpinnerAdapter(this, R.layout.spinner_value_layout, cityArraay);
        spinCity.setAdapter(adapter1);

        final SpinnerAdapter adapter2 = new SpinnerAdapter(this, R.layout.spinner_value_layout, categoryArray);
        spinCategory.setAdapter(adapter2);

        final SpinnerAdapter adapter3 = new SpinnerAdapter(this, R.layout.spinner_value_layout, iDArray);
        spinId.setAdapter(adapter3);


        spinLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                Object item = parent.getItemAtPosition(position);
                UserInformation.level = spinLevel.getSelectedItem().toString();
                level1=UserInformation.level;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserInformation.identification = spinId.getSelectedItem().toString();
                id1 = UserInformation.identification;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserInformation.category = spinCategory.getSelectedItem().toString();
                cat1 = UserInformation.category;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserInformation.city = spinCity.getSelectedItem().toString();
                city1 = UserInformation.city;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       /* spinLevel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                spinLevel.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
            }
        });*/

        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinLevel.equals("Select Level") || spinCity.equals("Select City") || spinCategory.equals("Select Category") || spinId.equals("Select ID For Identification")) {
                    Toast.makeText(Sponsored3Activity.this, "Error in selection", Toast.LENGTH_LONG).show();

                }
                deviceId = DeviceID.generateID(Sponsored3Activity.this);

                new sponsorPage().execute();





            }
        });

        btnuploadphoto.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                loadImagefromGallery();
//                btnuploadphoto.clearComposingText();
               /* btnuploadphoto.setBackground(getResources().getDrawable(R.drawable.button_round_corner));
                btnuploadid.setText("Uploaded");
                btnuploadid.setTextColor(getResources().getColor(R.color.white));*/
            }
        });
        btnuploadid.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                loadIdfromGallery();


            }
        });
    }

    private class sponsorPage extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            ArrayList<NameValuePair> param = new ArrayList<>();
            param.add(new BasicNameValuePair("Age", UserInformation.Age));
            param.add(new BasicNameValuePair("Name", UserInformation.Name));
            param.add(new BasicNameValuePair("EmailId", UserInformation.Id));
            param.add(new BasicNameValuePair("MobileNo", UserInformation.mobile));
            param.add(new BasicNameValuePair("LEVEL", level1));
            param.add(new BasicNameValuePair("CITY", city1));
            param.add(new BasicNameValuePair("CAT", cat1));
            param.add(new BasicNameValuePair("ID_Identify", id1));
            param.add(new BasicNameValuePair("Gender", UserInformation.gender));
            param.add(new BasicNameValuePair("IMG_URL", encodedimage));
            param.add(new BasicNameValuePair("ID_URL", encodedid));
            param.add(new BasicNameValuePair("DeviceId", deviceId));


            Log.i("param", "param:" + param);


            try {

                //------------------>>
                HttpGet httppost = new HttpGet(UserInformation.sponsorPage3);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                Log.i("reposnse", "reposnse:" + response);
                JSONObject jobj = jsonParser.makeHttpRequest(UserInformation.sponsorPage3, "POST", param);




                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();
                Log.i("status", "status:" + status);
                if (status == 200) {
                    Intent in = new Intent(Sponsored3Activity.this, Sponsored4Activity.class);
                    startActivity(in);
                } else {

//                    Toast.makeText(Sponsored3Activity.this,"Error",Toast.LENGTH_LONG).show();

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;


        }


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void loadImagefromGallery(){

        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("bmp_Image", bitmap);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE);





    }

    public void loadIdfromGallery(){

        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("bmp_Image", bitmap1);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODEE);

    }

    /*   @Override
       public boolean onOptionsItemSelected(MenuItem item) {
           onBackPressed();
           return true;
       }
   */
    @Override
    public void onBackPressed() {

        startActivity(new Intent(Sponsored3Activity.this, CategoryActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        InputStream stream = null;
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
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

                encodedimage = Base64.encodeToString(byteArray, Base64.DEFAULT);

                Log.i("photoooooooooo", "" + encodedimage);
                btnuploadphoto.setText(("Uploaded"));

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
    }else if (requestCode == REQUEST_CODEE && resultCode == Activity.RESULT_OK){

            try {
                // recyle unused bitmaps
                if (bitmap1 != null) {
                    bitmap1.recycle();
                }
                stream = getContentResolver().openInputStream(data.getData());
                bitmap1 = BitmapFactory.decodeStream(stream);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                encodedid = Base64.encodeToString(byteArray, Base64.DEFAULT);

                Log.i("iddddddddddddddd", "" + encodedid);
                btnuploadid.setText(("Uploaded"));


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
        }
    }
}
