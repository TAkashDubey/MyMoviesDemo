package com.example.mymovie.data.remote

import com.example.mymovie.data.remote.response.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesServices {
    @GET("search/")
    fun getMovies(@Query("offset") offset: Int): Single<MoviesResponse>
}