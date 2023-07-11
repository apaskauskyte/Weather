package com.paskauskyte.myweather.weather_api

data class CityWeatherResponse(
    val current: Current
)

data class Current(
    val temp: Double,
    val weather: List<Weather>
)

data class Weather(
    val description: String,
    val icon: String
)