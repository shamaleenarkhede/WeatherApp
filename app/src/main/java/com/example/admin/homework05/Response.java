package com.example.admin.homework05;

/**
 * Created by PriyankaMehta on 3/8/16.
 */
import org.simpleframework.xml.*;

import java.util.ArrayList;
import java.util.List;

@Root(name = "response" , strict = false)
public class Response{
    private Hourly_Forecast hourly_forecast=new Hourly_Forecast();

    public Hourly_Forecast getHourly_forecast() {
        return hourly_forecast;
    }
}
@Root(name="hourly_forecast",strict = false)
class Hourly_Forecast {
    @ElementList
    private List<Forecast> forecast=new ArrayList<>();

    public List<Forecast> getForecast() {
        return forecast;
    }
}

@Root(name="forecast",strict = false)
class Forecast {
    @Element(name="wx")
    private String wx;

    public String getWx() {
        return wx;
    }
}

