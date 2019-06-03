package com.example.mymovie.domain.usecase

import com.example.mymovie.data.remote.response.MoviesResponse
import com.example.mymovie.domain.BaseUseCase
import com.example.mymovie.domain.MoviesRepository
import com.example.mymovie.ui.utils.Constants
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) :
        BaseUseCase<MoviesResponse>() {
    override fun buildSingle(data: Map<String, Any?>): Single<MoviesResponse> {
        val request = data[Constants.GET_MOVIES] as Int
        return moviesRepository.getMovies(request)
    }
}