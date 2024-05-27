package com.example.mymovieappxml.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.mymovieappxml.R
import com.example.mymovieappxml.components.MovieAdapter
import com.example.mymovieappxml.components.SeriesAdapter
import com.example.mymovieappxml.components.SliderAdapter
import com.example.mymovieappxml.components.SliderModel
import com.example.mymovieappxml.databinding.ActivityMainBinding
import com.example.mymovieappxml.movies.Movie
import com.example.mymovieappxml.movies.Series
import com.example.mymovieappxml.viewmodel.HomeViewModel
import kotlin.math.abs
import kotlinx.coroutines.Runnable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var isViewImage: ViewPager2
    lateinit var isList: MutableList<SliderModel>
    private var popularMovies: MutableList<Movie> = mutableListOf()
    private var playNowMovies: MutableList<Movie> = mutableListOf()
    private var topRateMovies: MutableList<Movie> = mutableListOf()
    private var popularSeries: MutableList<Series> = mutableListOf()
    lateinit var adapter: SliderAdapter
    val sliderHandler = Handler(Looper.myLooper()!!)
    private lateinit var movieAdapterPopularMovies: MovieAdapter
    private lateinit var movieAdapterPlayNowMovies: MovieAdapter
    private lateinit var movieAdapterTopRateMovies: MovieAdapter
    private lateinit var movieAdapterPopularSeries: SeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isViewImage = findViewById(R.id.is_view_image)
        isList = mutableListOf()
        popularMovies = mutableListOf()
        isViewImage.clipChildren = false
        isViewImage.clipToPadding = false
        isViewImage.offscreenPageLimit = 5
        isViewImage.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        isViewImage.setPageTransformer(compositePageTransformer)
        isViewImage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 4000)

                if (position == isList.size - 2) {
                    isViewImage.post(runnable)
                }
                adapter.notifyDataSetChanged()
            }
        })

        homeViewModel.listUpcoming.observe(this, Observer { listOfUpComingMovies ->
            isList = listOfUpComingMovies.toMutableList()
            adapter = SliderAdapter(isList)
            isViewImage.adapter = adapter

        })
        homeViewModel.listUpcoming

        homeViewModel.popularMovies.observe(this, Observer { listOfPopularMovies ->
            popularMovies = listOfPopularMovies.toMutableList()
            movieAdapterPopularMovies = MovieAdapter(popularMovies.toList())
            initRecyclerViewUpcomingMoviesList()
        })

        homeViewModel.playNowMovies.observe(this, Observer { playNowMovieList ->
            playNowMovies = playNowMovieList.toMutableList()
            movieAdapterPlayNowMovies = MovieAdapter(playNowMovies)
            initRecyclerViewPlayNowMovieList()

        })

        homeViewModel.topRateMovies.observe(this, Observer { topRateMoviesList ->
            topRateMovies = topRateMoviesList.toMutableList()
            movieAdapterTopRateMovies = MovieAdapter(topRateMovies)
            initRecyclerViewTopRateMovies()

        })
        homeViewModel.popularSeries.observe(this, Observer { popularSeriesList ->
            popularSeries = popularSeriesList.toMutableList()
            movieAdapterPopularSeries = SeriesAdapter(popularSeries)
            initRecyclerViewPopularSeries()
        })

    }

    val sliderRunnable = Runnable { isViewImage.currentItem = isViewImage.currentItem + 1 }
    val runnable = Runnable {
        isList.addAll(isList)
        adapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.postDelayed(sliderRunnable, 500)
    }

    //List of movies
    private fun initRecyclerViewUpcomingMoviesList() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPopularMovies)
        recyclerView.layoutManager = LinearLayoutManager(
            this, RecyclerView.HORIZONTAL, false
        )
        recyclerView.adapter = movieAdapterPopularMovies
    }

    private fun initRecyclerViewPlayNowMovieList() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPlayNowMovies)
        recyclerView.layoutManager = LinearLayoutManager(
            this, RecyclerView.HORIZONTAL, false
        )
        recyclerView.adapter = movieAdapterPlayNowMovies
    }

    private fun initRecyclerViewTopRateMovies() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTopRateMovies)
        recyclerView.layoutManager = LinearLayoutManager(
            this, RecyclerView.HORIZONTAL, false
        )
        recyclerView.adapter = movieAdapterTopRateMovies
    }

    private fun initRecyclerViewPopularSeries() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPopularSeries)
        recyclerView.layoutManager = LinearLayoutManager(
            this, RecyclerView.HORIZONTAL, false
        )
        recyclerView.adapter = movieAdapterPopularSeries
    }


}