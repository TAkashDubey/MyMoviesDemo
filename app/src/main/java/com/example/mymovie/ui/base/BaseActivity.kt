package com.example.mymovie.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    //This will return @LayoutRes for Activity
    abstract val contentView: Int
        @LayoutRes get

    // This will be called in onCreate() of an activity where one can bind ViewModel to View.
    abstract fun viewModelSetup()

    // This will be called after viewModelSetup() whene one can write View related stuffs.
    abstract fun viewSetup()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentView.takeIf { it != 0 }?.let {
            setContentView(it)
        }
        viewModelSetup()
        viewSetup()
    }
}