/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mymovie.injection.module

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.mymovie.MyMovieApp
import com.example.mymovie.R
import com.example.mymovie.data.network.NetworkAvailabilityInterceptor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(app: MyMovieApp): Application = app

    @Singleton
    @Provides
    fun provideConnectivityManager(app: Application): ConnectivityManager {
        return app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Singleton
    @Provides
    fun provideNetworkAvailabilityInterceptor(
            app: Application,
            connectivityManager: ConnectivityManager
    ) = NetworkAvailabilityInterceptor(
            connectivityManager,
            app.getString(R.string.error_no_network)
    )
}
