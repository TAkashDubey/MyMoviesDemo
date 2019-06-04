package com.example.mymovie.data.remote.response


import com.google.gson.annotations.SerializedName

data class Results(
        @SerializedName("showing")
        var showing: MutableList<Showing?>? = mutableListOf(),
        @SerializedName("upcoming")
        var upcoming: MutableList<Showing?>? = mutableListOf()
)