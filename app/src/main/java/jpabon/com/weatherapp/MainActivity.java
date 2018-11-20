package jpabon.com.weatherapp;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import jpabon.com.weatherapp.entities.CityWeather;
import jpabon.com.weatherapp.viewmodels.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    //OpenWeatherMap cities id: HK=1819730; SG=1880252
    private static final int HK_ID = 1819730;
    private static final int SG_ID = 1880252;

    public static final String UNITS = "metric";
    public static final String API_ID = "23d55f60ea93aa49fef603b8a64f1048";

    public static final int DELAY_UPDATE_IN_MILLISECONDS = 60 * 1000;

    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        try {
            viewModel.init(getApplicationContext(),
                    Arrays.asList(new Integer[]{HK_ID, SG_ID}),
                    UNITS,
                    API_ID);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        viewModel.getCityWeather().observe(this, weather -> {
            if (weather != null) {
                UpdateCityFragments(weather);
            }
        });
        
        LoadHistoricFragment();

        StartUpdateTimer();
    }

    private void LoadHistoricFragment() {
        FragmentHistory fragment = new FragmentHistory();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.history_container, fragment)
                .commit();
    }

    private void UpdateCityFragments(List<CityWeather> weather) {
        FragmentManager fm = getSupportFragmentManager();

        for (CityWeather city_weather : weather) {
            int containerId;
            if (city_weather.getId() == (HK_ID)) {
                containerId = R.id.hk_container;
            }
            else {
                containerId = R.id.sg_container;
            }

            Bundle bundle = new Bundle();
            bundle.putParcelable("info", city_weather);

            FragmentCityWeather fragment = new FragmentCityWeather();
            fragment.setArguments(bundle);

            fm.beginTransaction()
                    .replace(containerId, fragment)
                    .commit();
        }
    }

    private void StartUpdateTimer() {
        long delay = DELAY_UPDATE_IN_MILLISECONDS;
        long periodToRepeat = DELAY_UPDATE_IN_MILLISECONDS;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    try {
                        viewModel.ReloadCityWeather(Arrays.asList(new Integer[]{HK_ID, SG_ID}),
                                MainActivity.UNITS,
                                MainActivity.API_ID);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                });
            }
        }, delay, periodToRepeat);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
