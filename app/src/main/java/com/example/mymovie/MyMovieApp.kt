package com.example.mymovie

import com.example.mymovie.injection.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyMovieApp : DaggerApplication() {

    companion object {
        lateinit var instance: MyMovieApp
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}