package com.example.mymovie.domain.entity

data class MovieEntity(
    var showing: MutableList<MovieDataEntity>? = mutableListOf(),
    var upcoming: MutableList<MovieDataEntity>? = mutableListOf()
) {
    data class MovieDataEntity(
        var ageCategory: String? = "",
        var description: String? = "",
        var posterPath: String? = "",
        var rate: Float? = 0.0F,
        var rateForRating : Float = 0.0F,
        var rateString: String? = "",
        var title: String? = "",
        var releaseDate: String? = ""
    )
}