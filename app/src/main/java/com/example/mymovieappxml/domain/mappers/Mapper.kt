package com.example.mymovieappxml.domain.mappers

import com.example.mymovieappxml.components.SliderModel
import com.example.mymovieappxml.movies.Movie
import com.example.mymovieappxml.movies.MovieAndSeriesImagePoster
import com.example.mymovieappxml.movies.MovieCast
import com.example.mymovieappxml.movies.Series
import com.example.mymovieappxml.movies.details.MovieAndSeriesDetails
import com.example.mymovieappxml.movies.imageMovieUrl
import com.example.mymovieappxml.network.model.movieandseriescredit.Cast
import com.example.mymovieappxml.network.model.movieandseriesimages.Poster
import com.example.mymovieappxml.network.model.moviedata.MovieId
import com.example.mymovieappxml.network.model.seriesdata.SeriesId
import com.example.mymovieappxml.network.model.Result

fun Result.toMovie(): Movie {
    // mapper
    return Movie(
        id = this.id ?: Int.MIN_VALUE,
        title = this.title ?: "unknown",
        url = this.posterPath ?: "default_url",
        backdropPath = this.backdropPath ?: "default_url"
    )
}

fun Result.toSliderModel(): SliderModel {
    return SliderModel(
        id = this.id ?: Int.MIN_VALUE,
        image =  imageMovieUrl(this.backdropPath?: "default_url")
    )
}

fun Result.toSeries(): Series {
    // mapper
    return Series(
        id = this.id ?: Int.MIN_VALUE,
        title = this.title ?: "unknown",
        url = this.posterPath ?: "default_url",
        backdropPath = this.backdropPath ?: "default_url"
    )
}

fun MovieId.toMovieDetails(): MovieAndSeriesDetails {
    // mapper
    return MovieAndSeriesDetails(
        id = this.id ?: Int.MIN_VALUE,
        title = this.title ?: "unknown",
        genre = this.genres ?: emptyList(),
        runtime = this.runtime ?: Int.MIN_VALUE,
        tagline = this.tagline ?: "unknown",
        overview = this.overview ?: "unknown",
        posterPath = this.posterPath ?: "default_poster",
        releaseDate = this.releaseDate ?: "unknown",
        voteAverage = this.voteAverage ?: 0.0,
        //series
        type = "movie",
        genres = emptyList(),
        numberOfSeason = Int.MIN_VALUE,
        firstAirDate = "Unknown",
        episodeRunTime = emptyList()
    )
}

fun SeriesId.toSeriesDetails(): MovieAndSeriesDetails {
    return MovieAndSeriesDetails(
        id = this.id ?: Int.MIN_VALUE,
        title = this.name ?: "unknown",
        genres = this.genres ?: emptyList(),
        numberOfSeason = this.numberOfSeasons ?: Int.MIN_VALUE,
        tagline = this.tagline ?: "unknown",
        overview = this.overview ?: "unknown",
        posterPath = this.posterPath ?: "default_poster",
        firstAirDate = this.firstAirDate ?: "unknown",
        voteAverage = this.voteAverage ?: 0.0,
        episodeRunTime = this.episodeRunTime ?: emptyList(),
        //movie
        type = "series",
        genre = emptyList(),
        runtime = Int.MIN_VALUE,
        releaseDate = "Unknown",
    )
}

fun Cast.toMovieAndSeriesCast(): MovieCast {
    return MovieCast(
        originalName = this.originalName ?: "unknown",
        character = this.character ?: "unknown",
        profilePath = this.profilePath ?: "defaultProfilePath"
    )
}

fun Poster.toMovieAndSeriesImagePoster(): MovieAndSeriesImagePoster {
    return MovieAndSeriesImagePoster(
        filePath = this.filePath ?: "defaultProfilePath",
        iso6391 = this.iso6391 ?: "unknown"
    )
}