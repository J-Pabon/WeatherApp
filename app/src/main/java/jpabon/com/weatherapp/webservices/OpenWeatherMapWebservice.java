package jpabon.com.weatherapp.webservices;

import jpabon.com.weatherapp.api.Details;
import jpabon.com.weatherapp.api.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapWebservice {
    @GET("/data/2.5/group")
    Call<Response<java.util.List<Details>>> getWeather(@Query(encoded = true, value = "id") String ids, @Query("units") String units, @Query("appid") String api_id);
}
