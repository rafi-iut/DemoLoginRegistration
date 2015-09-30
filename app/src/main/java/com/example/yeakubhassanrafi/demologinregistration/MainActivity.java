/*
* MainActivity
*
* Version 1
*
* 29-sep-2015
*
* Copyright Yeakub Hassan Rafi
*/

package com.example.yeakubhassanrafi.demologinregistration;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {


    EditText g_email_address;
    EditText g_password;

    Button g_sign_in;

    TextView g_registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    public void initialization()
    {
        g_email_address = (EditText) findViewById(R.id.etEmail);
        g_password = (EditText) findViewById(R.id.etPass);

        g_sign_in = (Button) findViewById(R.id.button);

        g_registration = (TextView) findViewById(R.id.textView2);
    }

    public void onClickSignIn(View v)
    {
        SQLiteDatabase l_database = openOrCreateDatabase("kolorob",MODE_PRIVATE,null);
        l_database.execSQL("CREATE TABLE IF NOT EXISTS UserInformation (email VARCHAR, password VARCHAR, age VARCHAR, sex VARCHAR);");

        Cursor l_match_username_password_cursor = l_database.rawQuery(String.format("SELECT * FROM UserInformation WHERE email = '%s' AND password = '%s'",
                g_email_address.getText().toString(), g_password.getText().toString()), null);
        if(l_match_username_password_cursor.getCount()>0)
        {
            Toast.makeText(getApplicationContext(), "Log In Successful !!", Toast.LENGTH_LONG).show();
        }
        l_database.close();
    }
    public void onClickRegistration(View v)
    {
        Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
