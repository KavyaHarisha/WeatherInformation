package com.kavya.weatherinformation.data.local

import androidx.room.*
import com.kavya.weatherinformation.data.model.CurrentWeatherResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM currentweatherresponse")
    fun getCurrentWeatherInfo(): Flow<CurrentWeatherResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeatherInfo(movies:CurrentWeatherResponse)

    @Query("DELETE FROM currentweatherresponse")
    suspend fun deleteCurrentWeatherInfo()

}