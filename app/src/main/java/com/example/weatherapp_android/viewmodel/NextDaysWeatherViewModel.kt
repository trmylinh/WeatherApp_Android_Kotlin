package com.example.weatherapp_android.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_android.APPID
import com.example.weatherapp_android.BASE_URL
import com.example.weatherapp_android.KELVIN_TO_CELSIUS
import com.example.weatherapp_android.LATITUDE
import com.example.weatherapp_android.LONGITUDE
import com.example.weatherapp_android.api.DailyForecastApi
import com.example.weatherapp_android.api.WeatherApi
import com.example.weatherapp_android.model.DayWeather
import com.example.weatherapp_android.model.Weather
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Calendar
import java.util.Date
import kotlin.math.roundToInt

class NextDaysWeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather

//    private var list: ArrayList<DayWeather> = ArrayList()

    private var _listDailyWeather = MutableLiveData<ArrayList<DayWeather>>()
        .apply { postValue(ArrayList()) }
    val listDailyWeather: LiveData<ArrayList<DayWeather>>
        get() = _listDailyWeather

    init {
        getDailyForecast()
//        getWeather()
    }

    private fun getDailyForecast() {
        viewModelScope.launch {
            try {
                val result = DailyForecastApi.dailyForecastService.getDailyForecast(
                    LATITUDE,
                    LONGITUDE,
                    APPID
                )
                for (i: Int in 1 until result.daily.size) {
                    val iconUrl = BASE_URL + "/img/w/" + result.daily[i].weather[0].icon + ".png"
                    result.daily[i]?.temp?.max?.minus(KELVIN_TO_CELSIUS)?.roundToInt()
                        ?.let { tempMax ->
                            result.daily[i]?.temp?.min?.minus(KELVIN_TO_CELSIUS)?.roundToInt()
                                ?.let { tempMin ->
                                    DayWeather(tempMax, tempMin, iconUrl, getDateTime(i))
                                }
                        }?.let { _listDailyWeather?.value?.add(it) }
                }
                _listDailyWeather.value = _listDailyWeather.value
                _response.value = "Success!!!"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    private fun getWeather() {
        viewModelScope.launch {
            try {
                val result =
                    WeatherApi.retrofitService.getCurrentWeatherData(LATITUDE, LONGITUDE, APPID)
                _weather.value = result
                _response.value = "Success!!!"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    private fun getDateTime(i: Int): String {
        var date = Date()
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DATE, i)
        date = calendar.time
        return DateFormat.getDateInstance(
            DateFormat.SHORT
        )
            .format(date).toString()
    }

}