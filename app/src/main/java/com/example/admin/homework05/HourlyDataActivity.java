package com.example.admin.homework05;
/*
* Assignment - Homework Assignment-5
* File Name - HourlyDataActivity.java
 * Team Members - Indraneel Bende
 *                Priyanka Mehta
 *                Shamalee Narkhede
* */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HourlyDataActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_data);

        this.setTitle(R.string.title_activity_hourlyDataActivity);

        String url=getIntent().getStringExtra("URL");
        String city = getIntent().getStringExtra("City");
        String State = getIntent().getStringExtra("State");
        ProgressDialog pb=new ProgressDialog(this);
        pb.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pb.show();
        new GetData(pb).execute(url);
    }

    public class GetData extends AsyncTask<String, Void,ArrayList<weather>> {

        ProgressDialog pb;
        GetData(ProgressDialog pb)
        {
            this.pb=pb;

        }
        @Override
        protected ArrayList<weather> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statuscode = con.getResponseCode();
                if (statuscode == HttpURLConnection.HTTP_OK) {
                    InputStream in=con.getInputStream();
                    return WeatherUtil.WeatherSAXParser.parseWeather(in);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final ArrayList<weather> s) {

            int max=0,min=9999;
            for(int i=0;i<s.size();i++)
            {
                if(Integer.parseInt(s.get(i).getTemperature())>max)
                {
                    max=Integer.parseInt(s.get(i).getTemperature());
                }
                if(Integer.parseInt(s.get(i).getTemperature())<min)
                {
                    min=Integer.parseInt(s.get(i).getTemperature());
                }
            }

            for(int i=0;i<s.size();i++)
            {
                s.get(i).setMaximumTemp(String.valueOf(max));
                s.get(i).setMinimumTemp(String.valueOf(min));
            }


            for(int i=0;i<s.size();i++)
            {
                Log.d("weather",s.get(i).toString());
            }

            super.onPostExecute(s);

            pb.dismiss();

            WeatherAdapter adapter=new WeatherAdapter(HourlyDataActivity.this.getBaseContext(),s);
            ListView list= (ListView) HourlyDataActivity.this.findViewById(R.id.lvweather);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent hourlydataintent = new Intent(HourlyDataActivity.this.getBaseContext(), DetailsActivity.class);
                    Bundle b=new Bundle();
                    b.putSerializable("list",s);
                    hourlydataintent.putExtras(b);
                    hourlydataintent.putExtra("weather", position);
                    String city = getIntent().getStringExtra("City");
                    String State = getIntent().getStringExtra("State");
                    hourlydataintent.putExtra("city",city);
                    hourlydataintent.putExtra("State", State);
                    Log.d("cityState",""+city+State);
                    startActivity(hourlydataintent);
                }
            });
            adapter.setNotifyOnChange(true);
        }

        }
    }

