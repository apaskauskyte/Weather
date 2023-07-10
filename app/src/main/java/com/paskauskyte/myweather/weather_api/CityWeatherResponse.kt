package com.paskauskyte.myweather.weather_api

import com.google.gson.annotations.SerializedName

data class CityWeatherResponse (
    @field:SerializedName("current.temp")
    val currentTemp: String,

    @field:SerializedName("current.weather.description")
    val description: String,

//    @field:SerializedName("current.humidity")
//    val humidity: Int,
//
//    @field:SerializedName("current.uvi")
//    val uvIndex: Int,
//
//    @field:SerializedName("current.rain")
//    val rain: Int,
)
