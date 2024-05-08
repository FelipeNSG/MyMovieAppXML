package com.example.mymovieappxml.movies.details

import com.example.mymovieappxml.network.model.moviedata.Genre
import com.example.mymovieappxml.network.model.seriesdata.Genres

open class MovieAndSeriesDetails(
    type:String,
    //Movie
    id: Int,
    title: String,
    genre: List<Genre>,
    runtime: Int,
    tagline: String,
    overview: String,
    posterPath: String,
    releaseDate: String,
    voteAverage: Double,
    //Series
    genres:  List<Genres>,
    numberOfSeason: Int,
    firstAirDate: String,
    episodeRunTime: List<Int>
) {
    val _type = type
    val _id = id
    val _title = title
    val _genre = genre
    val _runtime = runtime
    val _tagline = tagline
    val _overview = overview
    val _posterPath = posterPath
    val _releaseDate = releaseDate
    val _voteAverage = voteAverage
    val _genres = genres
    val _numberOfSeason = numberOfSeason
    val _firstAirDate = firstAirDate
    val _episodeRunTime = episodeRunTime
}