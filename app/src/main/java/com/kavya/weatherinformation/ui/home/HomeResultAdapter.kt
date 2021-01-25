package com.kavya.weatherinformation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.kavya.weatherinformation.base.BaseAdapter
import com.kavya.weatherinformation.databinding.HomeItemRowLayoutBinding
import com.kavya.weatherinformation.ui.dashboard.WeatherData

class HomeResultAdapter(private val callBack: (WeatherData) -> Unit) : BaseAdapter<WeatherData>(
    diffCallback
) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = HomeItemRowLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        mBinding.homeRootItem.setOnClickListener {
            mBinding.homeItemData?.let {
                callBack.invoke(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as HomeItemRowLayoutBinding).homeItemData = getItem(position)
        binding.executePendingBindings()
    }

}

val diffCallback = object : DiffUtil.ItemCallback<WeatherData>() {
    override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean =
        oldItem.cityName == newItem.cityName
}
