package com.fluidsoft.fluidsoft.tgconnect;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashSplashActivity extends AppCompatActivity {

    ImageView imageView;
    TextView text;
    Button next;
    LinearLayout linearLayout;
    ProgressDialog pd;
    String deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_splash);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.fluidsoft.fluidsoft.tgconnect",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("TAG", "onCreate: Exception" + e.getMessage());

        } catch (NoSuchAlgorithmException e) {
            Log.i("TAG", "onCreate: Exception" + e.getMessage());
        }

      /*  imageView=(ImageView)findViewById(R.id.splash_image_back);*/
        text=(TextView)findViewById(R.id.textView_splash_one_text);
        linearLayout=(LinearLayout) findViewById(R.id.splash_layout);
        next=(Button)findViewById(R.id.btn_splash_one_next);
        text.setText(Html.fromHtml(getString(R.string.splash1_text)));
        if (isNetworkAvailable()) {

            next.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {

                    text.setText(Html.fromHtml(getString(R.string.splash2_text)));
//                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.splash_two));
                    linearLayout.setBackground(getResources().getDrawable(R.drawable.splash_two));

                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            text.setText(Html.fromHtml(getString(R.string.splash3_text)));
//                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.splash_three));
                            linearLayout.setBackground(getResources().getDrawable(R.drawable.splash_three));


                            next.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    deviceId = DeviceID.generateID(SplashSplashActivity.this);
                                    Intent i1 = new Intent(SplashSplashActivity.this, SignInActivity.class);
                                    startActivity(i1);
                                }
                            });
                        }
                    });

                }
            });
        } else {
            Toast.makeText(getApplicationContext(),
                    "You don't have Internet connection. Close the application and restart after Internet Connection", Toast.LENGTH_LONG).show();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
