package com.example.mymovie.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract val contentView: Int
        @LayoutRes get

    abstract fun viewModelSetup()

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