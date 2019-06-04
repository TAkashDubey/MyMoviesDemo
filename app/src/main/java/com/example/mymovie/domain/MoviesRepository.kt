package com.example.mymovie.domain

import com.example.mymovie.domain.entity.MovieEntity
import io.reactivex.Single

interface MoviesRepository {
    fun getMovies(offset: Int): Single<MovieEntity>
}