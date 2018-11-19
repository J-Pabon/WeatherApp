package jpabon.com.weatherapp.api;

public class Sys {
    private float message;

    private int id;

    private String sunset;

    private String sunrise;

    private int type;

    private String country;

    public float getMessage ()
    {
        return message;
    }

    public void setMessage (float message)
    {
        this.message = message;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getSunset ()
    {
        return sunset;
    }

    public void setSunset (String sunset)
    {
        this.sunset = sunset;
    }

    public String getSunrise ()
    {
        return sunrise;
    }

    public void setSunrise (String sunrise)
    {
        this.sunrise = sunrise;
    }

    public int getType ()
    {
        return type;
    }

    public void setType (int type)
    {
        this.type = type;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }
}
