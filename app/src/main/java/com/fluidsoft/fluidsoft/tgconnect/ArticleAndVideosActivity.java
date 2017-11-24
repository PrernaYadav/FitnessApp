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

import com.fluidsoft.fluidsoft.tgconnect.adapter.NewsAdapter;
import com.fluidsoft.fluidsoft.tgconnect.model.News;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ArticleAndVideosActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private ArrayList<News> newsAdapterArrayList;
//    String api = "https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=top&apiKey=1187b11829bd40a8bcd96b9d5cae4874";
    String api ="http://api.suvidha.xyz/api/Article";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_and_videos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("TG Articles");
        init();
        new FetchDataTask().execute();

    }

    private void init() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(newsAdapter);

        recyclerView.setHasFixedSize(true);
        newsAdapterArrayList = new ArrayList<>();
        newsAdapter = new NewsAdapter(newsAdapterArrayList, ArticleAndVideosActivity.this,this);
        recyclerView.setAdapter(newsAdapter);
    }

    public class FetchDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(ArticleAndVideosActivity.this);
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

                JSONArray newsArray = jsonObject.getJSONArray("Article");

                for (int i = 0; i < newsArray.length(); i++) {
                    String t, d, u, p;

                   /* JSONObject jNews =  newsArray.getJSONObject(i);
                    jNews = respon.getJSONObject("articles");
*/
                    JSONObject jsonObject1 = (JSONObject) newsArray.get(i);

                    t = jsonObject1.getString("Article_Header");
                    d = jsonObject1.getString("Article_Body");
//                    u = jsonObject1.getString("url");
                    p = jsonObject1.getString("IMG_URL");


//                    String tmpHtml =  Html.fromHtml(d).toString();
//                    String tmpHtml1 =Html.fromHtml(d).toString();

//                    Log.i("dataaaaaaaaa", "        " + t + "              " + u + "        " + p);

                    News news = new News();
                    news.setTitle(t);
                    news.setDescription(d);
//                    news.setUrl(u);
                    news.setImageUrl(p);

                    newsAdapterArrayList.add(news);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pd.dismiss();
            newsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ArticleAndVideosActivity.this,CategoryActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}