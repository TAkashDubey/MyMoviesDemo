package com.example.mymovie.ui.utils

import io.reactivex.Single
import io.reactivex.disposables.Disposable

fun <T> Single<T>.customSubscribe(success: (T) -> Unit, errorRes: (String?) -> Unit): Disposable {
    return this.subscribe({
        success(it)
    }, {
        errorRes(it.message)
    })
}