package com.kavya.weatherinformation.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kavya.weatherinformation.data.model.CurrentWeatherResponse
import com.kavya.weatherinformation.data.network.SearchCurrentWeatherRepository
import com.kavya.weatherinformation.uitls.SingleLiveEvent
import com.kavya.weatherinformation.uitls.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class SearchCurrentWeatherViewModel @ViewModelInject constructor(
    private val dataRepository: SearchCurrentWeatherRepository) : ViewModel() {

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
}