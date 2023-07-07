package com.paskauskyte.myweather.search_fragment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel : ViewModel() {

    private val _cityListStateFlow: MutableStateFlow<List<City>> =
        MutableStateFlow(emptyList())
    val cityListStateFlow = _cityListStateFlow.asStateFlow()

    init {
        generateCities()
    }

    fun generateCities() {
        val cityList = mutableListOf<City>()
        for (i in 1..20) {
            val cityName = generateRandomCity()
            val countryName = generateRandomCountry()
            val city = City(cityName, countryName)
            cityList.add(city)
        }

        _cityListStateFlow.value = cityList
    }

    private fun generateRandomCity(): String {
        val rnds = (0..100).random()
        return "city$rnds"
    }

    private fun generateRandomCountry(): String {
        val rnds = (0..100).random()
        return "country$rnds"
    }
}