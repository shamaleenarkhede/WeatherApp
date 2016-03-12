package com.example.admin.homework05;
/*
* Assignment - Homework Assignment-5
* File Name - AddCity.java
 * Team Members - Indraneel Bende
 *                Priyanka Mehta
 *                Shamalee Narkhede
* */

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

public class Addcity extends AppCompatActivity {

    HashMap<String,String> database=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText city = (EditText) findViewById(R.id.cityname);
                String city1 = city.getText().toString();


                EditText state = (EditText) findViewById(R.id.state);
                String state1 = state.getText().toString();

                if (!city1.isEmpty() && !state1.isEmpty()) {

                    /*if (city1.contains(" ")) {
                        city1 = city1.replace(" ", "_");
                    }*/
                    /*Intent i=getIntent();
                    i.putExtra("city",city1);
                    i.putExtra("state", state1);
                    setResult(RESULT_OK,i);
                    finish();*/

                    city1 = city1.concat(",");
                    city1 = city1.concat(state1);
                    new ValidCombo(Addcity.this).execute(city1);

                } else {
                    Toast.makeText(Addcity.this, "Either or both the text fields are empty", Toast.LENGTH_LONG).show();
                }


                AssetManager am = Addcity.this.getAssets();

              /*  try {

                    InputStream is = am.open("US.txt");
                    InputStreamReader is1=new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(is1);

                    String line=null;
                    Log.d("here","here");
                    while ((line = br.readLine()) != null) {
                        for(int i=0;i<line.length();i++){

                        }
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });



    }


    public class ValidCombo extends AsyncTask<String,Void,Boolean>
    {
        Addcity city;
        ValidCombo(Addcity city){
            this.city=city;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String split=params[0];
            String[] split1=split.split(",");
            String url1="http://api.sba.gov/geodata/all_data_for_city_of/"+split1[0]+"/"+split1[1]+".json";
            try {
                URL url = new URL(url1);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb=new StringBuilder();
                String line="";
                while((line= reader.readLine()) != null){
                    sb.append(line);
                }
                Log.d("demo",sb.toString());
                if(sb.length()>3)
                    return true;
                else return false;
            } catch (ProtocolException e) {
                e.printStackTrace();
                return false;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {

                e.printStackTrace();
                return false;
            }

        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(aBoolean==true)
            {
                //EditText city = (EditText) findViewById(R.id.cityname);
                EditText e=(EditText)findViewById(R.id.cityname);
                String city1 = e.getText().toString();
                //EditText state = (EditText) findViewById(R.id.state);
                EditText f=(EditText)findViewById(R.id.state);
                String state1 = f.getText().toString();
                if (city1.contains(" ")) {
                    city1 = city1.replace(" ", "_");
                }
                Intent i=getIntent();
                i.putExtra("city",city1);
                i.putExtra("state", state1);
                setResult(RESULT_OK,i);
                finish();
            }

            else
            {
                Toast.makeText(city,"You have entered an invalid combo",Toast.LENGTH_LONG).show();
            }
        }
    }

}
