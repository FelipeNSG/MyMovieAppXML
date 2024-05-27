package com.example.mymovieappxml.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieappxml.R
import com.example.mymovieappxml.components.CastAdapter
import com.example.mymovieappxml.components.GalleryAdapter
import com.example.mymovieappxml.databinding.ActivityMain2Binding

import com.example.mymovieappxml.movies.MovieAndSeriesImagePoster
import com.example.mymovieappxml.movies.MovieCast
import com.example.mymovieappxml.viewmodel.DetailsViewModel

class MainFragment2 : Fragment(R.layout.activity_main2) {
    private lateinit var binding: ActivityMain2Binding
    private val detailsViewModel: DetailsViewModel by viewModels()
    private var castList: MutableList<MovieCast> = mutableListOf()
    private var galleryImages: MutableList<MovieAndSeriesImagePoster> = mutableListOf()
    private lateinit var castAdapter: CastAdapter
    private lateinit var galleryAdapter: GalleryAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ActivityMain2Binding.bind(view)

        fun initRecyclerViewCastList() {
            val recyclerView = binding.recyclerCast
            recyclerView.layoutManager = LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
            )
            recyclerView.adapter = castAdapter
        }

        fun initRecyclerViewGallery() {
            val recyclerView = binding.recyclerGallery
            recyclerView.layoutManager = LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
            )
            recyclerView.adapter = galleryAdapter
        }


        fun screenLogin() {
            binding.progressBar.isVisible = true
            binding.imageFromTheMovieOrSeriesForScreenBackground.isVisible = false
            binding.scrollableScreenDetails.isVisible = false
        }
        screenLogin()
        val bundle = arguments
        val type = bundle?.getString("type") ?: "movie"
        val id = bundle?.getString("id") ?: "Unknown"
        val titleData = bundle?.getString("title")
        val imageBackgroundImageUrl = bundle?.getString("imageBackground")
        detailsViewModel.getMovieAndSeriesDetails(id.toInt(), type)

        binding.movieTitle.text = detailsViewModel.titleTransform(titleData ?: "Unknown")
        activity?.let {
            Glide.with(it)
                .load(imageBackgroundImageUrl)
                .centerCrop()
                .into(binding.imageFromTheMovieOrSeriesForScreenBackground)
        }

        activity?.let {
            Glide.with(it)
                .load(imageBackgroundImageUrl)
                .centerCrop()
                .into(binding.mainImage)
        }


        detailsViewModel.movieAndSeriesDetails.observe(
            viewLifecycleOwner,
            Observer { movieOrSeriesDetail ->
                when (movieOrSeriesDetail) {
                    DetailsViewModel.MovieDetailsState.Loading -> {
                        screenLogin()
                        CastAdapter(emptyList())
                        GalleryAdapter(emptyList())
                    }

                    is DetailsViewModel.MovieDetailsState.Success -> {
                        val backButton = binding.backArrowAppBar
                        backButton.setOnClickListener {

                        }
                        binding.progressBar.isVisible = false
                        binding.imageFromTheMovieOrSeriesForScreenBackground.isVisible = true
                        binding.scrollableScreenDetails.isVisible = true
                        val details = (movieOrSeriesDetail).details


                        if (details._type == "movie") {
                            binding.premiereDate.text = details._releaseDate
                            binding.durationMovieOrSeries.text =
                                details._runtime.toString().plus(" minutes")
                            binding.categoriesDescription.text = details._genre[0].name
                        } else {
                            binding.premiereDate.text = details._firstAirDate.take(4)
                            if (details._episodeRunTime.isNotEmpty()) {
                                binding.durationMovieOrSeries.text =
                                    details._episodeRunTime[0].toString().plus(" minutes")
                            } else {
                                binding.durationMovieOrSeries.text =
                                    getString(R.string.unknownTitle)
                            }

                            binding.categoriesDescription.text = details._genres[0].name
                        }

                        binding.tagLineDescription.text = details._tagline


                        binding.punctuation.text = details._voteAverage.toString().take(3)

                        binding.storyLineDescription.text = details._overview

                        castList = movieOrSeriesDetail.credits.toMutableList()
                        castAdapter = CastAdapter(castList)
                        initRecyclerViewCastList()

                        galleryImages = movieOrSeriesDetail.imagePoster.toMutableList()
                        galleryAdapter = GalleryAdapter(galleryImages)
                        initRecyclerViewGallery()
                    }
                }
            })

    }


}