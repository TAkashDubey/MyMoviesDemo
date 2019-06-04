package com.example.mymovie.data.remote.response


import com.google.gson.annotations.SerializedName

data class Showing(
        @SerializedName("age_category")
        var ageCategory: String? = "",
        @SerializedName("description")
        var description: String? = "",
        @SerializedName("genre_ids")
        var genreIds: List<GenreId?>? = listOf(),
        @SerializedName("id")
        var id: String? = "",
        @SerializedName("poster_path")
        var posterPath: String? = "",
        @SerializedName("presale_flag")
        var presaleFlag: Int? = 0,
        @SerializedName("rate")
        var rate: Float? = 0.0F,
        @SerializedName("title")
        var title: String? = "",
        @SerializedName("release_date")
        var releaseDate: Int? = 0)