package com.paskauskyte.myweather.city

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityWeather(
    var city: String,
    var country: String?,
    var temperature: Double?,
    var weatherIcon: String,
    var description: String,
): Parcelable