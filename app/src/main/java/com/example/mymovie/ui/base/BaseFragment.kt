package com.example.mymovie.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    abstract val contentView: Int
        @LayoutRes get

    abstract fun viewModelSetup()

    abstract fun viewSetup()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        contentView.takeIf { it != 0 }?.let {
            return inflater.inflate(contentView, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelSetup()
        viewSetup()
    }
}