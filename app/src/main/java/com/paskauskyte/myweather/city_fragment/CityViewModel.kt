package com.paskauskyte.myweather.city_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paskauskyte.myweather.repository.CityWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CityViewModel : ViewModel() {

    private val _cityWeatherStateFlow: MutableStateFlow<CityWeather> =
        MutableStateFlow(CityWeather("", "", "", "", ""))

    val cityWeatherStateFlow = _cityWeatherStateFlow.asStateFlow()

    fun fetchCityWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            _cityWeatherStateFlow.value = CityWeather("", "", "", "", "")
        }
    }
}