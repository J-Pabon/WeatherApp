package jpabon.com.weatherapp.viewmodels;

import android.content.Context;
import android.os.Handler;

import java.io.UnsupportedEncodingException;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import jpabon.com.weatherapp.entities.CityWeather;
import jpabon.com.weatherapp.repository.WeatherRepository;

public class WeatherViewModel extends ViewModel {


    LiveData<List<CityWeather>> cityWeather;
    MutableLiveData<List<CityWeather>> cityHistoric;

    WeatherRepository weatherRepository;

    public void init(Context context, List<Integer> cities, String units, String api_id) throws IllegalAccessException, UnsupportedEncodingException, InstantiationException {
        if (weatherRepository == null) {
            weatherRepository = new WeatherRepository(context);
        }

        ReloadCityWeather(cities, units, api_id);
        cityHistoric = new MutableLiveData<>();
    }

    public void ReloadCityWeather(List<Integer> cities, String units, String api_id) throws IllegalAccessException, UnsupportedEncodingException, InstantiationException {
        cityWeather = weatherRepository.getWeatherForCities(cities, units, api_id);
    }

    public void ReloadCityHistoric(int id) {
        Handler myHandler = new Handler();
        myHandler.post(() -> {
            List<CityWeather> info = weatherRepository.getHistoricForCity(id);

            cityHistoric.setValue(info);
        });

    }

    public LiveData<List<CityWeather>> getCityWeather() {
        return cityWeather;
    }

    public MutableLiveData<List<CityWeather>> getCityHistoric() {
        return cityHistoric;
    }
}
