package com.example.admin.homework05;
/*
* Assignment - Homework Assignment-5
* File Name - WeatherAdapter.java
 * Team Members - Indraneel Bende
 *                Priyanka Mehta
 *                Shamalee Narkhede
* */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shamalee on 3/8/2016.
 */
public class WeatherAdapter extends ArrayAdapter<weather> {
    Context mContext;
    ArrayList<weather> objects;


    public WeatherAdapter(Context context, ArrayList<weather> objects) {
        super(context, R.layout.activity_hourly_data,objects);
        this.mContext=context;
        this.objects=objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.weather_overview,parent,false);
            holder=new ViewHolder();
            holder.time=(TextView)convertView.findViewById(R.id.idtime);
            holder.condition=(TextView)convertView.findViewById(R.id.idcondition);
            holder.temp=(TextView)convertView.findViewById(R.id.idtempertaure);
            holder.imageView=(ImageView)convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }

        holder=(ViewHolder)convertView.getTag();
        TextView time=holder.time;
        TextView condition=holder.condition;
        TextView temp=holder.temp;
        time.setText(objects.get(position).time);
        condition.setText(objects.get(position).climateType);
        temp.setText(objects.get(position).getTemperature());

        ImageView imageView=holder.imageView;
        if(objects.get(position).getIconUrl()!=null)
            Picasso.with(mContext).load(objects.get(position).getIconUrl()).into(imageView);
        return convertView;
    }
    static class ViewHolder{
        TextView time;
        TextView condition;
        TextView temp;
        ImageView imageView;
    }





}
