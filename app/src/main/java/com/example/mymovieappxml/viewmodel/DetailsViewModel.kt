package com.example.mymovieappxml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieappxml.data.repository.MoviesRepository
import com.example.mymovieappxml.movies.MovieAndSeriesImagePoster
import com.example.mymovieappxml.movies.MovieCast
import com.example.mymovieappxml.movies.details.MovieAndSeriesDetails
import kotlinx.coroutines.launch

enum class Type(val type: String) {
    MOVIE("movie"),
    SERIES("series")
}

class DetailsViewModel(): ViewModel() {
    private val repository = MoviesRepository
    private val _movieAndSeries = MutableLiveData<MovieDetailsState>()
    val movieAndSeriesDetails: LiveData<MovieDetailsState>
        get() = _movieAndSeries

fun titleTransform(title:String):String {
    val titleToSend:String
    return if (title.length > 33){
        titleToSend = title.take(30).also { it.plus("...") }
        titleToSend
    } else {
        titleToSend = title
        titleToSend
    }
}

     fun getMovieAndSeriesDetails(id:Int, type:String) {
        viewModelScope.launch {
            if (type == Type.MOVIE.type) {
                val movieDetails = repository.getMovieDetails(id)
                val movieCredits =
                    repository.getMovieCredits(id).filter { it.profilePath != "defaultProfilePath" }
                val movieAndSeriesImagePoster =
                    repository.getMovieImagesPoster(id).filter { it.iso6391 == "en" }
                        .shuffled()
                if (movieDetails != null) {
                    _movieAndSeries.value = MovieDetailsState.Success(
                        movieDetails,
                        movieCredits,
                        movieAndSeriesImagePoster
                    )
                }
            } else if (type == Type.SERIES.type) {
                val movieDetails = repository.getSeriesDetails(id)
                val movieCredits = repository.getSeriesCredits(id)
                    .filter { it.profilePath != "defaultProfilePath" }
                val movieAndSeriesImagePoster =
                    repository.getSeriesImagesPoster(id).filter { it.iso6391 == "en" }
                        .shuffled()
                if (movieDetails != null) {
                    _movieAndSeries.value = MovieDetailsState.Success(
                        movieDetails,
                        movieCredits,
                        movieAndSeriesImagePoster
                    )
                }
            }
        }
    }


    sealed class MovieDetailsState {
        object Loading : MovieDetailsState() {
        }
        data class Success(
            val details: MovieAndSeriesDetails,
            val credits: List<MovieCast>,
            val imagePoster: List<MovieAndSeriesImagePoster>
        ) : MovieDetailsState()
    }

}