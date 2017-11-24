package com.fluidsoft.fluidsoft.tgconnect;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JobSuccess extends AppCompatActivity {
Button btn_job_home;
    TextView textView_text1,textView_text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_success);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fitness Jobs");
   btn_job_home=(Button) findViewById(R.id.btn_job_home);
textView_text1=(TextView)findViewById(R.id.text1);
        textView_text2=(TextView)findViewById(R.id.text2);

        Typeface typeface = Typeface.createFromAsset(getAssets(),  "Roboto-Regular.ttf");
textView_text1.setTypeface(typeface);
        textView_text2.setTypeface(typeface);

        btn_job_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(JobSuccess.this,HomePageActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(JobSuccess.this,CategoryActivity.class));
    }
}
