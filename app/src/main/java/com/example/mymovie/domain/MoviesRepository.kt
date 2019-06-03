package com.example.mymovie.domain

import com.example.mymovie.data.remote.response.MoviesResponse
import io.reactivex.Single

interface MoviesRepository {
    fun getMovies(offset: Int): Single<MoviesResponse>
}