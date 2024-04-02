package com.example.weatherapp_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp_android.databinding.LayoutWeatherdailyItemBinding
import com.example.weatherapp_android.model.DayWeather

class WeatherDailyAdapter(
    val list: List<DayWeather>
): RecyclerView.Adapter<WeatherDailyAdapter.ItemWeatherDailyViewHolder>() {
    private lateinit var binding: LayoutWeatherdailyItemBinding
    inner class ItemWeatherDailyViewHolder(binding: LayoutWeatherdailyItemBinding) : RecyclerView.ViewHolder (binding.root){
        fun bind(item: DayWeather){
            Glide.with(itemView.context).load(item.icon).into(binding.imageWeather)
            binding.textDate.text = item.dateTime
            binding.tempMin.text = item.tempMin.toString() + "°C"
            binding.tempMax.text = item.tempMax.toString() + "°C"
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemWeatherDailyViewHolder {
        val view = LayoutInflater.from(parent.context)
        binding = LayoutWeatherdailyItemBinding.inflate(view, parent, false)
        return ItemWeatherDailyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemWeatherDailyViewHolder, position: Int) {
        holder.bind(list[position])
    }
}