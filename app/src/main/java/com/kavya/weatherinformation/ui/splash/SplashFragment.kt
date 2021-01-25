package com.kavya.weatherinformation.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load(GIF_IMG_URL)
            .into(binding.splashImage)
    }
}