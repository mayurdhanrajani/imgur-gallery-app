package com.imgur.gallery.networking.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.*
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

/** The class will observe if the internet connection is available using LiveData **/
class CheckInternetConnection(private val context: Context) : LiveData<Boolean>() {

    /** This variable is used for checking the network and internet availability using ConnectivityManager **/
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    /** This variable is called to post the network value using callbacks **/
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(false)
        }
    }

    /** This function will be called if the network is Active **/
    override fun onActive() {
        super.onActive()
        checkInternet()
    }

    /** This function will be called if the network is Inactive **/
    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    /** This function is used for checking the internet availability **/
    private fun checkInternet() {
        val network = connectivityManager.activeNetwork
        if (network == null) postValue(false)

        val requestBuilder = NetworkRequest.Builder().apply {
            addCapability(NET_CAPABILITY_INTERNET)
            addCapability(NET_CAPABILITY_VALIDATED)
            addTransportType(TRANSPORT_CELLULAR)
            addTransportType(TRANSPORT_WIFI)
            addTransportType(TRANSPORT_ETHERNET)
        }.build()

        connectivityManager.registerNetworkCallback(requestBuilder, networkCallback)
    }

}