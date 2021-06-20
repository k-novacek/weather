package kevin.weather.api

import kevin.weather.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {

    @GET("weather")
    fun getCurrent(@Query("q") query: String, @Query("units") units : String = "imperial", @Query("appid") appid: String = BuildConfig.OPEN_WEATHER_API_KEY)

    @GET("onecall")
    fun getForecast(@Query("lat")lat:Double, @Query("lon") lon:Double, @Query("exclude") exclude: String = "minutely,hourly", @Query("units") units : String = "imperial", @Query("appid") appid: String = BuildConfig.OPEN_WEATHER_API_KEY)
}