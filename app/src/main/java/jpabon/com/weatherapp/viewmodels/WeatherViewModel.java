package jpabon.com.weatherapp.viewmodels;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import jpabon.com.weatherapp.entities.CityWeather;
import jpabon.com.weatherapp.repository.WeatherRepository;

public class WeatherViewModel extends ViewModel {
    LiveData<List<CityWeather>> cityWeather;
    WeatherRepository weatherRepository;

    public void init(Context context, List<Integer> cities)  throws IllegalAccessException, InstantiationException, UnsupportedEncodingException {
        if (weatherRepository == null) {
            weatherRepository = new WeatherRepository(context);
        }

        cityWeather = weatherRepository.getWeatherForCities(cities);
    }

    public LiveData<List<CityWeather>> getCityWeather() {
        return cityWeather;
    }
}
