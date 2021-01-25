package com.kavya.weatherinformation.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kavya.weatherinformation.data.model.CurrentWeatherResponse
import com.kavya.weatherinformation.uitls.Constants.Database.Weather_DB

@Database(entities = [CurrentWeatherResponse::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object {
        @Volatile
        private var instance: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, WeatherDatabase::class.java, Weather_DB)
                .fallbackToDestructiveMigration()
                .build()

    }

}