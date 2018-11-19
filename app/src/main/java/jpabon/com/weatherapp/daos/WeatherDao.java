package jpabon.com.weatherapp.daos;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import jpabon.com.weatherapp.entities.CityWeather;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface WeatherDao {
    @Insert(onConflict = REPLACE)
    void save(CityWeather cityweather);

    @Query("SELECT * FROM CityWeather WHERE id IN (:city_ids)")
    LiveData<List<CityWeather>> load(List<Integer> city_ids);
}
