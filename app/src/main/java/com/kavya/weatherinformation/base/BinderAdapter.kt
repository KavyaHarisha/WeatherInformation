package com.kavya.weatherinformation.base

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.kavya.weatherinformation.R

@BindingAdapter("app:setWeatherIcon")
fun setWeatherIcon(view: ImageView, weatherIcon:String?) {
    if (weatherIcon.isNullOrEmpty())
        return
    val newPath = weatherIcon.replace(weatherIcon, "a$weatherIcon")
    val imageId = view.context.resources.
    getIdentifier(newPath + "_svg", "drawable", view.context.packageName)
    view.setImageDrawable( ContextCompat.getDrawable(view.context,imageId))
}

@BindingAdapter("app:setCityName")
fun setHomeCityName(view: TextView, city:String) {
    view.text = view.resources.getString(R.string.home_city_name,city)
}

@BindingAdapter("app:setCityTemp")
fun setHomeCityTemp(view: TextView, temp:String) {
    view.text = view.resources.getString(R.string.home_city_temp,temp)
}