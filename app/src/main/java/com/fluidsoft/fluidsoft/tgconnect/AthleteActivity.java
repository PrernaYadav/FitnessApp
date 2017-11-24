package com.fluidsoft.fluidsoft.tgconnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.fluidsoft.fluidsoft.tgconnect.adapter.AthleteAdapter;
import com.fluidsoft.fluidsoft.tgconnect.model.Athlete;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AthleteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AthleteAdapter athleteAdapter;
    private ArrayList<Athlete> athleteArrayList;
    //    String api = "https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=top&apiKey=1187b11829bd40a8bcd96b9d5cae4874";
    private String api ="http://api.suvidha.xyz/api/Athelete";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Athlete Recognition");
        init();
        new AthleteFetchDataTask().execute();
    }
    private void init() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView = (RecyclerView) findViewById(R.id.athlete_recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(athleteAdapter);



        recyclerView.setHasFixedSize(true);
        athleteArrayList = new ArrayList<>();
        athleteAdapter = new AthleteAdapter(athleteArrayList, AthleteActivity.this);
        recyclerView.setAdapter(athleteAdapter);


    }
    public class AthleteFetchDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(AthleteActivity.this);
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String newssss;
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            Uri uri = Uri.parse(api);
            URL url;
            try {
                url = new URL(uri.toString());
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
//                httpURLConnection.setRequestProperty();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line + "\n");
                }

                if (stringBuffer.length() == 0) {
                    return null;
                }
                newssss = stringBuffer.toString();
                JSONObject jsonObject = new JSONObject(newssss);
                Log.i("Response", jsonObject.toString());

                JSONArray athleteArray = jsonObject.getJSONArray("Athlete");

                for (int i = 0; i < athleteArray.length(); i++) {


                    String p, n,l;

                   /* JSONObject jNews =  newsArray.getJSONObject(i);
                    jNews = respon.getJSONObject("articles");
*/
                    JSONObject jsonObject1 = (JSONObject) athleteArray.get(i);

                    p = jsonObject1.getString("IMG_URL");
                    n = jsonObject1.getString("Athlete_Name");
//                    u = jsonObject1.getString("url");
//                    p = jsonObject1.getString("IMG_URL");

//                    Log.i("dataaaaaaaaa", "        " + t + "              " + u + "        " + p);

                    Athlete athlete = new Athlete();
                    athlete.setAthletePhoto(p);
                    athlete.setAthleteName(n);
//                    news.setUrl(u);
//                    news.setImageUrl(p);

                    athleteArrayList.add(athlete);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pd.dismiss();
            athleteAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(AthleteActivity.this,CategoryActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
