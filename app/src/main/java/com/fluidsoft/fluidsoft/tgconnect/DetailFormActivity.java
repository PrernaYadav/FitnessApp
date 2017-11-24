package com.fluidsoft.fluidsoft.tgconnect;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DetailFormActivity extends AppCompatActivity {

    //    Toolbar toolbar;
    Button btnNext;
    Boolean isFirstTime;

    TextView tv_fill, tv_requireddoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_form);
        /* toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        getSupportActionBar().setTitle("Sponsored Athlete");
        tv_fill = (TextView) findViewById(R.id.tv_fill);
        tv_requireddoc = (TextView) findViewById(R.id.tv_requireddoc);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        tv_fill.setTypeface(typeface);
        tv_requireddoc.setTypeface(typeface);

        tv_fill.setText(Html.fromHtml("<p>Let us help you find sponsor who can financially help you fulfill your dreams to make India proud.<br/>Fill up the form and get your sponsorship in few easy steps. \n" +
                "</p>"));
        tv_requireddoc.setText(Html.fromHtml("<p><b>Required Documents</b> to fill the form:<br/>1.ID Proof Scanned Copy.<br/>2.Differently abled proof if any</p>"));
        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailFormActivity.this, Sponsored2Activity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(DetailFormActivity.this, CategoryActivity.class));
    }

}
