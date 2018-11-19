package jpabon.com.weatherapp.api;


import java.util.ArrayList;
import java.util.List;

import jpabon.com.weatherapp.entities.CityWeather;

public class Details {
    private int id;

    private String dt;

    private Clouds clouds;

    private Coordinates coord;

    private Wind wind;

    private String visibility;

    private Sys sys;

    private String name;

    private java.util.List<Weather> weather;

    private Main main;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getDt ()
    {
        return dt;
    }

    public void setDt (String dt)
    {
        this.dt = dt;
    }

    public Clouds getClouds ()
    {
        return clouds;
    }

    public void setClouds (Clouds clouds)
    {
        this.clouds = clouds;
    }

    public Coordinates getCoord ()
    {
        return coord;
    }

    public void setCoord (Coordinates coord)
    {
        this.coord = coord;
    }

    public Wind getWind ()
    {
        return wind;
    }

    public void setWind (Wind wind)
    {
        this.wind = wind;
    }

    public String getVisibility ()
    {
        return visibility;
    }

    public void setVisibility (String visibility)
    {
        this.visibility = visibility;
    }

    public Sys getSys ()
    {
        return sys;
    }

    public void setSys (Sys sys)
    {
        this.sys = sys;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public java.util.List<Weather> getWeather ()
    {
        return weather;
    }

    public void setWeather (java.util.List<Weather> weather)
    {
        this.weather = weather;
    }

    public Main getMain ()
    {
        return main;
    }

    public void setMain (Main main)
    {
        this.main = main;
    }

    public CityWeather ExtractCityWeather() {
        int _id = id;
        String _name = name;

        int _weatherId = weather.get(0).getId();
        String _main = weather.get(0).getMain();
        String _description = weather.get(0).getDescription();
        String _icon = weather.get(0).getIcon();

        float _humidity = main.getHumidity();
        float _pressure = main.getPressure();
        float _temp_max = main.getTemp_max();
        float _temp_min = main.getTemp_min();
        float _temp = main.getTemp();

        return new CityWeather(_id, _name, _weatherId, _main, _description, _icon,
                _humidity, _pressure, _temp_max, _temp_min, _temp);
    }
}
