package com.example.admin.homework05;
/*
* Assignment - Homework Assignment-5
* File Name - weather.java
 * Team Members - Indraneel Bende
 *                Priyanka Mehta
 *                Shamalee Narkhede
* */

import java.io.Serializable;

/**
 * Created by ADMIN on 3/8/2016.
 */
public class weather implements Serializable{


    String time, temperature, dewpoint, clouds, iconUrl, windSpeed, windDirection;
    String climateType, humidity, feelsLike, maximumTemp, minimumTemp,pressure;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(String dewpoint) {
        this.dewpoint = dewpoint;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getClimateType() {
        return climateType;
    }

    public void setClimateType(String climateType) {
        this.climateType = climateType;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getMaximumTemp() {
        return maximumTemp;
    }

    public void setMaximumTemp(String maximumTemp) {
        this.maximumTemp = maximumTemp;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getMinimumTemp() {
        return minimumTemp;
    }

    public void setMinimumTemp(String minimumTemp) {
        this.minimumTemp = minimumTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }


    @Override
    public String toString() {
        return "weather{" +
                "time='" + time + '\'' +
                ", temperature='" + temperature + '\'' +
                ", dewpoint='" + dewpoint + '\'' +
                ", clouds='" + clouds + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", climateType='" + climateType + '\'' +
                ", humidity='" + humidity + '\'' +
                ", feelsLike='" + feelsLike + '\'' +
                ", maximumTemp='" + maximumTemp + '\'' +
                ", minimumTemp='" + minimumTemp + '\'' +
                ", pressure='" + pressure + '\'' +
                '}';
    }
}
