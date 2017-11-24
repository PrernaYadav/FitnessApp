package com.fluidsoft.fluidsoft.tgconnect;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {
TextView tg_1,tg_2,tg_3,tg_4,tg_5,tg_6,tg_7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fitness Jobs");
tg_1=(TextView)findViewById(R.id.tg_1);
        tg_2=(TextView)findViewById(R.id.tg_2);
        tg_3=(TextView)findViewById(R.id.tg_3);
        tg_4=(TextView)findViewById(R.id.tg_4);
        tg_5=(TextView)findViewById(R.id.tg_5);
        tg_6=(TextView)findViewById(R.id.tg_6);
        tg_7=(TextView)findViewById(R.id.tg_7);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"Roboto-Regular.ttf");
        tg_1.setTypeface(typeface);
        tg_2.setTypeface(typeface);
        tg_3.setTypeface(typeface);
        tg_4.setTypeface(typeface);
        tg_5.setTypeface(typeface);
        tg_6.setTypeface(typeface);
        tg_7.setTypeface(typeface);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AboutUsActivity.this,HomePageActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
