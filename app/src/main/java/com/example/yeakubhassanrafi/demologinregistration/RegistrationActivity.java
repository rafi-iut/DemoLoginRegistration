/*
* RegistrationActivity
*
* Version 1
*
* 29-sep-2015
*
* Copyright Yeakub Hassan Rafi
*/

package com.example.yeakubhassanrafi.demologinregistration;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class RegistrationActivity extends Activity {

    EditText g_email_address;
    EditText g_password;
    EditText g_age;

    RadioGroup g_sex;
    RadioButton g_male;
    RadioButton g_female;

    Button g_sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initialization();
    }
    public void onClickSignUp(View v)
    {
        SQLiteDatabase l_database = openOrCreateDatabase("kolorob",MODE_PRIVATE,null);
        l_database.execSQL("CREATE TABLE IF NOT EXISTS UserInformation (email VARCHAR, password VARCHAR, age VARCHAR, sex VARCHAR);");

        int l_radio_group_mode=g_sex.getCheckedRadioButtonId();
        String l_sex="";

        if(l_radio_group_mode==g_male.getId())
        {
            l_sex = "male";
        }
        if(l_radio_group_mode==g_female.getId())
        {
            l_sex = "female";
        }
        if(g_email_address.getText().toString().equals("") || g_password.getText().toString().equals("") || g_age.getText().toString().equals("") )
        {
            Toast.makeText(getApplicationContext(), "Wrong Input", Toast.LENGTH_LONG).show();
        }
        else {
            l_database.execSQL(String.format("INSERT INTO UserInformation VALUES('%s','%s','%s','%s');", g_email_address.getText().toString(),
                    g_password.getText().toString(), g_age.getText().toString(), l_sex));

            Toast.makeText(getApplicationContext(), "Registration Successful !!", Toast.LENGTH_LONG).show();

            finish();
        }

        l_database.close();

    }
    public void initialization()
    {
        g_email_address = (EditText) findViewById(R.id.editText);
        g_password = (EditText) findViewById(R.id.editText2);
        g_age = (EditText) findViewById(R.id.editText3);

        g_sex = (RadioGroup) findViewById(R.id.radioGroup);
        g_male = (RadioButton) findViewById(R.id.radioButton);
        g_female = (RadioButton) findViewById(R.id.radioButton2);

        g_sign_up = (Button) findViewById(R.id.button2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
