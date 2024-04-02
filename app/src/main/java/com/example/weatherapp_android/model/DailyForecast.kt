package com.example.weatherapp_android.model

class DailyForecast {
    var daily = ArrayList<DailyWeather>()
}

class Temp {
    val max = 0f
    val min = 0f
}

class DailyWeather {
    val temp: Temp? = null
    val weather = ArrayList<WeatherMain>()
}