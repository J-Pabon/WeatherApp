package jpabon.com.weatherapp;

import android.os.Bundle;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import jpabon.com.weatherapp.entities.CityWeather;
import jpabon.com.weatherapp.viewmodels.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    //OpenWeatherMap cities id: HK=1819730; SG=1880252
    private static final int HK_ID = 1819730;
    private static final int SG_ID = 1880252;

    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        try {
            viewModel.init(getApplicationContext(), Arrays.asList(new Integer[]{HK_ID, SG_ID}));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        viewModel.getCityWeather().observe(this, weather -> {
            if (weather != null) {
                UpdateFragments(weather);
            }
        });
        
        SetHistoricFragment();

        SetUpdateTimer();
    }

    private void SetHistoricFragment() {
        FragmentHistory fragment = new FragmentHistory();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.history_container, fragment)
                .commit();
    }

    private void UpdateFragments(List<CityWeather> weather) {
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

    private void SetUpdateTimer() {
        long delay = 30000;
        long periodToRepeat = 20000;//60 * 2000; /* 1 mint */
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            viewModel.ReloadCityWeather(Arrays.asList(new Integer[]{HK_ID, SG_ID}));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 3000, 3000);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
