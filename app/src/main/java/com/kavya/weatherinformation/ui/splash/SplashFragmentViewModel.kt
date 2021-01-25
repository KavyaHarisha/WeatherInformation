package com.kavya.weatherinformation.ui.splash

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.kavya.weatherinformation.uitls.getBookMarkCityList

class SplashFragmentViewModel @ViewModelInject constructor(val preferences: SharedPreferences,val gson: Gson) : ViewModel() {
    var navigateHomeScreen = false

    fun getNavigationScreenValue(){
        navigateHomeScreen = getBookMarkCityList(gson,preferences).isEmpty()
    }
}
