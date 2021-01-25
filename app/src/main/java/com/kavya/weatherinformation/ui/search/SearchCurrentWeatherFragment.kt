package com.kavya.weatherinformation.ui.search

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kavya.weatherinformation.R
import com.kavya.weatherinformation.base.BaseFragment
import com.kavya.weatherinformation.databinding.FragmentSearchCurrentWeatherBinding
import com.kavya.weatherinformation.uitls.Constants.Service.API_KEY_VALUE
import com.kavya.weatherinformation.uitls.Constants.Service.METRIC
import com.kavya.weatherinformation.uitls.NetworkUtils
import com.kavya.weatherinformation.uitls.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchCurrentWeatherFragment :
    BaseFragment<SearchCurrentWeatherViewModel, FragmentSearchCurrentWeatherBinding>
        (R.layout.fragment_search_current_weather) {

    override val mViewModel: SearchCurrentWeatherViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchView()
        initUsers()
    }

    private fun initUsers() {
        mViewModel.singleObserveEvent.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> binding.searchLoader.visibility = View.VISIBLE
                is State.Success -> {
                    binding.searchLoader.visibility = View.GONE
                }
                is State.Error -> {
                    binding.searchLoader.visibility = View.GONE
                    Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initSearchView() {
        val searchEditText: EditText = binding.searchView.findViewById(R.id.search_src_text)
        activity?.applicationContext?.let { ContextCompat.getColor(it, R.color.textColor) }
            ?.let { searchEditText.setTextColor(it) }
        activity?.applicationContext?.let {
            ContextCompat.getColor(it, android.R.color.darker_gray)
        }
            ?.let { searchEditText.setHintTextColor(it) }
        binding.searchView.isActivated = true
        binding.searchView.setIconifiedByDefault(false)
        binding.searchView.isIconified = false

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onQueryTextSubmit(newText: String): Boolean {
                if (newText.isNotEmpty() && newText.count() > 2) {
                    handleNetworkChanges(newText)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun handleNetworkChanges(value: String) {
        context?.let {
            NetworkUtils.getNetworkLiveData(it).observe(viewLifecycleOwner, Observer { mViewModel.fetchQuestionsData(value,
                METRIC,API_KEY_VALUE,Dispatchers.IO) })
        }
    }
}