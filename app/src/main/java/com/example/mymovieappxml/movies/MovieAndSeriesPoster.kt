package com.example.mymovieappxml.movies

fun imageMovieUrl(url: String, width: String = "w780"): String{

    return "https://image.tmdb.org/t/p/$width$url"
}