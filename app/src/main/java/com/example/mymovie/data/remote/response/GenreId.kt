package com.example.mymovie.data.remote.response


import com.google.gson.annotations.SerializedName

data class GenreId(
        @SerializedName("id")
        var id: String? = "",
        @SerializedName("name")
        var name: String? = ""
)