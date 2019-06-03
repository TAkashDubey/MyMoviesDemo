package com.example.mymovie.data.network

import android.net.ConnectivityManager
import com.example.mymovie.data.util.NoNetworkException
import com.example.mymovie.data.util.hasNetwork
import okhttp3.Interceptor
import okhttp3.Response

class NetworkAvailabilityInterceptor(
        private val cm: ConnectivityManager,
        private val noNetworkMessage: String
) :
        Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasNetwork(cm)) throw NoNetworkException(noNetworkMessage)
        return chain.proceed(chain.request())
    }
}