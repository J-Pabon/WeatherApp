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
    LiveData<List<CityWeather>> cityHistoric;

    WeatherRepository weatherRepository;

    public void init(Context context, List<Integer> cities) throws IllegalAccessException, UnsupportedEncodingException, InstantiationException {
        if (weatherRepository == null) {
            weatherRepository = new WeatherRepository(context);
        }

        ReloadCityWeather(cities);
        ReloadCityHistoric(cities.get(0));
    }

    public void ReloadCityWeather(List<Integer> cities) throws IllegalAccessException, UnsupportedEncodingException, InstantiationException {
        cityWeather = weatherRepository.getWeatherForCities(cities);
    }

    public void ReloadCityHistoric(int id) {
        cityHistoric = weatherRepository.getHistoricForCity(id);
    }

    public LiveData<List<CityWeather>> getCityWeather() {
        return cityWeather;
    }

    public LiveData<List<CityWeather>> getCityHistoric() {
        return cityHistoric;
    }

    public void setCityHistoric(LiveData<List<CityWeather>> cityHistoric) {
        this.cityHistoric = cityHistoric;
    }
}
