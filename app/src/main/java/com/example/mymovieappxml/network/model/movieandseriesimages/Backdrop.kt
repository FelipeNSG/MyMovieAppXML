package com.example.mymovieappxml.network.model.movieandseriesimages

import com.google.gson.annotations.SerializedName

data class Backdrop(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double? = null,
    @SerializedName("file_path")
    val filePath: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("width")
    val width: Int? = null
)