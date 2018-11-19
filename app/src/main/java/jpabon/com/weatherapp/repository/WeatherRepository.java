package jpabon.com.weatherapp.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import jpabon.com.weatherapp.api.Details;
import jpabon.com.weatherapp.async.CustomAsyncTask;
import jpabon.com.weatherapp.db.WeatherDatabase;
import jpabon.com.weatherapp.entities.CityWeather;
import jpabon.com.weatherapp.helpers.StringHelper;
import jpabon.com.weatherapp.network.RetrofitClientInstance;
import jpabon.com.weatherapp.webservices.OpenWeatherMapWebservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private static final String DB_NAME = "db_user";
    private WeatherDatabase db;
    private OpenWeatherMapWebservice webservice;
    private Context context;


    public WeatherRepository(Context context) {
        db = Room.databaseBuilder(context, WeatherDatabase.class, DB_NAME).build();
        webservice = RetrofitClientInstance.getRetrofitInstance().create(OpenWeatherMapWebservice.class);
        this.context = context;
    }

    public LiveData<java.util.List<CityWeather>> getWeatherForCities(java.util.List<Integer> cities) throws IllegalAccessException, InstantiationException, UnsupportedEncodingException {
        String encodedIds = StringHelper.EncodeArrayOfIntegers(cities);
        RefreshWeather(encodedIds);

        return getDb().weatherDao().load(cities);
    }

    public LiveData<List<CityWeather>> getHistoricForCity(int id) {
        CustomAsyncTask customAsync = new CustomAsyncTask(output -> {

        });

        LiveData<List<CityWeather>> data = null;

        try {
            data = customAsync.execute(this, id, null).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return data;
    }

    public void RefreshWeather(final String cities) throws IllegalAccessException, InstantiationException {
        Call<jpabon.com.weatherapp.api.Response<java.util.List<Details>>> call = webservice.getWeather(cities, "metric", "23d55f60ea93aa49fef603b8a64f1048");

        call.enqueue(new Callback<jpabon.com.weatherapp.api.Response<java.util.List<Details>>>() {
            @Override
            public void onResponse(Call<jpabon.com.weatherapp.api.Response<java.util.List<Details>>> call, Response<jpabon.com.weatherapp.api.Response<java.util.List<Details>>> response) {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        List<Details> details = response.body().getList();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            details.forEach(details1 -> {
                                getDb().weatherDao().save(details1.ExtractCityWeather());
                            });
                        }
                        else {
                            for (Details detail : details) {
                                getDb().weatherDao().save(detail.ExtractCityWeather());
                            }
                        }
                        return null;
                    }
                }.execute();
            }

            @Override
            public void onFailure(Call<jpabon.com.weatherapp.api.Response<java.util.List<Details>>> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public WeatherDatabase getDb() {
        return db;
    }
}