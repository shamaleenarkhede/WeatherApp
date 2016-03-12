package com.example.admin.homework05;
/*
* Assignment - Homework Assignment-5
* File Name - WeatherUtil.java
 * Team Members - Indraneel Bende
 *                Priyanka Mehta
 *                Shamalee Narkhede
* */

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ADMIN on 3/8/2016.
 */
public class WeatherUtil {



    static public class WeatherSAXParser extends DefaultHandler {
        private ArrayList<weather> weatherarraylist;
        weather weather;
        StringBuilder xmlInnertext;
        int flag;
        String time;

        public WeatherSAXParser() {
            super();
        }
        static public ArrayList<weather> parseWeather (InputStream in)throws
                IOException, SAXException {
            WeatherSAXParser parser = new WeatherSAXParser();
            Xml.parse(in, Xml.Encoding.UTF_8, parser);

            return parser.getWeatherarraylist();
        }

        @Override
        public void startDocument ()throws SAXException {
            super.startDocument();
            xmlInnertext = new StringBuilder();
            weatherarraylist = new ArrayList<weather>();

        }

        @Override
        public void endDocument ()throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement (String uri, String localName, String qName, Attributes
                attributes)throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("forecast")){
                weather = new weather();
            }

            if(localName.equals("temp"))
            {
                flag=1;
            }

            if(localName.equals("dewpoint"))
            {
                flag=2;
            }

            if(localName.equals("wspd")) {
                flag = 3;
            }

            if(localName.equals("feelslike")){
               Log.d("here","here");
                flag=4;
            }

            if(localName.equals("mslp")){
                flag=5;
            }



        }

        @Override
        public void endElement (String uri, String localName, String qName)throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("forecast")) {
                weatherarraylist.add(weather);
            }
            else if(localName.equals("english"))
            {
                if(flag==1) {
                    weather.setTemperature(xmlInnertext.toString().trim());  // .trim to remove white spaces
                }
                if(flag==2){
                    weather.setDewpoint(xmlInnertext.toString().trim());
                }
                if(flag==3){
                    flag=0;
                    weather.setWindSpeed(xmlInnertext.toString().trim());
                }
                if(flag==4){
                    flag=0;
                    weather.setFeelsLike(xmlInnertext.toString().trim());
                }
            }
            else if(localName.equals("condition"))
            {
               weather.setClouds(xmlInnertext.toString().trim());
            }
            else if(localName.equals("icon_url"))
            {
              weather.setIconUrl(xmlInnertext.toString().trim());
            }

            else if(localName.equals("humidity"))
            {
                weather.setHumidity(xmlInnertext.toString().trim());
            }


            else if(localName.equals("wx")) {
                weather.setClimateType(xmlInnertext.toString().trim());
            }

            else if(localName.equals("dir"))
            {
                weather.setWindDirection(xmlInnertext.toString().trim());
            }


            else if(localName.equals("metric")) {
                if (flag == 5) {
                    weather.setPressure(xmlInnertext.toString().trim());
                }
            }


            else if(localName.equals("hour"))
            {
                time=xmlInnertext.toString().trim();
            }
            else if(localName.equals("min"))
            {
                time=time.concat(":");
                time=time.concat(xmlInnertext.toString().trim());
            }

            else if(localName.equals("ampm")) {
                time=time.concat(" ");
                time=time.concat(xmlInnertext.toString().trim());
                weather.setTime(time);
                time=null;
            }




            xmlInnertext.setLength(0);
        }

        @Override
        public void characters ( char[] ch, int start, int length)throws SAXException {
            super.characters(ch, start, length);
            xmlInnertext.append(ch, start, length);
        }

        public ArrayList<weather> getWeatherarraylist() {
            return weatherarraylist;
        }
    }










}
