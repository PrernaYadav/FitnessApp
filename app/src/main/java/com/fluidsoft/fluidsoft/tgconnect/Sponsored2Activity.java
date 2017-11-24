package com.fluidsoft.fluidsoft.tgconnect;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Sponsored2Activity extends AppCompatActivity implements TextWatcher {

    Toolbar toolbar;
    Button btnNext2;
    EditText etAge, etName, etMail, etMobile;
//    ImageButton imgbtnFemale, imgbtnMale;
    Button imgbtnFemale, imgbtnMale;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//    public String male,female;
boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsored2);
       /* toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        getSupportActionBar().setTitle("Sponsored Athlete");

        etAge = (EditText) findViewById(R.id.et_age);
        etName = (EditText) findViewById(R.id.et_full_name);
        etMail = (EditText) findViewById(R.id.et_email_id);
        etMobile = (EditText) findViewById(R.id.et_mobile_number);

        etAge.setPressed(false);
        etName.setPressed(false);
        etMail.setPressed(false);
        etMobile.setPressed(false);

        etAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etAge.setBackground(etAge.hasFocus() ? getResources().getDrawable(R.drawable.rounded_edit_text) : getResources().getDrawable(R.drawable.red_rounded_edit_text));
                etAge.setTextColor(etAge.hasFocus() ? getResources().getColor(R.color.app_color) : getResources().getColor(R.color.red));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etName.setBackground(etName.hasFocus() ? getResources().getDrawable(R.drawable.rounded_edit_text) : getResources().getDrawable(R.drawable.red_rounded_edit_text));
                etName.setTextColor(etName.hasFocus() ? getResources().getColor(R.color.app_color) : getResources().getColor(R.color.red));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etMail.setBackground(etMail.hasFocus() ? getResources().getDrawable(R.drawable.rounded_edit_text) : getResources().getDrawable(R.drawable.red_rounded_edit_text));
                etMail.setTextColor(etMail.hasFocus() ? getResources().getColor(R.color.app_color) : getResources().getColor(R.color.red));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etMobile.setBackground(etMobile.hasFocus() ? getResources().getDrawable(R.drawable.rounded_edit_text) : getResources().getDrawable(R.drawable.red_rounded_edit_text));
                etMobile.setTextColor(etMobile.hasFocus() ? getResources().getColor(R.color.app_color) : getResources().getColor(R.color.red));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etAge.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFocusChange(View v, boolean hasFocus) {


                if (hasFocus) {
                    etAge.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etAge, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });

        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFocusChange(View v, boolean hasFocus) {


                if (hasFocus) {
                    etName.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etName, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });

        etMail.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFocusChange(View v, boolean hasFocus) {


                if (hasFocus) {
                    etMail.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etMail, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });
        etMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFocusChange(View v, boolean hasFocus) {


                if (hasFocus) {
                    etMobile.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etMobile, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });


        imgbtnFemale = (Button) findViewById(R.id.imgbtn_gen_female);
        imgbtnMale = (Button) findViewById(R.id.imgbtn_gen_male);

      /*  if (!clicked){

            Toast.makeText(Sponsored2Activity.this,"Select Age",Toast.LENGTH_LONG).show();

        }
*/

        imgbtnMale.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                UserInformation.gender = "male";
                clicked=true;
                imgbtnMale.setBackground(getResources().getDrawable(R.drawable.male_button_round_corner));
                imgbtnFemale.setBackground(getResources().getDrawable(R.drawable.white_rounded_edit_text));
                imgbtnFemale.setTextColor(getResources().getColor(R.color.gray));
                imgbtnMale.setTextColor(getResources().getColor(R.color.white));
              /*  imgbtnMale.setBackgroundColor(getResources().getColor(R.color.app_color));
                imgbtnFemale.setBackgroundColor(getResources().getColor(R.color.white));*/
            }
        });

        imgbtnFemale.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                UserInformation.gender = "female";
                clicked=true;
                imgbtnFemale.setBackground(getResources().getDrawable(R.drawable.female_button_round_corner));
                imgbtnMale.setBackground(getResources().getDrawable(R.drawable.white_rounded_edit_text));
                imgbtnMale.setTextColor(getResources().getColor(R.color.gray));

                imgbtnFemale.setTextColor(getResources().getColor(R.color.white));
               /* imgbtnFemale.setBackgroundColor(getResources().getColor(R.color.app_color));
                imgbtnMale.setBackgroundColor(getResources().getColor(R.color.white))*/;

            }
        });


        btnNext2 = (Button) findViewById(R.id.btn_next2);
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                UserInformation.Age = etAge.getText().toString();
                UserInformation.Name = etName.getText().toString();
                UserInformation.Id = etMail.getText().toString();
                UserInformation.mobile = etMobile.getText().toString();

//                String g = UserInformation.gender.toString();


                if (etAge.length() < 0 || etAge.getText().toString().equals("")) {
                    etAge.setBackground(getResources().getDrawable(R.drawable.red_rounded_edit_text));
                    etAge.setTextColor(getResources().getColor(R.color.red));
                } else if (etName.length() < 1 || etName.getText().toString().equals("")) {
                    etName.setBackground(getResources().getDrawable(R.drawable.red_rounded_edit_text));
                    etName.setTextColor(getResources().getColor(R.color.red));
                } else if (etMail.length() < 1 || etMail.getText().toString().equals("") || !etMail.getText().toString().trim().matches(emailPattern)) {
                    etMail.setBackground(getResources().getDrawable(R.drawable.red_rounded_edit_text));
                    etMail.setTextColor(getResources().getColor(R.color.red));
                } else if (etMobile.length() < 10 || etMobile.getText().toString().equals("")) {
                    etMobile.setBackground(getResources().getDrawable(R.drawable.red_rounded_edit_text));
                    etMobile.setTextColor(getResources().getColor(R.color.red));
                } else if(clicked=false) {
                    Toast.makeText(Sponsored2Activity.this,"Select Age",Toast.LENGTH_LONG).show();
                }
                else{

                    startActivity(new Intent(Sponsored2Activity.this, Sponsored3Activity.class));

                }

            }
        });
    }


    @Override
    public void onBackPressed() {

        startActivity(new Intent(Sponsored2Activity.this, CategoryActivity.class));
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
}
