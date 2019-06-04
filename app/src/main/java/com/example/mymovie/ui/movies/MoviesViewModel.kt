package com.example.mymovie.ui.movies

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.mymovie.domain.entity.MovieEntity
import com.example.mymovie.domain.usecase.GetMoviesUseCase
import com.example.mymovie.ui.base.BaseObservableViewModel
import com.example.mymovie.ui.utils.Constants
import com.example.mymovie.ui.utils.customSubscribe
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val app: Application, private val moviesUseCase: GetMoviesUseCase) : BaseObservableViewModel(app) {

    val upComingMovieLiveData = MutableLiveData<MutableList<MovieEntity.MovieDataEntity>>()

    val nowShowingMovieLiveData = MutableLiveData<MutableList<MovieEntity.MovieDataEntity>>()

    fun getMovies(offset: Int) {
        val data = mapOf(Constants.GET_MOVIES to offset)
        moviesUseCase.execute(data).customSubscribe({
            upComingMovieLiveData.value = it.upcoming
            nowShowingMovieLiveData.value = it.showing
        }, {
            _errorLiveData.value = it
        }).collect()
    }
}