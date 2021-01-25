package com.kavya.weatherinformation.data.network

import com.kavya.weatherinformation.data.model.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") q: String?,
        @Query("units") units: String?,
        @Query("appid") appId: String?
    ): Response<CurrentWeatherResponse>
}