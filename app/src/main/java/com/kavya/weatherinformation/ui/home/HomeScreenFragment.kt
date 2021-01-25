package com.kavya.weatherinformation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kavya.weatherinformation.R
import com.kavya.weatherinformation.base.BaseFragment
import com.kavya.weatherinformation.databinding.FragmentHomeScreenBinding
import com.kavya.weatherinformation.ui.dashboard.WeatherData
import com.kavya.weatherinformation.uitls.getBookMarkCityList
import com.kavya.weatherinformation.uitls.setBookMarkLists
import com.kavya.weatherinformation.uitls.setWeatherData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenFragment : BaseFragment<HomeScreenViewModel, FragmentHomeScreenBinding>(R.layout.fragment_home_screen) {

    override val mViewModel: HomeScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookMarkList = setUpHomeItemAdapter()
        setUpSwipeItemDelete(bookMarkList)
    }

    private fun setUpSwipeItemDelete(bookMarkList: ArrayList<WeatherData>) {
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.homeRecyclerView.adapter as HomeResultAdapter
                bookMarkList.removeAt(viewHolder.adapterPosition)
                setBookMarkLists(mViewModel.gson, mViewModel.sharedPreferences, bookMarkList)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                if(bookMarkList.isNullOrEmpty())navigate(R.id.action_homeScreenFragment3_to_searchCurrentWeatherFragment)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.homeRecyclerView)
    }

    private fun setUpHomeItemAdapter(): ArrayList<WeatherData> {
        binding.homeRecyclerView.adapter = HomeResultAdapter {
            setWeatherData(mViewModel.gson,mViewModel.sharedPreferences,it)
            findNavController().navigate(R.id.action_homeScreenFragment3_to_cityScreenFragment)
        }
        val bookMarkList = getBookMarkCityList(mViewModel.gson, mViewModel.sharedPreferences)
        (binding.homeRecyclerView.adapter as HomeResultAdapter).submitList(bookMarkList)
        return bookMarkList
    }
}
