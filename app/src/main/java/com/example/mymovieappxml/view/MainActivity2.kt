package com.example.mymovieappxml.view

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
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



class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private val detailsViewModel: DetailsViewModel by viewModels()
    private var castList: MutableList<MovieCast> = mutableListOf()
    private var galleryImages: MutableList<MovieAndSeriesImagePoster> = mutableListOf()
    private lateinit var  castAdapter:CastAdapter
    private lateinit var  galleryAdapter:GalleryAdapter


    private fun initRecyclerViewCastList() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerCast)
        recyclerView.layoutManager = LinearLayoutManager(
            this, RecyclerView.HORIZONTAL, false
        )
        recyclerView.adapter = castAdapter
    }

    private fun initRecyclerViewGallery() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerGallery)
        recyclerView.layoutManager = LinearLayoutManager(
            this, RecyclerView.HORIZONTAL, false
        )
        recyclerView.adapter = galleryAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        this.resources.getString(R.string.movie)
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)

        fun screenLogin(){
            binding.progressBar.isVisible = true
            binding.imageFromTheMovieOrSeriesForScreenBackground.isVisible = false
            binding.scrollableScreenDetails.isVisible = false
        }
        screenLogin()
        setContentView(binding.root)
        val bundle = intent.extras
        val type = bundle?.getString("type")?:"movie"
        val id = bundle?.getString("id")?: "Unknown"
        val titleData = bundle?.getString("title")
        val imageBackgroundImageUrl = bundle?.getString("imageBackground")
        detailsViewModel.getMovieAndSeriesDetails(id.toInt(),type)

        findViewById<TextView>(binding.movieTitle.id).apply { this.text = detailsViewModel.titleTransform(titleData?:"Unknown") }
        findViewById<ImageView>(binding.imageFromTheMovieOrSeriesForScreenBackground.id).apply {
            Glide.with(this.context)
                .load(imageBackgroundImageUrl)
                .centerCrop()
                .into(this)
        }
        findViewById<ImageView>(binding.mainImage.id).apply {
            Glide.with(this.context)
                .load(imageBackgroundImageUrl)
                .centerCrop()
                .into(this)
        }

       detailsViewModel.movieAndSeriesDetails.observe(this, Observer { movieOrSeriesDetail ->
           when(movieOrSeriesDetail){
               DetailsViewModel.MovieDetailsState.Loading -> {
                 screenLogin()
                   CastAdapter(emptyList())
                   GalleryAdapter(emptyList())
               }

               is DetailsViewModel.MovieDetailsState.Success -> {
                  val backButton = binding.backArrowAppBar
                   backButton.setOnClickListener {
                       finish()
                   }
                   binding.progressBar.isVisible = false
                   binding.imageFromTheMovieOrSeriesForScreenBackground.isVisible = true
                   binding.scrollableScreenDetails.isVisible = true
                   val details = (movieOrSeriesDetail).details


                   if (details._type == "movie"){
                       findViewById<TextView>(binding.premiereDate.id).apply { this.text = details._releaseDate }
                       findViewById<TextView>(binding.durationMovieOrSeries.id).apply { this.text = details._runtime.toString().plus(" minutes") }
                       findViewById<TextView>(binding.categoriesDescription.id).apply { this.text = details._genre[0].name }
                   }
                   else{
                       findViewById<TextView>(binding.premiereDate.id).apply{ this.text = details._firstAirDate.take(4) }
                       if (details._episodeRunTime.isNotEmpty() ){findViewById<TextView>(binding.durationMovieOrSeries.id)
                           .apply { this.text = details._episodeRunTime[0].toString().plus(" minutes") }}
                       else{
                           findViewById<TextView>(binding.durationMovieOrSeries.id)
                               .apply { this.text = getString(R.string.unknownTitle) }
                       }
                       findViewById<TextView>(binding.categoriesDescription.id).apply {
                           this.text = details._genres[0].name
                       }
                   }
                   findViewById<TextView>(binding.tagLineDescription.id).apply {
                       this.text = details._tagline
                   }
                   findViewById<TextView>(binding.punctuation.id).apply {
                       this.text = details._voteAverage.toString().take(3)
                   }
                   findViewById<TextView>(binding.storyLineDescription.id).apply { this.text = details._overview }

                   castList = movieOrSeriesDetail.credits.toMutableList()
                   castAdapter =  CastAdapter(castList)
                   initRecyclerViewCastList()

                   galleryImages = movieOrSeriesDetail.imagePoster.toMutableList()
                   galleryAdapter = GalleryAdapter(galleryImages)
                   initRecyclerViewGallery()
               }
           }
        })
    }



}