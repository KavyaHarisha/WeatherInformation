package com.kavya.weatherinformation.base

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setWeatherIcon")
fun setWeatherIcon(view: ImageView, weatherIcon:String?) {
    if (weatherIcon.isNullOrEmpty())
        return
    val newPath = weatherIcon.replace(weatherIcon, "a$weatherIcon")
    val imageId = view.context.resources.
    getIdentifier(newPath + "_svg", "drawable", view.context.packageName)
    view.setImageDrawable( ContextCompat.getDrawable(view.context,imageId))
}