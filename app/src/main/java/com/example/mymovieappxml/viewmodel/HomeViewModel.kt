package com.example.mymovieappxml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieappxml.components.SliderModel
import com.example.mymovieappxml.data.repository.MoviesRepository
import com.example.mymovieappxml.movies.Movie
import com.example.mymovieappxml.movies.Series
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _listUpcoming = MutableLiveData<List<SliderModel>>()
    private val _popularMovies = MutableLiveData<List<Movie>>()
    private val _platNowMovies = MutableLiveData<List<Movie>>()
    private val _topRateMovies = MutableLiveData<List<Movie>>()
    private val _popularSeries = MutableLiveData<List<Series>>()

    val listUpcoming: LiveData<List<SliderModel>>
        get() = _listUpcoming

    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies

    val playNowMovies: LiveData<List<Movie>>
        get() = _platNowMovies

    val topRateMovies: LiveData<List<Movie>>
        get() = _topRateMovies

    val popularSeries: LiveData<List<Series>>
        get() = _popularSeries

    init {
        getListUpcomingSliderModel()
        getMostPopularMovies()
        getPlayNowMovies()
        getTopRateMovies()
        getPopularSeries()
    }

    private fun getListUpcomingSliderModel() {
        viewModelScope.launch {
            val movies = MoviesRepository.getUpComingMoviesSliderModel().take(5)
            _listUpcoming.value = movies
        }
    }

    private fun getMostPopularMovies() {
        viewModelScope.launch {
            val movies = MoviesRepository.getPopularMovies()
            _popularMovies.value = movies
        }
    }

    private fun getPlayNowMovies() {
        viewModelScope.launch {
            val movies = MoviesRepository.getPlayNow()
            _platNowMovies.value = movies
        }
    }

    private fun getTopRateMovies() {
        viewModelScope.launch {
            val movies = MoviesRepository.getTopRate()
            _topRateMovies.value = movies
        }
    }

    private fun getPopularSeries() {
        viewModelScope.launch {
            val series = MoviesRepository.getPopularSeries()
            _popularSeries.value = series
        }
    }
}