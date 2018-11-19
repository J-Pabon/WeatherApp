package jpabon.com.weatherapp.async;

import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import jpabon.com.weatherapp.entities.CityWeather;
import jpabon.com.weatherapp.repository.WeatherRepository;

public class CustomAsyncTask extends AsyncTask<Object, Object, LiveData<List<CityWeather>>> {

    public interface AsyncResponse {
        void processFinish(List<CityWeather> output);
    }

    public AsyncResponse delegate = null;

    public CustomAsyncTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected LiveData<List<CityWeather>> doInBackground(Object... objects) {
        WeatherRepository repository = (WeatherRepository) objects[0];
        return repository.getDb().weatherDao().load_historic((Integer)objects[1]);
    }

    /*@Override
    protected void onPostExecute(List<CityWeather> cityWeathers) {
        delegate.processFinish(cityWeathers);
    }*/
}
