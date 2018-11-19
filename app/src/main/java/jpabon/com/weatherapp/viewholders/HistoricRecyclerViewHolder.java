package jpabon.com.weatherapp.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jpabon.com.weatherapp.R;

public class HistoricRecyclerViewHolder extends RecyclerView.ViewHolder {
    private ImageView itemWeatherIcon = null;
    private TextView itemTemperature = null;
    private TextView itemHumidity = null;

    public HistoricRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        if (itemView != null) {
            setItemWeatherIcon(itemView.findViewById(R.id.item_weather_icon));
            setItemTemperature(itemView.findViewById(R.id.item_temperature));
            setItemHumidity(itemView.findViewById(R.id.item_humidity));
        }
    }

    public ImageView getItemWeatherIcon() {
        return itemWeatherIcon;
    }

    public void setItemWeatherIcon(ImageView itemWeatherIcon) {
        this.itemWeatherIcon = itemWeatherIcon;
    }

    public TextView getItemTemperature() {
        return itemTemperature;
    }

    public void setItemTemperature(TextView itemTemperature) {
        this.itemTemperature = itemTemperature;
    }

    public TextView getItemHumidity() {
        return itemHumidity;
    }

    public void setItemHumidity(TextView itemHumidity) {
        this.itemHumidity = itemHumidity;
    }
}
