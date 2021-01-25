package com.kavya.weatherinformation.data.network

import com.kavya.weatherinformation.data.local.CurrentWeatherDao
import com.kavya.weatherinformation.data.model.CurrentWeatherResponse
import com.kavya.weatherinformation.uitls.NetworkDataRepository
import com.kavya.weatherinformation.uitls.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class SearchCurrentWeatherRepository @Inject constructor(
    private val remoteDataSource: SearchCurrentWeatherDataSource,
    private val currentWeatherDao: CurrentWeatherDao
) {

    fun getCurrentWeatherData(
        query: String,
        units: String,
        appId: String
    ): Flow<State<CurrentWeatherResponse>> {
        return object : NetworkDataRepository<CurrentWeatherResponse, CurrentWeatherResponse>() {

            override suspend fun saveRemoteData(response: CurrentWeatherResponse) {
                currentWeatherDao.deleteCurrentWeatherInfo()
                currentWeatherDao.insertCurrentWeatherInfo(response)
            }

            override fun fetchFromLocal(): Flow<CurrentWeatherResponse> =
                currentWeatherDao.getCurrentWeatherInfo()

            override suspend fun fetchFromRemote(): Response<CurrentWeatherResponse> =
                remoteDataSource.getCurrentSearchWeather(query, units, appId)
        }.asFlow()
    }

}