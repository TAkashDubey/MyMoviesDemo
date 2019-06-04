package com.example.mymovie.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mymovie.data.remote.response.Showing
import com.example.mymovie.data.remote.response.Upcoming
import com.example.mymovie.domain.usecase.GetMoviesUseCase
import com.example.mymovie.ui.base.BaseObservableViewModel
import com.example.mymovie.ui.utils.Constants
import com.example.mymovie.ui.utils.customSubscribe
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val app: Application, private val moviesUseCase: GetMoviesUseCase) : BaseObservableViewModel(app) {

    val upComingMovieLiveData = MutableLiveData<MutableList<Upcoming?>>()

    val nowShowingMovieLiveData = MutableLiveData<MutableList<Showing?>>()

    fun getMovies(offset: Int) {
        val data = mapOf(Constants.GET_MOVIES to offset)
        moviesUseCase.execute(data).customSubscribe({
            if (it.success == true) {
                Log.e("data >>", it.results.toString())
                Log.e("upcoming >>", it.results?.upcoming?.toString())
                Log.e("showing >>", it.results?.showing?.toString())
                upComingMovieLiveData.value = it.results?.upcoming
                nowShowingMovieLiveData.value = it.results?.showing
            }
        }, {
            _errorLiveData.value = it
        }).collect()
    }
}