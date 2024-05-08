package com.example.mymovieappxml.data.repository

import com.example.mymovieappxml.components.SliderModel
import com.example.mymovieappxml.domain.mappers.toMovie
import com.example.mymovieappxml.domain.mappers.toMovieAndSeriesCast
import com.example.mymovieappxml.domain.mappers.toMovieAndSeriesImagePoster
import com.example.mymovieappxml.domain.mappers.toMovieDetails
import com.example.mymovieappxml.domain.mappers.toSeries
import com.example.mymovieappxml.domain.mappers.toSeriesDetails
import com.example.mymovieappxml.domain.mappers.toSliderModel
import com.example.mymovieappxml.movies.Movie
import com.example.mymovieappxml.movies.MovieAndSeriesImagePoster
import com.example.mymovieappxml.movies.MovieCast
import com.example.mymovieappxml.movies.Series
import com.example.mymovieappxml.movies.details.MovieAndSeriesDetails
import com.example.mymovieappxml.network.client.MovieClient
import retrofit2.HttpException

object MoviesRepository {
    suspend fun getPopularMovies(): List<Movie> {
        return try {
            val result = MovieClient.createMoviesService().getPopularMovies()
            result.results?.mapNotNull {
                it?.toMovie()
            } ?: emptyList()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            emptyList()
        }
    }

    suspend fun getTopRate(): List<Movie> {
        return try {
            val result = MovieClient.createMoviesService().getTopRate()
            result.results?.mapNotNull {
                it?.toMovie()
            } ?: emptyList()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            emptyList()
        }
    }

    suspend fun getPopularSeries(): List<Series> {
        return try {
            val result = MovieClient.createMoviesService().getPopularSeries()
            result.results?.mapNotNull {
                it?.toSeries()
            } ?: emptyList()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            emptyList()
        }
    }


    suspend fun getUpComingMoviesSliderModel():List<SliderModel>{
        return try {
            val result = MovieClient.createMoviesService().getUpcomingMovies()
            result.results?.mapNotNull { it?.toSliderModel() }
                ?: emptyList()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            emptyList()
        }
    }

    suspend fun getUpcomingMovies(): List<Movie> {
        return try {
            val result = MovieClient.createMoviesService().getUpcomingMovies()
            result.results?.mapNotNull { it?.toMovie() }
                ?: emptyList()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            emptyList()
        }
    }

    suspend fun getPlayNow(): List<Movie> {
        return try {
            val result = MovieClient.createMoviesService().getPlayNow()
            result.results?.mapNotNull {
                it?.toMovie()
            } ?: emptyList()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            emptyList()
        }
    }

    suspend fun getMovieDetails(id: Int): MovieAndSeriesDetails? {
        return try {
            val result = MovieClient.createMoviesService().getMovieDetails(id)
            result.toMovieDetails()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            return null
        }
    }

    suspend fun getSeriesDetails(id: Int): MovieAndSeriesDetails? {
        return try {
            val result = MovieClient.createMoviesService().getSeriesDetails(id)
            result.toSeriesDetails()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            return null
        }
    }

    suspend fun getMovieCredits(id: Int): List<MovieCast> {
        return try {
            val result = MovieClient.createMoviesService().getMovieCredits(id)
            result.cast?.mapNotNull {
                it?.toMovieAndSeriesCast()
            } ?: emptyList()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            emptyList()
        }
    }

    suspend fun getSeriesCredits(id: Int): List<MovieCast> {
        return try {
            val result = MovieClient.createMoviesService().getSeriesCredits(id)
            result.cast?.mapNotNull {
                it?.toMovieAndSeriesCast()
            } ?: emptyList()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            emptyList()
        }
    }

    suspend fun getMovieImagesPoster(id: Int): List<MovieAndSeriesImagePoster> {
        return try {
            val result = MovieClient.createMoviesService().getMovieImagesPoster(id)
            result.posters?.mapNotNull {
                it?.toMovieAndSeriesImagePoster()
            } ?: emptyList()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            emptyList()
        }
    }

    suspend fun getSeriesImagesPoster(id: Int): List<MovieAndSeriesImagePoster> {
        return try {
            val result = MovieClient.createMoviesService().getSeriesImagesPoster(id)
            result.posters?.mapNotNull {
                it?.toMovieAndSeriesImagePoster()
            } ?: emptyList()
        } catch (ex: HttpException) {
            ex.printStackTrace()
            emptyList()
        }
    }
}

