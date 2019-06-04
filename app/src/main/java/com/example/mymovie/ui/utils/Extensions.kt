package com.example.mymovie.ui.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snackBar = Snackbar.make(this, message, length)
    snackBar.show()
}

fun View.gone() {
    this.visibility = View.GONE
}
