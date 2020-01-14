package com.firdausam.mobile.myweather.data.models


import com.google.gson.annotations.SerializedName


data class Weather(

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("cnt")
	val cnt: Int? = null,

	@field:SerializedName("cod")
	val cod: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("list")
	val list: List<ListItem?>? = null
)