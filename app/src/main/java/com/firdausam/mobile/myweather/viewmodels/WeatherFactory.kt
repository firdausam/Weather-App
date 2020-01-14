package com.firdausam.mobile.myweather.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firdausam.mobile.myweather.data.WeatherRepository

class WeatherFactory: ViewModelProvider.NewInstanceFactory() {
    companion object {
        fun newInstance(): WeatherFactory {
            return WeatherFactory()
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            WeatherViewModel(WeatherRepository()) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}