package jpabon.com.weatherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jpabon.com.weatherapp.R;
import jpabon.com.weatherapp.entities.CityWeather;
import jpabon.com.weatherapp.viewholders.HistoricRecyclerViewHolder;

public class HistoricRecyclerViewAdapter extends RecyclerView.Adapter<HistoricRecyclerViewHolder> {
    List<CityWeather> weatherHistoric;

    public HistoricRecyclerViewAdapter(List<CityWeather> weatherHistoric) {
        this.weatherHistoric = weatherHistoric;
    }

    @NonNull
    @Override
    public HistoricRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        HistoricRecyclerViewHolder viewHolder = new HistoricRecyclerViewHolder(inflater.inflate(R.layout.item_historic,
                parent,
                false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricRecyclerViewHolder holder, int position) {
        String iconUri = String.format("https://openweathermap.org/img/w/%s.png", weatherHistoric.get(position).getIcon());
        Picasso.get()
                .load(iconUri)
                .resize(80,80)
                .centerInside()
                .into(holder.getItemWeatherIcon());

        holder.getItemTemperature().setText(String.valueOf(weatherHistoric.get(position).getTemp()));
        holder.getItemHumidity().setText(String.valueOf(weatherHistoric.get(position).getHumidity()));
    }

    @Override
    public int getItemCount() {
        return weatherHistoric.size();
    }
}
