package com.kavya.weatherinformation.uitls

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
object NetworkUtils : ConnectivityManager.NetworkCallback() {

    private val networkLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val networkSingleObserveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun getNetworkLiveData(context: Context): LiveData<Boolean> {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(this)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), this)
        }

        var isConnected = false

        connectivityManager.allNetworks.forEach { network ->
            val networkCapability = connectivityManager.getNetworkCapabilities(network)

            networkCapability?.let {
                if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    isConnected = true
                    return@forEach
                }
            }
        }

        networkSingleObserveEvent.postValue(isConnected)

        return networkSingleObserveEvent
    }

    override fun onAvailable(network: Network) {
        networkLiveData.postValue(true)
    }

    override fun onLost(network: Network) {
        networkLiveData.postValue(false)
    }
}
