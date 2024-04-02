package com.example.weatherapp_android.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.weatherapp_android.BASE_URL
import com.example.weatherapp_android.KELVIN_TO_CELSIUS
import com.example.weatherapp_android.METER_PER_SECOND_TO_KILOMETER_PER_HOUR
import com.example.weatherapp_android.R
import com.example.weatherapp_android.databinding.FragmentHomeBinding
import com.example.weatherapp_android.viewmodel.HomeViewModel
import com.example.weatherapp_android.viewmodel.MyViewModelFactory
import java.text.DateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.roundToInt

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = MyViewModelFactory(requireActivity().application)
        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next7days.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_nextDaysWeatherFragment)
        }

        homeViewModel.weather.observe(viewLifecycleOwner){weather ->
            binding.textWeather.text = weather.weather[0].main
            binding.textDate.text = DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(Calendar.getInstance().time).toString()
            binding.textAddress.text = weather.name

            binding.textTemp.text = weather.main?.temp?.let {convertKelvinToCelsius(it)}


            binding.cloudy.text = weather.clouds?.all?.roundToInt()?.toString() + "%"
            binding.windy.text = weather.wind?.speed?.times(
                METER_PER_SECOND_TO_KILOMETER_PER_HOUR
            )?.roundToInt().toString() + " km/h"
            binding.humidity.text = weather.main?.humidity?.roundToInt()?.toString()

            val iconUrl = BASE_URL + "/img/w/" + weather.weather[0].icon + ".png"
            Glide.with(requireContext()).load(iconUrl).into(binding.imageIconWeather)
        }
    }

    private fun convertKelvinToCelsius(temp: Float): String {
        return (temp.minus(KELVIN_TO_CELSIUS)).roundToInt().toString() + "°C"
    }

}