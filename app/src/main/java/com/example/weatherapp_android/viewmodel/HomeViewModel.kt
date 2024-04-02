package com.example.weatherapp_android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_android.APPID
import com.example.weatherapp_android.LATITUDE
import com.example.weatherapp_android.LONGITUDE
import com.example.weatherapp_android.api.WeatherApi
import com.example.weatherapp_android.model.Weather
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application){
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather

    init {
        getWeather()
    }
    private  fun getWeather(){
        viewModelScope.launch {
            try{
                val result = WeatherApi.retrofitService.getCurrentWeatherData(LATITUDE, LONGITUDE, APPID)
                _weather.value = result
            } catch (e: Exception){
                _response.value = "Failure ${e.message}"
            }
        }
    }

}