package com.example.mymovie.data

import com.example.mymovie.data.remote.response.MoviesResponse
import com.example.mymovie.data.remote.response.Showing
import com.example.mymovie.domain.entity.MovieEntity
import com.example.mymovie.ui.utils.Utils

fun MoviesResponse.map() =
        MovieEntity(transformShowingList(this.results?.showing), transformShowingList(this.results?.upcoming))

fun transformShowingList(showing: MutableList<Showing?>?) = showing?.map {
    MovieEntity.MovieDataEntity(
            it?.ageCategory,
            it?.description,
            it?.posterPath,
            it?.rate,
            it?.rate?.div(2) ?: 0.0F,
            it?.rate?.toString(),
            it?.title,
            it?.releaseDate?.toLong()?.let { Utils.getDate(it, "DD MMM yyyy") }
    )
}?.toMutableList()
