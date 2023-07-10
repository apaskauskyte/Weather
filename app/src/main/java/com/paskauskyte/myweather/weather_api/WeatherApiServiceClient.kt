package com.paskauskyte.myweather.weather_api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiServiceClient {
    private const val BASE_URL = "https://api.openweathermap.org/"

    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("x-api-key", "7b51caeff3b17dccc9c31c5e59ff2ef4")
                .build()
            chain.proceed(newRequest)
        }
        .build()

    fun providesApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            )
            .build()
            .create(ApiService::class.java)
    }
}