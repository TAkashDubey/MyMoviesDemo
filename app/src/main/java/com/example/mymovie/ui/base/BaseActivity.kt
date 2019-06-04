package com.example.mymovie.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    /**
     * Layout Resource for content view
     */
    abstract val contentView: Int
        @LayoutRes get

    /**
     * Initialize ViewModel and Observers
     */
    abstract fun viewModelSetup()

    /**
     * Setup initial UI
     */
    abstract fun viewSetup()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        viewModelSetup()
        viewSetup()
    }
}