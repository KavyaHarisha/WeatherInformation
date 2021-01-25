package com.kavya.weatherinformation.ui.dashboard

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.kavya.weatherinformation.uitls.Constants
import com.kavya.weatherinformation.uitls.getBookMarkCityList
import com.kavya.weatherinformation.uitls.setBookMarkLists

class CityViewModel @ViewModelInject constructor(var sharedPreferences: SharedPreferences, val gson: Gson) :
        ViewModel() {

        fun getWeatherData():WeatherData{
              return gson.fromJson(sharedPreferences.getString(Constants.Preferences.WEATHER_DATA,""),WeatherData::class.java)
        }

        fun prepareBookMarkCityList(isBookMark:Boolean) {
                val list:ArrayList<WeatherData> = getBookMarkCityList(gson,sharedPreferences)
                val weather = getWeatherData()
                if (isBookMark) {
                        weather.isBookMark = isBookMark
                        list.add(weather)
                } else {
                        list.remove(weather)
                }
                setBookMarkLists(gson, sharedPreferences, list)
        }
}