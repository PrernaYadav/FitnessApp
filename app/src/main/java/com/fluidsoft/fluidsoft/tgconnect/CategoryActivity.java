package com.fluidsoft.fluidsoft.tgconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;


public class CategoryActivity extends AppCompatActivity {

    Button btn_sponsor, btn_events, btn_news, btn_athlete, btn_job;
    boolean isFirstTime;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Category");

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");


        btn_sponsor = (Button) findViewById(R.id.btn_sponsor);
        btn_sponsor.setText(Html.fromHtml(getString(R.string.find_me_sponsor)));
        btn_events = (Button) findViewById(R.id.btn_events);
        btn_events.setText(Html.fromHtml(getString(R.string.tg_events)));
        btn_news = (Button) findViewById(R.id.btn_news);
        btn_news.setText(Html.fromHtml(getString(R.string.news_and_articles)));
        btn_athlete = (Button) findViewById(R.id.btn_athlete);
        btn_athlete.setText(Html.fromHtml(getString(R.string.tg_athlete_recognition)));
        btn_job = (Button) findViewById(R.id.btn_jobs);
        btn_job.setText(Html.fromHtml(getString(R.string.tg_athlete_job)));


        btn_sponsor.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {

                btn_sponsor.setBackgroundColor((getResources().getColor(R.color.app_color)));
                btn_sponsor.setTextColor(getResources().getColor(R.color.white));
                btn_events.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_events.setTextColor(getResources().getColor(R.color.black));
                btn_news.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_news.setTextColor(getResources().getColor(R.color.black));
                btn_athlete.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_athlete.setTextColor(getResources().getColor(R.color.black));

                startActivity(new Intent(CategoryActivity.this, DetailFormActivity.class));
              /*  SharedPreferences app_preferences = PreferenceManager
                        .getDefaultSharedPreferences(CategoryActivity.this);

                final SharedPreferences.Editor editor = app_preferences.edit();

                isFirstTime = app_preferences.getBoolean("isFirstTime", true);

                if (isFirstTime) {

//implement your first time logic
                    editor.putBoolean("isFirstTime", false);
                    startActivity(new Intent(CategoryActivity.this, DetailFormActivity.class));

//                    editor.commit();

                } else {
//app open directly

                    startActivity(new Intent(CategoryActivity.this, HomePageActivity.class));
                }
*/
//                startActivity(new Intent(CategoryActivity.this, DetailFormActivity.class));


            }
        });

        btn_job.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {

                btn_job.setBackgroundColor((getResources().getColor(R.color.app_color)));
                btn_job.setTextColor(getResources().getColor(R.color.white));
                btn_events.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_events.setTextColor(getResources().getColor(R.color.black));
                btn_news.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_news.setTextColor(getResources().getColor(R.color.black));
                btn_athlete.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_athlete.setTextColor(getResources().getColor(R.color.black));

                startActivity(new Intent(CategoryActivity.this, JobsActivity.class));


            }
        });

        btn_events.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                btn_sponsor.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_sponsor.setTextColor(getResources().getColor(R.color.black));
                btn_news.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_news.setTextColor(getResources().getColor(R.color.black));
                btn_athlete.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_athlete.setTextColor(getResources().getColor(R.color.black));
                btn_events.setBackgroundColor((getResources().getColor(R.color.app_color)));
                btn_events.setTextColor(getResources().getColor(R.color.white));

//                startActivity(new Intent(CategoryActivity.this, ChooseEventActivity.class));
                startActivity(new Intent(CategoryActivity.this, ChooseEventActivity.class));
//                Toast.makeText(CategoryActivity.this,"Coming Soon",Toast.LENGTH_LONG).show();


            }
        });

        btn_news.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                btn_sponsor.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_sponsor.setTextColor(getResources().getColor(R.color.black));
                btn_events.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_events.setTextColor(getResources().getColor(R.color.black));
                btn_athlete.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_athlete.setTextColor(getResources().getColor(R.color.black));
                btn_news.setBackgroundColor((getResources().getColor(R.color.app_color)));
                btn_news.setTextColor(getResources().getColor(R.color.white));

//                startActivity(new Intent(CategoryActivity.this, ArticleAndVideosActivity.class));
                startActivity(new Intent(CategoryActivity.this, VideoAndArticalActivity.class));


            }
        });

        btn_sponsor.setTypeface(typeface);
        btn_events.setTypeface(typeface);
        btn_news.setTypeface(typeface);
        btn_athlete.setTypeface(typeface);
        btn_job.setTypeface(typeface);


        btn_athlete.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                btn_sponsor.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_sponsor.setTextColor(getResources().getColor(R.color.black));
                btn_events.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_events.setTextColor(getResources().getColor(R.color.black));
                btn_news.setBackgroundColor((getResources().getColor(R.color.white)));
                btn_news.setTextColor(getResources().getColor(R.color.black));
                btn_athlete.setBackgroundColor((getResources().getColor(R.color.app_color)));
                btn_athlete.setTextColor(getResources().getColor(R.color.white));


                startActivity(new Intent(CategoryActivity.this, AthleteActivity.class));


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_sponsor.setBackgroundColor((getResources().getColor(R.color.white)));
        btn_sponsor.setTextColor(getResources().getColor(R.color.black));
        btn_events.setBackgroundColor((getResources().getColor(R.color.white)));
        btn_events.setTextColor(getResources().getColor(R.color.black));
        btn_news.setBackgroundColor((getResources().getColor(R.color.white)));
        btn_news.setTextColor(getResources().getColor(R.color.black));
        btn_athlete.setBackgroundColor((getResources().getColor(R.color.white)));
        btn_athlete.setTextColor(getResources().getColor(R.color.black));


    }


    @Override
    public void onBackPressed() {
       /* Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/

        new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit Application!")
                .setMessage("Are you sure you want to close this application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(intent);
                        finish();

                    }

                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();

    }

}

