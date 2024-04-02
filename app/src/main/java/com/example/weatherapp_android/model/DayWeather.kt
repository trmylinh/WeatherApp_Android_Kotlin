package com.example.weatherapp_android.model

data class DayWeather(
    val tempMax: Int,
    val tempMin: Int,
    val icon: String,
    val dateTime: String
)