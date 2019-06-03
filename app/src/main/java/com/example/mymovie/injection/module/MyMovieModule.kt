package com.example.mymovie.injection.module

import com.example.mymovie.data.MoviesDataRepository
import com.example.mymovie.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyMovieModule {
    @Singleton
    @Provides
    fun provideMoviesRepository(moviesDataRepository: MoviesDataRepository): MoviesRepository = moviesDataRepository
}