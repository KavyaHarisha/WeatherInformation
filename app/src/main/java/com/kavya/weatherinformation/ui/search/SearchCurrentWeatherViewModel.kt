package com.kavya.weatherinformation.ui.search

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kavya.weatherinformation.data.model.CurrentWeatherResponse
import com.kavya.weatherinformation.data.network.SearchCurrentWeatherRepository
import com.kavya.weatherinformation.ui.dashboard.WeatherData
import com.kavya.weatherinformation.uitls.SingleLiveEvent
import com.kavya.weatherinformation.uitls.State
import com.kavya.weatherinformation.uitls.setWeatherData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class SearchCurrentWeatherViewModel @ViewModelInject constructor(
    private val dataRepository: SearchCurrentWeatherRepository,
    private val pref: SharedPreferences, private val gson: Gson) : ViewModel() {

    val singleObserveEvent: SingleLiveEvent<State<CurrentWeatherResponse>> = SingleLiveEvent()

    fun fetchQuestionsData(
        query: String,
        units: String,
        appId: String,
        dispatcher: CoroutineDispatcher
    ) {
        viewModelScope.launch(dispatcher) {
            dataRepository.getCurrentWeatherData(query, units, appId).collect {
                singleObserveEvent.postValue(it)
            }
        }
    }

    fun storeCurrentWeather(weatherData:CurrentWeatherResponse){
        val weatherRequiredData = WeatherData(weatherData.name,weatherData.main.temp.toString(),
            weatherData.weather[0].main,weatherData.main.humidity.toString(),weatherData.weather[0].icon)
        setWeatherData(gson,pref,weatherRequiredData)
    }
}