package com.example.mymovieappxml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieappxml.components.SliderModel
import com.example.mymovieappxml.data.repository.MoviesRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _listUpcoming = MutableLiveData<List<SliderModel>>()

    val listUpcoming: LiveData<List<SliderModel>>
        get() = _listUpcoming



     fun getListUpcomingSliderModel() {
        viewModelScope.launch {
            val movies = MoviesRepository.getUpComingMoviesSliderModel().take(4)
            _listUpcoming.value = movies
        }
    }


}