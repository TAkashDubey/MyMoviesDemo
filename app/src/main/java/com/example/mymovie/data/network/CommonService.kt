package com.example.mymovie.data.network

import androidx.annotation.CallSuper
import com.example.mymovie.BuildConfig
import com.example.mymovie.MyMovieApp
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class CommonService<T> : BaseService<T>() {

    companion object {
        const val SERVICE_API_URL = "https://easy-mock.com/mock/5c19c6ff64b4573fc81a61f3/movieapp/"
    }

    @Inject
    lateinit var networkAvailabilityInterceptor: NetworkAvailabilityInterceptor

    @Inject
    lateinit var myMovieApp: MyMovieApp

    override val baseUrl = SERVICE_API_URL

    @CallSuper
    override fun handleOkHttpBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return if (BuildConfig.DEBUG) {
            super.handleOkHttpBuilder(builder)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(networkAvailabilityInterceptor)
        } else {
            super.handleOkHttpBuilder(builder)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(networkAvailabilityInterceptor)
        }
    }
}