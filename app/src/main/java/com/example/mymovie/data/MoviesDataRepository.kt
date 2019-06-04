package com.example.mymovie.data

import com.example.mymovie.data.network.CommonService
import com.example.mymovie.data.remote.MoviesServices
import com.example.mymovie.data.remote.response.MoviesResponse
import com.example.mymovie.domain.MoviesRepository
import com.example.mymovie.domain.entity.MovieEntity
import io.reactivex.Single
import javax.inject.Inject

class MoviesDataRepository @Inject constructor() : CommonService<MoviesServices>(), MoviesRepository {

    override val baseClass = MoviesServices::class.java

    override fun getMovies(offset: Int): Single<MovieEntity> {
        return networkService.getMovies(offset).map {
            it.map()
        }
    }
}