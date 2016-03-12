/*
* Assignment - Homework Assignment-5
* File Name - DetailsActivity.java
 * Team Members - Indraneel Bende
 *                Priyanka Mehta
 *                Shamalee Narkhede
* */

package com.example.admin.homework05;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.setTitle(R.string.title_activity_detailsactivity);

        Bundle b= getIntent().getExtras();
        final ArrayList<weather> weatherlist= (ArrayList<weather>) b.getSerializable("list");
        Log.d("size", "" + weatherlist.size());
        Integer position=getIntent().getIntExtra("weather", 0);
        Log.d("position", "" + position);
        TextView tvcurrentlocation = (TextView)findViewById(R.id.idcurrentloc);
        tvcurrentlocation.setText(getIntent().getStringExtra("city") + ", ");
        TextView tvstate = (TextView)findViewById(R.id.idstate);
        tvstate.setText(getIntent().getStringExtra("State")+"("+weatherlist.get(position).getTime()+")");
        TextView tvtemp = (TextView)findViewById(R.id.idtemp);
        tvtemp.setText(weatherlist.get(position).getTemperature()+" F");
        TextView tvcondition = (TextView)findViewById(R.id.idcondition);
        tvcondition.setText(weatherlist.get(position).getClimateType());
        TextView tvmaxtemp = (TextView)findViewById(R.id.idmaxtempdisplay);
        tvmaxtemp.setText(weatherlist.get(position).getMaximumTemp()+" Fahrenheit");
        TextView tvmintemp = (TextView)findViewById(R.id.idmintempdis);
        tvmintemp.setText(weatherlist.get(position).getMinimumTemp()+" Fahrenheit");
        ImageView ivtemp = (ImageView)findViewById(R.id.ivclouds);
         Picasso.with(mContext).load(weatherlist.get(position).getIconUrl()).into(ivtemp);
        TextView tvfeelslike = (TextView)findViewById(R.id.idfeelslike);
        tvfeelslike.setText(weatherlist.get(position).getFeelsLike()+" Fahrenheit");
        TextView tvhumidity = (TextView)findViewById(R.id.idhumidity);
        tvhumidity.setText(weatherlist.get(position).getHumidity()+" %");
        TextView tvdewpoint = (TextView)findViewById(R.id.iddewpoint);
        tvdewpoint.setText(weatherlist.get(position).getDewpoint()+" Fahrenheit");
        TextView tvpressure = (TextView)findViewById(R.id.idpressure);
        tvpressure.setText(weatherlist.get(position).getPressure()+" hPa");
        TextView tvclouds = (TextView)findViewById(R.id.idclouds);
        tvclouds.setText(weatherlist.get(position).getClouds());
        TextView tvwinds = (TextView)findViewById(R.id.idwinds);
        String wind = (weatherlist.get(position).getWindSpeed()+", "+weatherlist.get(position).getWindDirection());

        if (wind.charAt(wind.length()-1)=='W') {
            wind = wind.replace(wind.substring(wind.length() - 1), "West");
            tvwinds.setText(wind);
        }
        else if(wind.charAt(wind.length()-1)=='E') {
            wind = wind.replace(wind.substring(wind.length() - 1), "East");
            tvwinds.setText(wind);
        }
        else if(wind.charAt(wind.length()-1)=='S') {
            wind = wind.replace(wind.substring(wind.length() - 1), "South");
            tvwinds.setText(wind);
        }
        else{
            wind = wind.replace(wind.substring(wind.length() - 1), "North");
            tvwinds.setText(wind);
        }



        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = 0;
                TextView t1 = (TextView) findViewById(R.id.textView2);
                for (int i = 0; i < weatherlist.size(); i++) {
                    if (weatherlist.get(i).getTime().equals(t1.getText().toString())) {
                        if (i == weatherlist.size() - 1) {
                            id = 0;
                            break;
                        } else {
                            id = i + 1;
                            break;
                        }
                    }

                }


                TextView tvcurrentlocation = (TextView)findViewById(R.id.idcurrentloc);
                tvcurrentlocation.setText(getIntent().getStringExtra("city")+", ");
                TextView tvstate = (TextView)findViewById(R.id.idstate);
                tvstate.setText(getIntent().getStringExtra("State")+"("+weatherlist.get(id).getTime()+")");
                TextView tvtemp = (TextView)findViewById(R.id.idtemp);
                tvtemp.setText(weatherlist.get(id).getTemperature()+" F");
                TextView tvcondition = (TextView)findViewById(R.id.idcondition);
                tvcondition.setText(weatherlist.get(id).getClimateType());
                TextView tvmaxtemp = (TextView)findViewById(R.id.idmaxtempdisplay);
                tvmaxtemp.setText(weatherlist.get(id).getMaximumTemp()+" Fahrenheit");
                TextView tvmintemp = (TextView)findViewById(R.id.idmintempdis);
                tvmintemp.setText(weatherlist.get(id).getMinimumTemp()+" Fahrenheit");
                ImageView ivtemp = (ImageView)findViewById(R.id.ivclouds);
                Picasso.with(mContext).load(weatherlist.get(id).getIconUrl()).into(ivtemp);
                TextView tvfeelslike = (TextView)findViewById(R.id.idfeelslike);
                tvfeelslike.setText(weatherlist.get(id).getFeelsLike() + " Fahrenheit");
                TextView tvhumidity = (TextView)findViewById(R.id.idhumidity);
                tvhumidity.setText(weatherlist.get(id).getHumidity()+" %");
                TextView tvdewpoint = (TextView)findViewById(R.id.iddewpoint);
                tvdewpoint.setText(weatherlist.get(id).getDewpoint()+" Fahrenheit");
                TextView tvpressure = (TextView)findViewById(R.id.idpressure);
                tvpressure.setText(weatherlist.get(id).getPressure()+" hPa");
                TextView tvclouds = (TextView)findViewById(R.id.idclouds);
                tvclouds.setText(weatherlist.get(id).getClouds());
                TextView time=(TextView)findViewById(R.id.textView2);
                time.setText(weatherlist.get(id).getTime());
                TextView tvwinds = (TextView)findViewById(R.id.idwinds);
                String wind = (weatherlist.get(id).getWindSpeed()+", "+weatherlist.get(id).getWindDirection());

                if (wind.charAt(wind.length()-1)=='W') {
                    wind = wind.replace(wind.substring(wind.length() - 1), "West");
                    tvwinds.setText(wind);
                }
                else if(wind.charAt(wind.length()-1)=='E') {
                    wind = wind.replace(wind.substring(wind.length() - 1), "East");
                    tvwinds.setText(wind);
                }
                else if(wind.charAt(wind.length()-1)=='S') {
                    wind = wind.replace(wind.substring(wind.length() - 1), "South");
                    tvwinds.setText(wind);
                }
                else{
                    wind = wind.replace(wind.substring(wind.length() - 1), "North");
                    tvwinds.setText(wind);
                }







            }
        });

        findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = 0;
                TextView t1 = (TextView) findViewById(R.id.textView2);
                for (int i = 0; i < weatherlist.size(); i++) {
                    if (weatherlist.get(i).getTime().equals(t1.getText().toString())) {
                        i = i - 1;
                        if (i == -1) {
                            i = weatherlist.size() - 1;
                        }
                        id = i;
                        break;
                    }
                }


                TextView tvcurrentlocation = (TextView)findViewById(R.id.idcurrentloc);
                tvcurrentlocation.setText(getIntent().getStringExtra("city")+", ");
                TextView tvstate = (TextView)findViewById(R.id.idstate);
                tvstate.setText(getIntent().getStringExtra("State")+"("+weatherlist.get(id).getTime()+")");
                TextView tvtemp = (TextView)findViewById(R.id.idtemp);
                tvtemp.setText(weatherlist.get(id).getTemperature()+" F");
                TextView tvcondition = (TextView)findViewById(R.id.idcondition);
                tvcondition.setText(weatherlist.get(id).getClimateType());
                TextView tvmaxtemp = (TextView)findViewById(R.id.idmaxtempdisplay);
                tvmaxtemp.setText(weatherlist.get(id).getMaximumTemp()+" Fahrenheit");
                TextView tvmintemp = (TextView)findViewById(R.id.idmintempdis);
                tvmintemp.setText(weatherlist.get(id).getMinimumTemp()+" Fahrenheit");
                ImageView ivtemp = (ImageView)findViewById(R.id.ivclouds);
                Picasso.with(mContext).load(weatherlist.get(id).getIconUrl()).into(ivtemp);
                TextView tvfeelslike = (TextView)findViewById(R.id.idfeelslike);
                tvfeelslike.setText(weatherlist.get(id).getFeelsLike() + " Fahrenheit");
                TextView tvhumidity = (TextView)findViewById(R.id.idhumidity);
                tvhumidity.setText(weatherlist.get(id).getHumidity()+" %");
                TextView tvdewpoint = (TextView)findViewById(R.id.iddewpoint);
                tvdewpoint.setText(weatherlist.get(id).getDewpoint()+" Fahrenheit");
                TextView tvpressure = (TextView)findViewById(R.id.idpressure);
                tvpressure.setText(weatherlist.get(id).getPressure()+" hPa");
                TextView tvclouds = (TextView)findViewById(R.id.idclouds);
                tvclouds.setText(weatherlist.get(id).getClouds());
                TextView time=(TextView)findViewById(R.id.textView2);
                time.setText(weatherlist.get(id).getTime());
                TextView tvwinds = (TextView)findViewById(R.id.idwinds);
                String wind = (weatherlist.get(id).getWindSpeed()+", "+weatherlist.get(id).getWindDirection());

                if (wind.charAt(wind.length()-1)=='W') {
                    wind = wind.replace(wind.substring(wind.length() - 1), "West");
                    tvwinds.setText(wind);
                }
                else if(wind.charAt(wind.length()-1)=='E') {
                    wind = wind.replace(wind.substring(wind.length() - 1), "East");
                    tvwinds.setText(wind);
                }
                else if(wind.charAt(wind.length()-1)=='S') {
                    wind = wind.replace(wind.substring(wind.length() - 1), "South");
                    tvwinds.setText(wind);
                }
                else{
                    wind = wind.replace(wind.substring(wind.length() - 1), "North");
                    tvwinds.setText(wind);
                }










            }
        });
    }
}
