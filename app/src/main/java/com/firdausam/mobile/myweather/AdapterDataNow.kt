package com.firdausam.mobile.myweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firdausam.mobile.myweather.data.models.IWeatherItem
import com.firdausam.mobile.myweather.databinding.ItemWeatherNowBinding

class AdapterDataNow: RecyclerView.Adapter<AdapterDataNow.WeatherHolderNow>() {
    var data = ArrayList<IWeatherItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherHolderNow(
            ItemWeatherNowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: WeatherHolderNow, position: Int) {
        holder.onBind(data[position])
    }

    class WeatherHolderNow(
        val binding: ItemWeatherNowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: IWeatherItem) {
            binding.item = item
        }
    }
}