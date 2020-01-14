package com.firdausam.mobile.myweather.data.models


import com.google.gson.annotations.SerializedName


data class Coord(

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)