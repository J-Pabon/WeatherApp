package jpabon.com.weatherapp.async;

import android.os.AsyncTask;

import java.util.List;

import jpabon.com.weatherapp.entities.CityWeather;
import jpabon.com.weatherapp.repository.WeatherRepository;

public class CustomAsyncTask extends AsyncTask<Object, Object, List<CityWeather>> {

    @Override
    protected List<CityWeather> doInBackground(Object... objects) {
        WeatherRepository repository = (WeatherRepository) objects[0];
        return repository.getDb().weatherDao().load_historic((Integer)objects[1]);
    }
}
