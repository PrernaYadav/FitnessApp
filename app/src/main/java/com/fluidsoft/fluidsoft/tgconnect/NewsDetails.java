package com.fluidsoft.fluidsoft.tgconnect;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NewsDetails extends AppCompatActivity {

    ImageView img;
    TextView title,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        img=(ImageView)findViewById(R.id.imageview_news_detail);
        title=(TextView)findViewById(R.id.textView_title_news_details);
        des=(TextView)findViewById(R.id.textView_description_news_details);

        Typeface face = Typeface.createFromAsset(getAssets(),
                "LobsterTwo-BoldItalic.ttf");
        title.setTypeface(face);



//        img.setImageResource(getIntent().getIntExtra("IMG_URL",0));
        title.setText(getIntent().getStringExtra("Article_Header"));
        des.setText(Html.fromHtml(getIntent().getStringExtra("Article_Body")));
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("IMG_URL")).asBitmap().into(img);
    }
}
