package com.fluidsoft.fluidsoft.tgconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fluidsoft.fluidsoft.tgconnect.adapter.YoutubeAdapter;
import com.fluidsoft.fluidsoft.tgconnect.adapter.YoutubeVideo;

import java.util.Vector;


public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    SharedPreferences sharedpreferences;
    private TextToSpeech tts;
    public static final String MyPREFERENCES = "MyPrefs" ;
    RecyclerView recyclerView;
    Vector<YoutubeVideo> youtubeVideoss=new Vector<YoutubeVideo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);






        recyclerView=(RecyclerView)findViewById(R.id.recycler_video);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Iz1BSyJfBqQ\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/aycT9lhW4QU\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/N-A02kGHSdI\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8T0y2e5sRmg?list=PLVTJPJStKeHjK8L3D1YlirQOlLw_ky616\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/O7ETOxxx0ww?list=PLVTJPJStKeHjK8L3D1YlirQOlLw_ky616\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/M9VzpGY_urk?list=PLVTJPJStKeHjK8L3D1YlirQOlLw_ky616\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/5GCcD-_dF5M?list=PLVTJPJStKeHjK8L3D1YlirQOlLw_ky616\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/lcE1GdthjKM?list=PLVTJPJStKeHjK8L3D1YlirQOlLw_ky616\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/yRXDFotfnJ4?list=PLVTJPJStKeHjK8L3D1YlirQOlLw_ky616\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/f87POCDKi3g?list=PLVTJPJStKeHjK8L3D1YlirQOlLw_ky616\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/risVUMVYpQM?list=PLVTJPJStKeHjK8L3D1YlirQOlLw_ky616\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/RPnLVl0J4gw?list=PLVTJPJStKeHjWrFR9cJgUVbBHVR_rBDBk\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qb947uQvuPY?list=PLVTJPJStKeHjWrFR9cJgUVbBHVR_rBDBk\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/FELIRYV_JR8?list=PLVTJPJStKeHjWrFR9cJgUVbBHVR_rBDBk\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/EcPjwXlKU7Y?list=PLVTJPJStKeHjWrFR9cJgUVbBHVR_rBDBk\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ywCXXJCgYXo?list=PLVTJPJStKeHjWrFR9cJgUVbBHVR_rBDBk\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/xa3Ni0eL_I8?list=PLVTJPJStKeHjWrFR9cJgUVbBHVR_rBDBk\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3t3FedRRhmg?list=PLVTJPJStKeHjpPBd97gUxP1R6q9WWIbC9\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/O4EzBHs4BFE?list=PLVTJPJStKeHjpPBd97gUxP1R6q9WWIbC9\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/_Sxt_4dq9Z0?list=PLVTJPJStKeHjlq_1nmVxoek8cfbAcRGNH\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/alg3wC88RpM?list=PLVTJPJStKeHihw7IEVCQVhEt4RKYc4xKR\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/d_xVwRLLY-Y?list=PLVTJPJStKeHihw7IEVCQVhEt4RKYc4xKR\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/sPy-GaHSWYk?list=PLVTJPJStKeHihw7IEVCQVhEt4RKYc4xKR\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/kKlVDHFW2Tk?list=PLVTJPJStKeHihw7IEVCQVhEt4RKYc4xKR\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/6dwcQQuFM44?list=PLVTJPJStKeHihw7IEVCQVhEt4RKYc4xKR\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eY8H6r8U8y0?list=PLVTJPJStKeHihw7IEVCQVhEt4RKYc4xKR\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/OMJBIsmybRg?list=PLVTJPJStKeHihw7IEVCQVhEt4RKYc4xKR\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/tMoJiPXWEuc?list=PLVTJPJStKeHihw7IEVCQVhEt4RKYc4xKR\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/DysA-93da6Y?list=PLVTJPJStKeHihw7IEVCQVhEt4RKYc4xKR\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XUl_yfp52es?list=PLVTJPJStKeHihw7IEVCQVhEt4RKYc4xKR\" frameborder=\"0\" allowfullscreen></iframe>"));


        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qN-QTmSdAf8\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/1-VRKKEhUZY\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4qQrpvDVlwk\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/yLRiPiHNJ44\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/2-bZlBd21Qk\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4VrmTdIg_Ns\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/DMCwzsJFrT0\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/WfE5NZvccbI\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideoss.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/rm5Gq3dH6VY\" frameborder=\"0\" allowfullscreen></iframe>"));
        YoutubeAdapter youtubeAdapter=new YoutubeAdapter(youtubeVideoss);
        recyclerView.setAdapter(youtubeAdapter);












//        getSupportActionBar().setTitle("TG Connect Fitness");
//        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mailll  = sharedpreferences.getString("email",null);
        String mailllType  = sharedpreferences.getString("type",null);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0); // 0-index header
        navigationView.setNavigationItemSelectedListener(this);
navigationView.setItemIconTintList(null);

//        View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        ImageView imgvw = (ImageView) headerLayout.findViewById(R.id.img_email_dp_display);
        TextView tv = (TextView) headerLayout.findViewById(R.id.txt_email_display);

        String emailGMAIL = SignInActivity.nameGmail;
        String emailFB = SignInActivity.emailFB;
        String dp = SignInActivity.profilePictureString;

        String mail = SignInActivity.email;

//        imgvw .setBackground(dpGMAIL);
        String type = UserInformation.loginType;
        /*if (mailllType.equals("G")) {
            tv.setText(mailll);
            imgvw.setImageURI(Uri.parse(dp));

        } else if (mailllType.equals("F")) {
            tv.setText(mailll);
            imgvw.setImageURI(Uri.parse(dp));
        } else if (mailllType.equals("N")) {
            tv.setText(mailll);
            imgvw.setImageDrawable(getResources().getDrawable(R.drawable.become_sponsor));
        }*/


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/

      /*  int itemId = item.getItemId();
        String btnName = null;
        switch (itemId) {
            case R.id.menu_tg_connect_events:
                btnName = "Settings";
                break;
            case R.id.menu_articles_and_videos:
                btnName = "Compass";
                break;
            case R.id.menu_become_sponsored_athlete:
                btnName = "Help";
                break;
            case R.id.menu_notification:
                btnName = "Help";
                break;
            case R.id.menu_help:
                btnName = "Help";
                break;
            case R.id.menu_tg_athlete_recognition:
                btnName = "Help";
                break;
            case R.id.menu_search:
                btnName = "Help";
                break;
        }*/

        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {


          /*  super.onBackPressed();*/
            new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Exit Application!")
                    .setMessage("Are you sure you want to close this application?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tg_connect) {
            // Handle the camera action
            startActivity(new Intent(HomePageActivity.this, ChooseEventActivity.class));
        } else if (id == R.id.nav_become_sponsor) {
            startActivity(new Intent(HomePageActivity.this, DetailFormActivity.class));

        } else if (id == R.id.nav_article_video) {
            startActivity(new Intent(HomePageActivity.this, ArticleAndVideosActivity.class));

        } else if (id == R.id.nav_recognition) {
            startActivity(new Intent(HomePageActivity.this, AthleteActivity.class));

        } else if (id == R.id.nav_about_us) {
            startActivity(new Intent(HomePageActivity.this, AboutUsActivity.class));

        } else if (id == R.id.nav_help) {
            startActivity(new Intent(HomePageActivity.this, HelpActivity.class));

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
