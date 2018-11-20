package jpabon.com.weatherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jpabon.com.weatherapp.adapters.HistoricRecyclerViewAdapter;
import jpabon.com.weatherapp.entities.CityWeather;
import jpabon.com.weatherapp.viewmodels.WeatherViewModel;

public class FragmentHistory extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner cityFilter;
    RecyclerView historyRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        cityFilter = view.findViewById(R.id.city_filter);
        cityFilter.setOnItemSelectedListener(this);

        historyRecycler = view.findViewById(R.id.history_recycler);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WeatherViewModel viewModel = ViewModelProviders.of(getActivity()).get(WeatherViewModel.class);
        viewModel.getCityHistoric().observe(getActivity(), historics -> {
            if (historics != null) {
                UpdateUI(historics);
            }
        });

    }

    private void UpdateUI(List<CityWeather> historic) {
        if (historic == null || historic.size() == 0)
            return;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        historyRecycler.setLayoutManager(layoutManager);

        historyRecycler.setAdapter(new HistoricRecyclerViewAdapter(historic));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL);
        historyRecycler.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        WeatherViewModel viewmodel = ViewModelProviders.of(getActivity()).get(WeatherViewModel.class);

        if (viewmodel.getCityWeather().getValue() == null)
            return;

        String cityName = parent.getItemAtPosition(position).toString();

        int cityId = 0;
        for (CityWeather weather: viewmodel.getCityWeather().getValue()) {
            if (weather.getName().equals(cityName)) {
                cityId = weather.getId();
                break;
            }
        }

        if (cityId > 0) {
            viewmodel.ReloadCityHistoric(cityId);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
