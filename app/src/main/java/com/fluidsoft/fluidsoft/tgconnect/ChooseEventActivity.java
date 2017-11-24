package com.fluidsoft.fluidsoft.tgconnect;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseEventActivity extends AppCompatActivity {

    Button upcomingEvents,previousEvents;
    TextView txt1,txt2,txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_choose_enevts);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("TG Events");


        upcomingEvents=(Button) findViewById(R.id.upcoming_event);
        previousEvents=(Button) findViewById(R.id.previous_event);






        Typeface face = Typeface.createFromAsset(getAssets(),
                "Roboto-Regular.ttf");
        upcomingEvents.setTypeface(face);
        previousEvents.setTypeface(face);

        upcomingEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseEventActivity.this,EventsUpcomingActivity.class));
            }
        });

        previousEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ChooseEventActivity.this,EventsPreviousActivity.class));

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ChooseEventActivity.this,CategoryActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
