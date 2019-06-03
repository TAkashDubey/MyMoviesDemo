package com.example.mymovie.data.util

import android.net.ConnectivityManager
import androidx.core.net.ConnectivityManagerCompat

fun hasNetwork(cm: ConnectivityManager) = cm.activeNetworkInfo?.isConnected ?: false
fun isNetworkMetered(cm: ConnectivityManager) = ConnectivityManagerCompat.isActiveNetworkMetered(cm)