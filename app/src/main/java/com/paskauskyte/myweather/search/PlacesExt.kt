package com.paskauskyte.myweather.search

import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceTypes
import com.google.android.libraries.places.ktx.api.net.awaitFetchPlace
import com.google.android.libraries.places.ktx.api.net.awaitFindAutocompletePredictions
import com.paskauskyte.myweather.PlacesClientProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
/**
 * Use this method to get a list of places that match the submitted [query]
 *
 * @param query any text to submit to Places API to find possible matches
 */
suspend fun findAutoCompletePredictions(query: String): List<AutocompletePrediction> =
    withContext(Dispatchers.IO) {
        val response = PlacesClientProvider.placesClient.awaitFindAutocompletePredictions {
            this.query = query
            typesFilter = mutableListOf(PlaceTypes.CITIES)
        }

        response.autocompletePredictions
    }

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun fetchPlace(placeId: String): Place {
    val response = PlacesClientProvider.placesClient.awaitFetchPlace(
        placeId, listOf(
            Place.Field.LAT_LNG, Place.Field.NAME, Place.Field.ID, Place.Field.ADDRESS_COMPONENTS
        )
    )
    return response.place
}