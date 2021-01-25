package com.kavya.weatherinformation.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.kavya.weatherinformation.R
import com.kavya.weatherinformation.base.BaseFragment
import com.kavya.weatherinformation.databinding.FragmentCityScreenBinding
import com.kavya.weatherinformation.ui.MainActivity
import com.kavya.weatherinformation.uitls.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CityScreenFragment : BaseFragment<CityViewModel, FragmentCityScreenBinding>(
    R.layout.fragment_city_screen
) {

    override val mViewModel: CityViewModel by viewModels()

    @Inject
    lateinit var gson: Gson

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = gson.fromJson(mViewModel.sharedPreferences.getString(Constants.Preferences.WEATHER_DATA,""),WeatherData::class.java)
        binding.containerCity.weatherData = data
        (activity as MainActivity).binding.toolbar.title = data.cityName
    }
}