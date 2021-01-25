package com.kavya.weatherinformation.ui.home

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class HomeScreenViewModel @ViewModelInject constructor(var sharedPreferences: SharedPreferences,val gson: Gson) :
        ViewModel()