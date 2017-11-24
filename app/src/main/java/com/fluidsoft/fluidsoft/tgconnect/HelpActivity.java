package com.fluidsoft.fluidsoft.tgconnect;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.io.IOException;
import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {

    EditText etHelp;
    Button btnHelpSubmit;
    String help;
    String deviceId;
    private final Jsonparse jsonParser = new Jsonparse(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_help);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        etHelp=(EditText)findViewById(R.id.et_help);

        btnHelpSubmit=(Button)findViewById(R.id.btn_help_submit);
        btnHelpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                help=etHelp.getText().toString();
                deviceId = DeviceID.generateID(HelpActivity.this);
                new helpPage().execute();
            }
        });

    }

    private class helpPage extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            ArrayList<NameValuePair> param = new ArrayList<>();
            param.add(new BasicNameValuePair("Message", help));
            param.add(new BasicNameValuePair("DeviceID", deviceId));

            Log.i("param", "param:" + param);


            try {

                //------------------>>
                HttpGet httppost = new HttpGet(UserInformation.help);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);


                JSONObject jobj = jsonParser.makeHttpRequest(UserInformation.help, "POST", param);


                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    Intent intent = new Intent(HelpActivity.this, HomePageActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(HelpActivity.this, "Error in submit", Toast.LENGTH_LONG);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;


        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            Log.i("tag", "" + result);

        }
    }
}
