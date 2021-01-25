package com.kavya.weatherinformation.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kavya.weatherinformation.data.model.*

object DataConverter {

    var gson = Gson()

    @TypeConverter
    @JvmStatic
    fun cloudToString(cloud: Clouds): String {
        return gson.toJson(cloud)
    }

    @TypeConverter
    @JvmStatic
    fun stringToCloud(data: String): Clouds {
        val cloud = object : TypeToken<Clouds>() {
        }.type
        return gson.fromJson(data, cloud)
    }

    @TypeConverter
    @JvmStatic
    fun mainToString(mainItem: Main): String {
        return gson.toJson(mainItem)
    }

    @TypeConverter
    @JvmStatic
    fun stringToMain(data: String): Main {
        val main = object : TypeToken<Main>() {
        }.type
        return gson.fromJson(data, main)
    }

    @TypeConverter
    @JvmStatic
    fun sysToString(sysItem: Sys): String {
        return gson.toJson(sysItem)
    }

    @TypeConverter
    @JvmStatic
    fun stringToSys(data: String): Sys {
        val sys = object : TypeToken<Sys>() {
        }.type
        return gson.fromJson(data, sys)
    }

    @TypeConverter
    @JvmStatic
    fun windString(windItem: Wind): String {
        return gson.toJson(windItem)
    }

    @TypeConverter
    @JvmStatic
    fun stringToWind(data: String): Wind {
        val wind = object : TypeToken<Wind>() {
        }.type
        return gson.fromJson(data, wind)
    }

    @TypeConverter
    @JvmStatic
    fun coordString(coordItem: Coord): String {
        return gson.toJson(coordItem)
    }

    @TypeConverter
    @JvmStatic
    fun stringToCoord(data: String): Coord {
        val coord = object : TypeToken<Coord>() {
        }.type
        return gson.fromJson(data, coord)
    }

    @TypeConverter
    @JvmStatic
    fun weatherToString(weatherItems: List<Weather>): String {
        return gson.toJson(weatherItems)
    }

    @TypeConverter
    @JvmStatic
    fun stringToWeatherItem(data: String): List<Weather> {
        val weatherItems = object : TypeToken<List<Weather>>() {
        }.type
        return gson.fromJson(data, weatherItems)
    }
}
