package com.fluidsoft.fluidsoft.tgconnect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, TextWatcher {

    EditText et_username, et_email, et_password;
    Button btn_signup;
    TextView txt_forgot_password;
    Button btn_signin_facebook, btn_signin_google, btn_signin_twitter, btn_signin_instagram;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";//    LoginButton btn_signin_facebook;

    SharedPreferences sharedpreferences;
    CallbackManager callbackManager;
    //    SignInButton google_signin;
    GoogleApiClient googleApiClient;
    private int RC_SIGN_IN = 100;
    private final Jsonparse jsonParser = new Jsonparse(this);
    public static String name, email, pass;
    public static String loginType;
    public static String emailGmail;
    public static String nameGmail;
    public static Uri dpGmail;
    public static String profilePictureString;
    public static Uri fb_profile_pic;
    public static String emailFB;
    Boolean isFirstTime;
    public static final String Email = "emailKey";
    public static final String Type = "emailType";
    public static final String MyPREFERENCES = "MyPrefs" ;
    Toolbar toolbar;
    String deviceIdd;
    SharedPreferences.Editor editor1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        String deviceId =Settings.Secure.getString(this.getContentResolver(),Settings
                .Secure.ANDROID_ID);
        String build=Build.SERIAL;
                /*Settings.Secure.getString(this.getContentResolver(),android.os.Build.SERIAL);*/


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            public void onCompleted(JSONObject jsonObject, GraphResponse response) {

                                Profile profile = Profile.getCurrentProfile();
                                fb_profile_pic = profile.getProfilePictureUri(200, 200);
                                profilePictureString = String.valueOf(fb_profile_pic);
                                Bundle facebookData = getFacebookData(jsonObject);
                                emailFB = facebookData.getString("emailfb");
                               /* idFB = facebookData.getString("idFacebook");
                                nameFB = facebookData.getString("name");
                                gender = facebookData.getString("gender");
                                about = facebookData.getString("about");
                                birthday = facebookData.getString("birthday");

*/
                            }
                        });


                        loginType = "F";
                        Intent intent = new Intent(SignInActivity.this, CategoryActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(SignInActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(SignInActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        setContentView(R.layout.activity_sign_in);

        et_username = (EditText) findViewById(R.id.et_username);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);


        et_username.setPressed(false);
        et_email.setPressed(false);
        et_password.setPressed(false);

        et_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

        btn_signup = (Button) findViewById(R.id.btn_sign_up);
        txt_forgot_password = (TextView) findViewById(R.id.txt_forgot_password);
        btn_signin_facebook = (Button) findViewById(R.id.signin_facebook);
//        btn_signin_facebook = (LoginButton) findViewById(R.id.signin_facebook);
//        btn_signin_facebook.setBackgroundResource(R.drawable.facebook_small);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("login", 0); // 0 - for private mode
        editor1= pref.edit();


        btn_signin_google = (Button) findViewById(R.id.signin_google);
        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_username.setBackground(et_username.hasFocus() ? getResources().getDrawable(R.drawable.rounded_edit_text) : getResources().getDrawable(R.drawable.red_rounded_edit_text));
                et_username.setTextColor(et_username.hasFocus() ? getResources().getColor(R.color.app_color) : getResources().getColor(R.color.red));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_email.setBackground(et_email.hasFocus() ? getResources().getDrawable(R.drawable.rounded_edit_text) : getResources().getDrawable(R.drawable.red_rounded_edit_text));
                et_email.setTextColor(et_email.hasFocus() ? getResources().getColor(R.color.app_color) : getResources().getColor(R.color.red));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_password.setBackground(et_password.hasFocus() ? getResources().getDrawable(R.drawable.rounded_edit_text) : getResources().getDrawable(R.drawable.red_rounded_edit_text));
                et_password.setTextColor(et_password.hasFocus() ? getResources().getColor(R.color.app_color) : getResources().getColor(R.color.red));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFocusChange(View v, boolean hasFocus) {


                if (hasFocus) {
                    et_username.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(et_username, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });


        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFocusChange(View v, boolean hasFocus) {
               /* et_username.setPressed(false);
                et_username.setFocusable(true);
                et_password.setPressed(false);
                et_password.setFocusable(true);*/

                if (hasFocus) {

                    et_email.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));

                    InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm1.showSoftInput(et_email, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFocusChange(View v, boolean hasFocus) {
               /* et_password.setActivated(true);
                et_password.setPressed(true);
                et_password.setFocusable(true);*/

                if (hasFocus) {
                    et_password.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));

                    InputMethodManager imm2 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm2.showSoftInput(et_password, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });

        SharedPreferences app_preferences = PreferenceManager
                .getDefaultSharedPreferences(SignInActivity.this);

        final SharedPreferences.Editor editor = app_preferences.edit();

        isFirstTime = app_preferences.getBoolean("isFirstTime", true);

        if (isFirstTime) {

//implement your first time logic
            editor.putBoolean("isFirstTime", false);
            editor.commit();

        }else{
//app open directly

            startActivity(new Intent(SignInActivity.this,CategoryActivity.class));
        }
        //google sign in

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        btn_signin_google = (Button) findViewById(R.id.signin_google);

        btn_signin_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor1.putString("email", emailGmail);
                editor1.putString("type",loginType);

                // Storing string
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        btn_signin_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
editor1.putString("email",emailFB);
                editor1.putString("type",loginType);
                LoginManager.getInstance().logInWithReadPermissions(SignInActivity.this, Arrays.asList("public_profile", "user_friends"));
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {



                loginType = "N";

                name = et_username.getText().toString();
                email = et_email.getText().toString();
                pass = et_password.getText().toString();


                editor1.putString("email",email);
                editor1.putString("type",loginType);

                //store
              /*  SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Email, email);
                editor.putString(Type,loginType);
                editor.commit();*/
                if (name.equals("")) {
                    et_username.setBackground(getResources().getDrawable(R.drawable.red_rounded_edit_text));
                    et_username.setTextColor(getResources().getColor(R.color.red));


                } else if (et_email.length() < 5 || et_email.getText().toString().equals("") || !et_email.getText().toString().trim().matches(emailPattern)) {
                    et_email.setTextColor(getResources().getColor(R.color.red));
                    et_email.setBackground(getResources().getDrawable(R.drawable.red_rounded_edit_text));


                } else if (pass.equals("")) {

                    et_password.setTextColor(getResources().getColor(R.color.red));
                    et_password.setBackground(getResources().getDrawable(R.drawable.red_rounded_edit_text));

                } else {

/*
deviceId = DeviceID.generateID(JobsActivity.this);
                    et_username.addTextChangedListener(new );
                    et_username.setTextColor(getResources().getColor(R.color.app_color));
                    et_email.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                    et_username.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                    et_password.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                    et_email.setTextColor(getResources().getColor(R.color.app_color));
                    et_password.setTextColor(getResources().getColor(R.color.app_color));
*/
                    deviceIdd = DeviceID.generateID(SignInActivity.this);
                    new signinPage().execute();
                }

            }
        });
    /*    et_username.setTextColor(getResources().getColor(R.color.app_color));
        et_password.setTextColor(getResources().getColor(R.color.app_color));
        et_email.setTextColor(getResources().getColor(R.color.app_color));
*/


    }

    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();
        try {
            String id = object.getString("id");


            bundle.putString("idFacebook", id);
            if (object.has("name"))
                bundle.putString("name", object.getString("name"));
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("emailfb"))
                bundle.putString("emailfb", object.getString("emailfb"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("about"))
                bundle.putString("about", object.getString("about"));
            if (object.has("phone_number"))
                bundle.putString("phone_number", object.getString("phone_number"));


        } catch (Exception e) {
            Log.d("TAG", "BUNDLE Exception : " + e.toString());
        }

        return bundle;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

  /*  @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        et_username.setTextColor(et_username.hasFocus() ? getResources().getColor(R.color.app_color) : getResources().getColor(R.color.red));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }*/

    private class signinPage extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            ArrayList<NameValuePair> param = new ArrayList<>();
            param.add(new BasicNameValuePair("UserName", name));
            param.add(new BasicNameValuePair("EmailId", email));
            param.add(new BasicNameValuePair("Password", pass));
            param.add(new BasicNameValuePair("DeviceID",deviceIdd));

            Log.i("param", "param:" + param);


            try {

                //------------------>>
                HttpGet httppost = new HttpGet(UserInformation.signup);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);


                JSONObject jobj = jsonParser.makeHttpRequest(UserInformation.signup, "POST", param);


                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    Intent intent = new Intent(SignInActivity.this, CategoryActivity.class);
                    startActivity(intent);
                } else {

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;


        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            Log.i("tag", "" + result);

        }
    }

    /*  @Override
      protected void onActivityResult(int requestCode, int resultCode, Intent data) {
          callbackManager.onActivityResult(requestCode, resultCode, data);
      }*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (callbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("TAG", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            loginType = "G";


            nameGmail = acct.getDisplayName();
            emailGmail = acct.getEmail();
            dpGmail = acct.getPhotoUrl();
            profilePictureString = String.valueOf(dpGmail);
//            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            Intent i = new Intent(SignInActivity.this, CategoryActivity.class);
            startActivity(i);
//            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }

}
