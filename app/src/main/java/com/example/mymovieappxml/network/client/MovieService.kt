package com.example.mymovieappxml.network.client

import com.example.mymovieappxml.network.model.MovieList
import com.example.mymovieappxml.network.model.MovieListsWithDate
import com.example.mymovieappxml.network.model.movieandseriescredit.MovieAndSeriesCredits
import com.example.mymovieappxml.network.model.movieandseriesimages.MovieAndSeriesImages
import com.example.mymovieappxml.network.model.moviedata.MovieId
import com.example.mymovieappxml.network.model.seriesdata.SeriesId
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(): MovieList

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(): MovieListsWithDate

    @GET("3/movie/now_playing?language=en-US&page=1")
    suspend fun getPlayNow(): MovieListsWithDate

    @GET("3/movie/top_rated?language=en-US&page=3")
    suspend fun getTopRate(): MovieList

    @GET("3/tv/popular?language=en-US&page=1")
    suspend fun getPopularSeries(): MovieList

    @GET("3/movie/{id}?language=en-US")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieId
    @GET("3/tv/{id}?language=en-US")
    suspend fun getSeriesDetails(@Path("id") id: Int): SeriesId

    @GET("3/movie/{id}/credits")
    suspend fun getMovieCredits(@Path("id") id: Int): MovieAndSeriesCredits
    @GET("3/tv/{id}/credits?language=en-US")
    suspend fun getSeriesCredits(@Path("id") id: Int): MovieAndSeriesCredits

    @GET("3/movie/{id}/images")
    suspend fun getMovieImagesPoster(@Path("id") id: Int): MovieAndSeriesImages

    @GET("3/tv/{id}/images")
    suspend fun getSeriesImagesPoster(@Path("id") id: Int): MovieAndSeriesImages

}