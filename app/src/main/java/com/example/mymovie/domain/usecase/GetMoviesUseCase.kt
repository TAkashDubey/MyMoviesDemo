package com.example.mymovie.domain.usecase

import com.example.mymovie.domain.BaseUseCase
import com.example.mymovie.domain.MoviesRepository
import com.example.mymovie.domain.entity.MovieEntity
import com.example.mymovie.ui.utils.Constants
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) :
        BaseUseCase<MovieEntity>() {
    override fun buildSingle(data: Map<String, Any?>): Single<MovieEntity> {
        val request = data[Constants.GET_MOVIES] as Int
        return moviesRepository.getMovies(request)
    }
}