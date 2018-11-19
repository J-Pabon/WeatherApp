package jpabon.com.weatherapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CityWeather implements Parcelable {
    @PrimaryKey
    private int id;
    private String name;

    private int weatherId;
    private String main;
    private String description;
    private String icon;

    private float humidity;
    private float pressure;
    private float temp_max;
    private float temp_min;
    private float temp;

    public CityWeather(int id, String name, int weatherId, String main, String description, String icon,
                       float humidity, float pressure, float temp_max, float temp_min, float temp)
    {
        this.id = id;
        this.name = name;
        this.weatherId = weatherId;
        this.main = main;
        this.description = description;
        this.icon = icon;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.temp = temp;
    }

    protected CityWeather(Parcel in) {
        id = in.readInt();
        name = in.readString();
        weatherId = in.readInt();
        main = in.readString();
        description = in.readString();
        icon = in.readString();
        humidity = in.readFloat();
        pressure = in.readFloat();
        temp_max = in.readFloat();
        temp_min = in.readFloat();
        temp = in.readFloat();
    }

    public static final Creator<CityWeather> CREATOR = new Creator<CityWeather>() {
        @Override
        public CityWeather createFromParcel(Parcel in) {
            return new CityWeather(in);
        }

        @Override
        public CityWeather[] newArray(int size) {
            return new CityWeather[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(weatherId);
        dest.writeString(main);
        dest.writeString(description);
        dest.writeString(icon);
        dest.writeFloat(humidity);
        dest.writeFloat(pressure);
        dest.writeFloat(temp_max);
        dest.writeFloat(temp_min);
        dest.writeFloat(temp);
    }
}
