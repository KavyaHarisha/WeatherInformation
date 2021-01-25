package com.kavya.weatherinformation.ui.dashboard

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class CityViewModel @ViewModelInject constructor(var sharedPreferences: SharedPreferences) :
        ViewModel() {
}