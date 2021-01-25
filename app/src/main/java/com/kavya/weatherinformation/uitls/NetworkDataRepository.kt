package com.kavya.weatherinformation.uitls

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response


abstract class NetworkDataRepository<RESULT, REQUEST> {

    @ExperimentalCoroutinesApi
    fun asFlow() = flow<State<RESULT>> {

        emit(State.loading())

        val apiResponse = fetchFromRemote()

        val remotePosts = apiResponse.body()

        if (apiResponse.isSuccessful && remotePosts != null) {
            saveRemoteData(remotePosts)
            emitAll(
                fetchFromLocal().map {
                    State.success(it)
                }
            )
        } else {
            emit(State.error(apiResponse.message()))
        }


    }.catch { e ->
        emit(State.error("Network error! Can't get latest weather data."))
        e.printStackTrace()
    }


    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)


    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>


    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}
