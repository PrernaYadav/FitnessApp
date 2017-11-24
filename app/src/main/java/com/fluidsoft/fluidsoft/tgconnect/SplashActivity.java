package com.fluidsoft.fluidsoft.tgconnect;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.fluidsoft.fluidsoft.tgconnect.R.color.app_color;

public class SplashActivity extends AppCompatActivity {

    public static int SPLASH_TIME_OUT = 3000;
    int flag = 0;
    ImageView imageView, line;
    TextView textView;
    Button button;
    LinearLayout linearLayout;
//    Boolean isFirstTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash1);

        imageView = (ImageView) findViewById(R.id.splash_imageview);
        line = (ImageView) findViewById(R.id.splash_line);
        textView = (TextView) findViewById(R.id.splash_textview);
        linearLayout = (LinearLayout) findViewById(R.id.layout);

        textView.setText(Html.fromHtml(getString(R.string.splash1_text)));
        button = (Button) findViewById(R.id.splash_button);


     /*   SharedPreferences app_preferences = PreferenceManager
                .getDefaultSharedPreferences(SplashActivity.this);

        SharedPreferences.Editor editor = app_preferences.edit();

        isFirstTime = app_preferences.getBoolean("isFirstTime", true);

        if (isFirstTime) {

//implement your first time logic
            editor.putBoolean("isFirstTime", false);
            editor.commit();

        }else{
//app open directly

            startActivity(new Intent(SplashActivity.this,CategoryActivity.class));
        }*/

        if (isNetworkAvailable()) {

            button.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    imageView.setBackgroundColor(getResources().getColor(R.color.white));
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.splash_2));
                    line.setBackgroundColor(getResources().getColor(R.color.app_color));
                    line.setImageDrawable(getResources().getDrawable(R.drawable.line_3));
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
                    textView.setText(Html.fromHtml(getString(R.string.splash2_text)));
                    textView.setTextColor(getResources().getColor(app_color));
                    button.setTextColor(getResources().getColor(R.color.white));
                    button.setBackground(getResources().getDrawable(R.drawable.button_round_corner));

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageView.setBackgroundColor(getResources().getColor(R.color.app_color));
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.splash_3));
                            line.setImageDrawable(getResources().getDrawable(R.drawable.line_3));
                            textView.setText(Html.fromHtml(getString(R.string.splash3_text)));
                            textView.setTextColor(getResources().getColor(R.color.white));
                            linearLayout.setBackgroundColor(getResources().getColor(app_color));
                            button.setTextColor(getResources().getColor(app_color));
//                        button.setBackgroundColor(getResources().getColor(R.color.white));
                            button.setBackground(getResources().getDrawable(R.drawable.white_rounded_edit_text));
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent i1 = new Intent(SplashActivity.this, SignInActivity.class);
                                    startActivity(i1);
                                }
                            });
                        }
                    });

               /* if (isNetworkAvailable()) {

                    for (int i = 0; i < 2; i++) {
                        if (flag == 0) {
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.splash_2));
                            textView.setText(Html.fromHtml(getString(R.string.splash2_text)));
                            button.setBackgroundColor(getResources().getColor(R.color.white));
                        } else if (flag == 1) {
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.splash_3));
                            textView.setText(Html.fromHtml(getString(R.string.splash3_text)));
                            button.setBackgroundColor(getResources().getColor(R.color.app_color));
                        } else {
                            Intent i1 = new Intent(SplashActivity.this, SignInActivity.class);
                            startActivity(i1);
                        }
                    }
                }else {
                    Toast.makeText(getApplicationContext(),
                            "You don't have Internet connection. Close the application and restart after Internet Connection", Toast.LENGTH_LONG).show();
                }*/
                }
            });
        } else {
            Toast.makeText(getApplicationContext(),
                    "You don't have Internet connection. Close the application and restart after Internet Connection", Toast.LENGTH_LONG).show();
        }


        // code for getting facebook key
   /*     try {
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
        }*/

       /* if (isNetworkAvailable()) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);

        } else {
            Toast.makeText(getApplicationContext(),
                    "You don't have Internet connection. Close the application and restart after Internet Connection", Toast.LENGTH_LONG).show();
        }*/
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
