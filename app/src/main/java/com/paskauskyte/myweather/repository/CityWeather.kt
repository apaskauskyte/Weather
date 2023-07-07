package com.paskauskyte.myweather.repository

data class CityWeather(
    var city: String,
    var country: String,
    var temperature: String,
    var weatherIcon: String,
    var description: String,
) {
}