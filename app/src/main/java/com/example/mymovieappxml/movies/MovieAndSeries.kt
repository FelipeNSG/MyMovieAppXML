package com.example.mymovieappxml.movies

open class MovieAndSeries(
    id: Int,
    title: String,
    url: String,
    backdropPath: String,
    type: String
) {
    val _id = id
    val _title = title
    val _url = url
    val _backdropPath = backdropPath
    val _type = type
}