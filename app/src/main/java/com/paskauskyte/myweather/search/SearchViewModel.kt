package com.paskauskyte.myweather.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class SearchViewModel : ViewModel() {

    private val _cityListStateFlow: MutableStateFlow<List<City>> =
        MutableStateFlow(emptyList())
    val cityListStateFlow = _cityListStateFlow.asStateFlow()

    fun findPlace(text: String) {
        viewModelScope.launch {
            val places = findAutoCompletePredictions(text)
            val cities = places.map {
                City(name = it.getFullText(null).toString(), placeId = it.placeId)
            }
            _cityListStateFlow.value = cities
        }
    }

    private suspend fun getPlaceLatLng(placeId: String): LatLng {
        val place = fetchPlace(placeId)
        return place.latLng!!
    }
}