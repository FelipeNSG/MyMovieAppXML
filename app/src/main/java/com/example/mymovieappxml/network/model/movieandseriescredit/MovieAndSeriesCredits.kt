package com.example.mymovieappxml.network.model.movieandseriescredit

import com.google.gson.annotations.SerializedName

data class MovieAndSeriesCredits(
    @SerializedName("cast")
    val cast: List<Cast?>?= null,
)