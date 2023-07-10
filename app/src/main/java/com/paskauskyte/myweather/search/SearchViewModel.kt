package com.paskauskyte.myweather.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.model.Place
import com.paskauskyte.myweather.city.CityWeather
import com.paskauskyte.myweather.weather_api.WeatherApiServiceClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class SearchViewModel : ViewModel() {

    private val _cityListStateFlow: MutableStateFlow<List<City>> =
        MutableStateFlow(emptyList())
    val cityListStateFlow = _cityListStateFlow.asStateFlow()

    private val _cityWeatherLiveData = MutableLiveData<CityWeather>()
    val cityWeatherLiveData: MutableLiveData<CityWeather>
        get() = _cityWeatherLiveData

    fun findPlace(text: String) {
        viewModelScope.launch {
            val places = findAutoCompletePredictions(text)
            val cities = places.map {
                City(name = it.getFullText(null).toString(), placeId = it.placeId)
            }
            _cityListStateFlow.value = cities
        }
    }

    fun loadWeather(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            val place = getPlace(city.placeId)
            val country = findCountry(place)
            val response = WeatherApiServiceClient.providesApiService().getCityWeather(
                place.latLng.latitude,
                place.latLng.longitude
            )
            cityWeatherLiveData.value = CityWeather(
                city.name,
                country,
                response.body()?.currentTemp ?: "",
                "",
                response.body()?.description ?: "")
        }
    }

    private fun findCountry(place: Place): String? {
        place.addressComponents.asList().forEach {addressComponent ->
            val country = addressComponent.types.find {it.equals("country")}
            if(country != null) {
                return country
            }
        }
        return null
    }

    private suspend fun getPlace(placeId: String): Place {
        return fetchPlace(placeId)
    }
}