package jpabon.com.weatherapp.daos;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import jpabon.com.weatherapp.entities.CityWeather;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface WeatherDao {
    @Insert(onConflict = REPLACE)
    void save(CityWeather cityweather);

    @Query("SELECT * FROM CityWeather WHERE id IN (:city_ids) LIMIT 2")
    LiveData<List<CityWeather>> load(List<Integer> city_ids);

    @Query("SELECT * FROM CityWeather WHERE id = :id LIMIT 5")
    LiveData<List<CityWeather>> load_historic(int id);
}
