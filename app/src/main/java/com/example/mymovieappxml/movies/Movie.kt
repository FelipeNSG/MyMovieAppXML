package com.example.mymovieappxml.movies


data class Movie(
    val id: Int,
    val title: String,
    val url: String,
    val backdropPath: String,
    val type: String = "movie"
): MovieAndSeries(
    id,
    title,
    url,
    backdropPath,
    type
)