package com.example.weatherapp_android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp_android.adapter.WeatherDailyAdapter
import com.example.weatherapp_android.databinding.FragmentNextDaysWeatherBinding
import com.example.weatherapp_android.model.DailyWeather
import com.example.weatherapp_android.viewmodel.HomeViewModel
import com.example.weatherapp_android.viewmodel.MyViewModelFactory
import com.example.weatherapp_android.viewmodel.NextDaysWeatherViewModel


class NextDaysWeatherFragment : Fragment() {
    private lateinit var binding: FragmentNextDaysWeatherBinding
    private lateinit var nextDaysWeatherViewModel: NextDaysWeatherViewModel
    private lateinit var dailyWeatherAdapter: WeatherDailyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = MyViewModelFactory(requireActivity().application)
        nextDaysWeatherViewModel = ViewModelProvider(this, viewModelFactory)[NextDaysWeatherViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNextDaysWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.iconBack.setOnClickListener {
            findNavController().popBackStack()
        }

        nextDaysWeatherViewModel.listDailyWeather.observe(viewLifecycleOwner){
            dailyWeatherAdapter = WeatherDailyAdapter(it)
            binding.rvDailyWeather.adapter = dailyWeatherAdapter
            binding.rvDailyWeather.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.VERTICAL, false
                )
            }
        }
    }

}