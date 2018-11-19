package jpabon.com.weatherapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import jpabon.com.weatherapp.daos.WeatherDao;
import jpabon.com.weatherapp.entities.CityWeather;

@Database(entities = CityWeather.class, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
