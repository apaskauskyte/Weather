package com.paskauskyte.myweather.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CityViewModel : ViewModel() {

    private val _cityWeatherLiveData = MutableLiveData<CityWeather>()
    val cityWeatherLiveData: MutableLiveData<CityWeather>
        get() = _cityWeatherLiveData

    fun saveCityWeather(cityWeather: CityWeather) {
        _cityWeatherLiveData.value = cityWeather
    }
}