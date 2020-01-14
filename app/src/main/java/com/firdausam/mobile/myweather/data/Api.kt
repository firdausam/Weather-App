package com.firdausam.mobile.myweather.data

import com.firdausam.mobile.myweather.BuildConfig
import com.firdausam.mobile.myweather.data.models.Weather
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Api {
    @GET("/data/2.5/forecast")
    fun getWeather(@QueryMap options: Map<String, String>): Call<Weather>

    object Factory {
        var api: Api? = null
        fun create(): Api? {
            if (api == null) {
                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url()
                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("appid", BuildConfig.APP_ID)
                        .build()
                    val requestBuilder = original.newBuilder()
                        .url(url)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .client(httpClient.build())
                    .build()
                api =
                    retrofit.create(Api::class.java)
            }
            return api
        }
    }
}