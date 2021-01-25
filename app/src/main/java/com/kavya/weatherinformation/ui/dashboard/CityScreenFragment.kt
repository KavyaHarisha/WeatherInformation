package com.kavya.weatherinformation.ui.dashboard

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.kavya.weatherinformation.R
import com.kavya.weatherinformation.base.BaseFragment
import com.kavya.weatherinformation.databinding.FragmentCityScreenBinding
import com.kavya.weatherinformation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CityScreenFragment : BaseFragment<CityViewModel, FragmentCityScreenBinding>(
    R.layout.fragment_city_screen
) {

    override val mViewModel: CityViewModel by viewModels()

    var isBookMarkCity = false

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = mViewModel.getWeatherData()
        binding.containerCity.weatherData = data
        (activity as MainActivity).binding.toolbar.title = data.cityName
        isBookMarkCity= data.isBookMark
        setBookMarkImageResource(isBookMarkCity)
        bookMarkClick()
    }

    private fun bookMarkClick() {
        binding.cityBookMarkImg.setOnClickListener {
            isBookMarkCity = !isBookMarkCity
            setBookMarkImageResource(isBookMarkCity)
            mViewModel.prepareBookMarkCityList(isBookMarkCity)
        }
    }

    private fun setBookMarkImageResource(isMark:Boolean) {
        if (isMark) binding.cityBookMarkImg.background =
            ContextCompat.getDrawable(requireContext(), android.R.drawable.btn_star_big_on)
        else binding.cityBookMarkImg.background =
            ContextCompat.getDrawable(requireContext(), android.R.drawable.btn_star_big_off)
    }
}