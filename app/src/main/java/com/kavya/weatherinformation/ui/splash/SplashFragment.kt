package com.kavya.weatherinformation.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.kavya.weatherinformation.R
import com.kavya.weatherinformation.base.BaseFragment
import com.kavya.weatherinformation.databinding.FragmentSplashBinding
import com.kavya.weatherinformation.uitls.Constants.GIF_IMG_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment :
    BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>(R.layout.fragment_splash) {
    override val mViewModel: SplashFragmentViewModel by viewModels()

    override fun init() {
        super.init()
        binding.splashViewModel = mViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load(GIF_IMG_URL)
            .into(binding.splashImage)
        mViewModel.getNavigationScreenValue()
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().graph.startDestination = R.id.homeScreenFragment3
            if (binding.splashViewModel!!.navigateHomeScreen)
                navigate(R.id.action_splashFragment_to_searchCurrentWeatherFragment)
            else
                navigate(R.id.action_splashFragment_to_homeScreenFragment3)
        }, 3000)
    }
}