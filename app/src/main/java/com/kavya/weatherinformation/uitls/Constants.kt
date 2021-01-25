package com.kavya.weatherinformation.uitls

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kavya.weatherinformation.ui.dashboard.WeatherData
import com.kavya.weatherinformation.uitls.Constants.Preferences.BOOK_MARK_CITY
import com.kavya.weatherinformation.uitls.Constants.Preferences.WEATHER_DATA

object Constants {
    const val GIF_IMG_URL = "https://media.giphy.com/media/KV1s4kSJHaY3m/giphy.gif"

    object Database {
        const val Weather_DB = "weather_db"
    }

    object Service {
        const val METRIC = "metric"
        const val API_KEY_VALUE = "fae7190d7e6433ec3a45285ffcf55c86"
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    }

    object Preferences {
        const val PREFERENCE_NAME ="weather_preferences"
        const val WEATHER_DATA ="weather_data_key"
        const val BOOK_MARK_CITY ="book_mark_city"
    }
}

fun setWeatherData(gson: Gson, preferences: SharedPreferences, data: WeatherData) {
    preferences.edit().putString(WEATHER_DATA, gson.toJson(data).toString()).apply()
}

fun setBookMarkLists(gson: Gson, preferences: SharedPreferences, list: ArrayList<WeatherData>){
    preferences.edit().putString(BOOK_MARK_CITY, gson.toJson(list)).apply()
}

fun getBookMarkCityList(gson: Gson,preferences: SharedPreferences):ArrayList<WeatherData>{
    val json = preferences.getString(BOOK_MARK_CITY,null) ?: return arrayListOf()
    val type = object : TypeToken<ArrayList<WeatherData>>(){}.type
    return gson.fromJson(json,type)
}