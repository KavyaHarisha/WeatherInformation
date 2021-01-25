package com.kavya.weatherinformation.data.network

import javax.inject.Inject

class SearchCurrentWeatherDataSource @Inject constructor(private val weatherService: WeatherService){
    suspend fun getCurrentSearchWeather(query:String,units:String,appId:String) =
        weatherService.getCurrentWeather(query,units,appId)
}