package com.firdausam.mobile.myweather.data

import android.util.Log
import com.firdausam.mobile.myweather.data.models.IWeatherItem
import com.firdausam.mobile.myweather.data.models.ListItem
import com.firdausam.mobile.myweather.data.models.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class WeatherRepository {
    private val api: Api = Api.Factory.create()!!

    fun getDataWeather(
        postalCode: Int,
        onGetNowData: (List<IWeatherItem>) -> Unit,
        onGetSoonData: (List<IWeatherItem>) -> Unit,
        onError: (String) -> Unit
    ) {
        val option = HashMap<String, String>()
        option["q"] = "Indonesia"
        option["units"] = "metric"
        option["zip"] = postalCode.toString()
        val call = api.getWeather(option)
        call.enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                Log.d("test", "test $response")
                if (response.isSuccessful) {
                    response.body()?.let { it ->
                        val dateFormat = "dd-MM-yyyy"
                        val dataNow = getDateFromMillis(Date().time, dateFormat)
                        val weatherNow = it.list?.filter { listItem ->
                            val dt = convertDateString(listItem?.dtTxt, "yyyy-MM-dd HH:mm:ss")
                            val dateString = getDateFromMillis(dt!!.time, dateFormat)
                            dateString == dataNow
                        }

                        val weatherSoon = it.list?.filter { listItem ->
                            !weatherNow!!.contains(listItem)
                        }
                        Log.d("test", "list new: $weatherNow")
                        onGetNowData(convertDataToIWeather(weatherNow as List<ListItem>))
                        onGetSoonData(convertDataToIWeather(weatherSoon as List<ListItem>))
                    }
                } else {
                    onError("Data tidak ditemukan")
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.e("test", "error: ", t)
            }
        })
    }

    fun getDateFromMillis(millis: Long, dateFormat: String) : String{
        val formater = SimpleDateFormat(dateFormat, Locale.getDefault())
        val calendar  = Calendar.getInstance()
        calendar.timeInMillis = millis
        return formater.format(calendar.time)
    }

    fun convertDateString(dateString: String?, dateFormat: String): Date? {
        return if (dateString != null) {
            val formater = SimpleDateFormat(dateFormat, Locale.getDefault())
            formater.parse(dateString)
        } else {
            Date()
        }
    }

    fun convertDataToIWeather(listItem: List<ListItem>): List<IWeatherItem> {
        val result = ArrayList<IWeatherItem>()
        for (item in listItem) {
            val builder = IWeatherItem.Builder()
            val dt = convertDateString(item.dtTxt, "yyyy-MM-dd HH:mm:ss")
            builder.setDate(getDateFromMillis(dt!!.time, "dd MMM yyyy"))
            builder.setTime(getDateFromMillis(dt.time, "HH:mm"))
            builder.setMinDegree("${item.main!!.tempMin}°C")
            builder.setMaxDegree("${item.main.tempMax}°C")
            builder.setDescription("${item.weather?.get(0)?.main}")
            builder.setSrc("http://openweathermap.org/img/wn/${item.weather?.get(0)?.icon}@2x.png")
            result.add(builder.build())
        }
        return result
    }
}