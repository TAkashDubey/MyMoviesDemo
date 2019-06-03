package com.example.mymovie.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingFragment<B : ViewDataBinding> : BaseFragment() {

    var binding: B? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, contentView, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        binding?.unbind()
        super.onDestroyView()
    }
}