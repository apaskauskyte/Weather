package com.paskauskyte.myweather.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.model.PlaceTypes
import com.google.android.libraries.places.ktx.api.net.awaitFindAutocompletePredictions
import com.paskauskyte.myweather.PlacesClientProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {

    private val _cityListStateFlow: MutableStateFlow<List<City>> =
        MutableStateFlow(emptyList())
    val cityListStateFlow = _cityListStateFlow.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun findPlace(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = PlacesClientProvider.placesClient.awaitFindAutocompletePredictions {
                query = text
                typesFilter = mutableListOf(PlaceTypes.CITIES)
            }

            val cities = response.autocompletePredictions.map {
                City(name = it.getFullText(null).toString(), placeId = it.placeId)
            }
            withContext(Dispatchers.Main) {
                _cityListStateFlow.value = cities
            }
        }
    }
}