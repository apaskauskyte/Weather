package com.paskauskyte.myweather.weather_api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/3.0/onecall")
    suspend fun getCityWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
//        @Query("appid") apiKey: String = "7b51caeff3b17dccc9c31c5e59ff2ef4",
    ): Response<CityWeatherResponse>
}