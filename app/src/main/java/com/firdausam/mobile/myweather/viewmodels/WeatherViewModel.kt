package com.firdausam.mobile.myweather.viewmodels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.firdausam.mobile.myweather.R
import com.firdausam.mobile.myweather.data.WeatherRepository
import com.firdausam.mobile.myweather.data.models.IWeatherItem
import com.firdausam.mobile.myweather.data.models.Weather

@BindingAdapter("setImageSource")
fun bindImageSource(imageView: ImageView, imgSrc: String?) {
    val context = imageView.context
    Glide.with(context)
        .load(imgSrc)
        .centerCrop()
        .placeholder(R.drawable.ic_cloud_primary)
        .into(imageView)
}

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _resultLiveDataNow = MutableLiveData<List<IWeatherItem>>()
    val resultLiveDataNow: LiveData<List<IWeatherItem>> get() = _resultLiveDataNow

    private val _resultLiveDataSoon = MutableLiveData<List<IWeatherItem>>()
    val resultLiveDataSoon: LiveData<List<IWeatherItem>> get() = _resultLiveDataSoon

    protected val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun getData(postalCode: Int) {
        repository.getDataWeather(postalCode, _resultLiveDataNow::setValue, _resultLiveDataSoon::setValue, _errorLiveData::setValue)
    }
}