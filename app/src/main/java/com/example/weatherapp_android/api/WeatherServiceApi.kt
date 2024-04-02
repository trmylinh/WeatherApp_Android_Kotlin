package com.example.weatherapp_android.api

import com.example.weatherapp_android.BASE_URL
import com.example.weatherapp_android.model.DailyForecast
import com.example.weatherapp_android.model.Weather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


// current weather
interface WeatherService {
    @GET("data/2.5/weather?")
    suspend fun getCurrentWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ) : Weather
}

object WeatherApi{
    val retrofitService : WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }
}

interface WeatherForecastService{
    @GET("data/2.5/forecast?")
    suspend fun getWeatherForecast(@Query("lat") lat: Double,
                                   @Query("lon") lon: Double,
                                   @Query("appid") appid: String) : ArrayList<Weather>
}

object WeatherForecastApi{
    val weatherForecastService : WeatherForecastService by lazy {
        retrofit.create(WeatherForecastService::class.java)
    }
}

interface DailyForecastService {
    @GET("data/2.5/onecall?")
    suspend fun getDailyForecast(@Query("lat") lat: Double,
                                 @Query("lon") lon: Double,
                                 @Query("appid") appid: String) : DailyForecast
}

object DailyForecastApi{
    val dailyForecastService : DailyForecastService by lazy {
        retrofit.create(DailyForecastService::class.java)
    }
}