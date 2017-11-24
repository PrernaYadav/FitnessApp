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

import com.fluidsoft.fluidsoft.tgconnect.adapter.EventAdapter;
import com.fluidsoft.fluidsoft.tgconnect.model.Event;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class EventsUpcomingActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEvent;
    private EventAdapter eventAdapter;
    private ArrayList<Event> eventArrayList;
//    String apiEvent = "http://api.suvidha.xyz/api/Event";
    ProgressDialog pd;
    public String d, t, l, p, j;
    String joinEvent;
    public Jsonparse jsonParser = new Jsonparse(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Upcoming Events");
        initEvent();
        new FetchEventDataTask().execute();
    }

    private void initEvent() {

        recyclerViewEvent = (RecyclerView) findViewById(R.id.events_recyclerview);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEvent.setHasFixedSize(true);
        eventArrayList = new ArrayList<>();
        eventAdapter = new EventAdapter(eventArrayList, EventsUpcomingActivity.this);
        recyclerViewEvent.setAdapter(eventAdapter);

    }

    public class FetchEventDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(EventsUpcomingActivity.this);
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            Uri uri = Uri.parse(UserInformation.joinEvent);
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

                String events = stringBuffer.toString();

                JSONObject jsonObject = new JSONObject(events);
                Log.i("Response", jsonObject.toString());

                JSONArray eventArray = jsonObject.getJSONArray("UpComingEvent");


                for (int i = 0; i < eventArray.length(); i++) {


                    JSONObject jsonObject1 = (JSONObject) eventArray.get(i);

                    d = jsonObject1.getString("EventDate");
                    t = jsonObject1.getString("EventTime");
                    l = jsonObject1.getString("EventPlace");
                    p = jsonObject1.getString("IMG_URL");
                    j = jsonObject1.getString("Event_Code");

                    Event event1 = new Event();
                    event1.setDateEvent(d);
                    event1.setLocationEvent(l);
                    event1.setTimeEvent(t);
                    event1.setImageEvent(p);
//                    event1.setJoin(j);
                    eventArrayList.add(event1);

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
