package com.example.mymovie.data.remote.response


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
        @SerializedName("results")
        var results: Results? = Results(),
        @SerializedName("success")
        var success: Boolean? = false
)