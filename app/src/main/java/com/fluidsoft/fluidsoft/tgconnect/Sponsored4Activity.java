package com.fluidsoft.fluidsoft.tgconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Sponsored4Activity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtTakeMeToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsored4);
       /* toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        getSupportActionBar().setTitle("Sponsored Athlete");

        txtTakeMeToHome=(TextView)findViewById(R.id.take_me_to_home);
        txtTakeMeToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sponsored4Activity.this,HomePageActivity.class));
            }
        });

    }
/*

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
*/

    @Override
    public void onBackPressed() {

        startActivity(new Intent(Sponsored4Activity.this,CategoryActivity.class));
    }
}
