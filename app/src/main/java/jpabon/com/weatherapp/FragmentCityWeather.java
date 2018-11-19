package jpabon.com.weatherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import jpabon.com.weatherapp.entities.CityWeather;

public class FragmentCityWeather extends Fragment {
    ImageView weatherIcon;
    TextView cityName;
    TextView temperature;
    TextView humidity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_weather, container, false);

        weatherIcon = (ImageView) view.findViewById(R.id.weather_icon);
        cityName = (TextView) view.findViewById(R.id.city_name);
        temperature = (TextView) view.findViewById(R.id.temperature);
        humidity = (TextView) view.findViewById(R.id.humidity);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            CityWeather weather = bundle.getParcelable("info");
            UpdateUI(weather);
        }
    }

    private void UpdateUI(CityWeather weather) {
        String iconUri = String.format("https://openweathermap.org/img/w/%s.png", weather.getIcon());
        Picasso.get()
                .load(iconUri)
                .resize(80,80)
                .centerInside()
                .into(weatherIcon);

        cityName.setText(weather.getName());

        temperature.setText(String.valueOf(weather.getTemp()));
        humidity.setText(String.valueOf(weather.getHumidity()));
    }
}
