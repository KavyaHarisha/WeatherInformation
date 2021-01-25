package com.kavya.weatherinformation.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kavya.weatherinformation.data.local.WeatherDatabase
import com.kavya.weatherinformation.data.network.SearchCurrentWeatherDataSource
import com.kavya.weatherinformation.data.network.WeatherService
import com.kavya.weatherinformation.uitls.Constants.Preferences.PREFERENCE_NAME
import com.kavya.weatherinformation.uitls.Constants.Service.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit =  Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideSharedPreference(application: Application): SharedPreferences =
        application.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = WeatherDatabase.getDatabase(application)

    @Singleton
    @Provides
    fun provideCurrentWeatherDao(database: WeatherDatabase) = database.currentWeatherDao()

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(userService: WeatherService) = SearchCurrentWeatherDataSource(userService)

}