package com.paskauskyte.myweather

import android.app.Application
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializePlaces()
        println()
    }

    private fun initializePlaces() {
        MainScope().launch(Dispatchers.IO) {
            Places.initialize(applicationContext, getString(R.string.googleApiKey))
            val client = Places.createClient(this@WeatherApplication)
            PlacesClientProvider.placesClient = client
        }
    }
}