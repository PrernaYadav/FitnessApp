package com.fluidsoft.fluidsoft.tgconnect;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.fluidsoft.fluidsoft.tgconnect.adapter.EventsPreviousAdapter;
import com.fluidsoft.fluidsoft.tgconnect.model.EventPrevious;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class EventsPreviousActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEventPrevious;
    private EventsPreviousAdapter eventAdapterPrevious;
    private ArrayList<EventPrevious> eventArrayListPrevious;
    //    String apiEvent = "http://api.suvidha.xyz/api/Event";
    ProgressDialog pd;
    public String d, t, l, p;
    String joinEvent;
    public Jsonparse jsonParser = new Jsonparse(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_previous);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Previous Events");
        initEventPrevious();
        new FetchEventPreviousDataTask().execute();
    }

    private void initEventPrevious() {

        recyclerViewEventPrevious = (RecyclerView) findViewById(R.id.events_previous_recyclerview);
        recyclerViewEventPrevious.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEventPrevious.setHasFixedSize(true);
        eventArrayListPrevious = new ArrayList<>();
        eventAdapterPrevious = new EventsPreviousAdapter(eventArrayListPrevious, EventsPreviousActivity.this);
        recyclerViewEventPrevious.setAdapter(eventAdapterPrevious);

    }

    public class FetchEventPreviousDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(EventsPreviousActivity.this);
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            Uri uri = Uri.parse(UserInformation.joinEventPrevious);
            URL url;

            try {
                url = new URL(uri.toString());
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String lineEvent;

                while ((lineEvent = bufferedReader.readLine()) != null) {
                    stringBuffer.append(lineEvent + "\n");

                }
                if (stringBuffer.length() == 0) {
                    return null;
                }

                String eventsprevious= stringBuffer.toString();

                JSONObject jsonObject = new JSONObject(eventsprevious);
                Log.i("Response", jsonObject.toString());

                JSONArray eventArray = jsonObject.getJSONArray("PreviousEvent");


                for (int i = 0; i < eventArray.length(); i++) {

                    JSONObject jsonObject1 = (JSONObject) eventArray.get(i);

                    d = jsonObject1.getString("EventDate");
                    t = jsonObject1.getString("EventTime");
                    l = jsonObject1.getString("EventPlace");
                    p = jsonObject1.getString("IMG_URL");


                    EventPrevious event1 = new EventPrevious();
                    event1.setDateEventPrevious(d);
                    event1.setLocationEventPrevious(l);
                    event1.setTimeEventPrevious(t);
                    event1.setImageEventPrevious(p);
                    eventArrayListPrevious.add(event1);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pd.dismiss();
        }
    }

}
