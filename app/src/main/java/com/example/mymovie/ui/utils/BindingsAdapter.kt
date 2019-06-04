package com.example.mymovie.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mymovie.GlideApp

object BindingsAdapter {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, url: String?) {
        GlideApp.with(view)
                .load(url)
                .transform(CenterCrop(), RoundedCorners(40))
                .into(view)
    }
}