package com.example.mymovieappxml.view.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
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
import com.example.mymovieappxml.databinding.ActivityContainerBinding
import com.example.mymovieappxml.databinding.ActivityMainBinding
import com.example.mymovieappxml.movies.Movie
import com.example.mymovieappxml.movies.Series
import com.example.mymovieappxml.movies.imageMovieUrl
import com.example.mymovieappxml.view.MainActivity
import com.example.mymovieappxml.view.details.DetailsFragment2
import com.example.mymovieappxml.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.callbackFlow
import kotlin.math.abs

class HomeFragment() : Fragment(R.layout.activity_main) {
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.bind(view)
        isViewImage = binding.isViewImage
        isList = mutableListOf()
        popularMovies = mutableListOf()
        isViewImage.clipChildren = false
        isViewImage.clipToPadding = false
        isViewImage.offscreenPageLimit = 5
        isViewImage.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val activity = requireActivity() as MainActivity


        //List of movies
        fun initRecyclerViewUpcomingMoviesList() {
            val recyclerView = binding.recyclerViewPopularMovies
            recyclerView.layoutManager = LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
            )
            recyclerView.adapter = movieAdapterPopularMovies
        }

        fun initRecyclerViewPlayNowMovieList() {
            val recyclerView = binding.recyclerViewPlayNowMovies
            recyclerView.layoutManager = LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
            )
            recyclerView.adapter = movieAdapterPlayNowMovies
        }

        fun initRecyclerViewTopRateMovies() {
            val recyclerView = binding.recyclerViewTopRateMovies
            recyclerView.layoutManager = LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
            )
            recyclerView.adapter = movieAdapterTopRateMovies
        }

        fun initRecyclerViewPopularSeries() {
            val recyclerView = binding.recyclerViewPopularSeries
            recyclerView.layoutManager = LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
            )
            recyclerView.adapter = movieAdapterPopularSeries
        }

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

        homeViewModel.listUpcoming.observe(viewLifecycleOwner, Observer { listOfUpComingMovies ->
            isList = listOfUpComingMovies.toMutableList()
            adapter = SliderAdapter(isList)
            isViewImage.adapter = adapter

        })


        homeViewModel.popularMovies.observe(viewLifecycleOwner, Observer { listOfPopularMovies ->
            popularMovies = listOfPopularMovies.toMutableList()
            movieAdapterPopularMovies = MovieAdapter(popularMovies.toList()) {
            activity.supportFragmentManager.fragments.clear()
                activity.replaceFragment(
                    DetailsFragment2::class.java,
                    bundleOf(
                        "title" to it.title,
                        "imageBackground" to imageMovieUrl(it.url),
                        "type" to it.type,
                        "id" to it.id.toString()
                    )
                )
            }
            initRecyclerViewUpcomingMoviesList()
        })

        homeViewModel.playNowMovies.observe(viewLifecycleOwner, Observer { playNowMovieList ->
            playNowMovies = playNowMovieList.toMutableList()
            movieAdapterPlayNowMovies = MovieAdapter(playNowMovies) {
                activity.supportFragmentManager.fragments.clear()
                activity.replaceFragment(
                    DetailsFragment2::class.java,
                    bundleOf(
                        "title" to it.title,
                        "imageBackground" to imageMovieUrl(it.url),
                        "type" to it.type,
                        "id" to it.id.toString()
                    )
                )

            }
            initRecyclerViewPlayNowMovieList()

        })

        homeViewModel.topRateMovies.observe(viewLifecycleOwner, Observer { topRateMoviesList ->
            topRateMovies = topRateMoviesList.toMutableList()
            movieAdapterTopRateMovies = MovieAdapter(topRateMovies) {
                activity.supportFragmentManager.fragments.clear()
                activity.replaceFragment(
                    DetailsFragment2::class.java,
                    bundleOf(
                        "title" to it.title,
                        "imageBackground" to imageMovieUrl(it.url),
                        "type" to it.type,
                        "id" to it.id.toString()
                    )
                )
            }
            initRecyclerViewTopRateMovies()

        })
        homeViewModel.popularSeries.observe(viewLifecycleOwner, Observer { popularSeriesList ->
            popularSeries = popularSeriesList.toMutableList()
            movieAdapterPopularSeries = SeriesAdapter(popularSeries) {
                activity.supportFragmentManager.fragments.clear()
                activity.replaceFragment(
                    DetailsFragment2::class.java,
                    bundleOf(
                        "title" to it.title,
                        "imageBackground" to imageMovieUrl(it.url),
                        "type" to it.type,
                        "id" to it.id.toString()
                    )
                )
            }
            initRecyclerViewPopularSeries()
        })


    }

    val sliderRunnable =
        kotlinx.coroutines.Runnable { isViewImage.currentItem = isViewImage.currentItem + 1 }
    val runnable = kotlinx.coroutines.Runnable {
        isList.addAll(isList)
        adapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.postDelayed(sliderRunnable, 500)
    }
}