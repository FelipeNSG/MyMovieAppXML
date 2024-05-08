package com.example.mymovieappxml.network.model.movieandseriesimages

import com.google.gson.annotations.SerializedName

data class MovieAndSeriesImages(
    @SerializedName("backdrops")
    val backdrops: List<Backdrop?>? = null,
    @SerializedName("posters")
    val posters: List<Poster?>? = null
)