package com.example.mymovieappxml.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.mymovieappxml.R
import com.example.mymovieappxml.databinding.ActivityMain2Binding
import com.example.mymovieappxml.viewmodel.DetailsViewModel



class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    /*private var movieOrSeriesDetail: DetailsViewModel.MovieDetailsState = DetailsViewModel.MovieDetailsState.Loading*/
    private val detailsViewModel: DetailsViewModel by viewModels()
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
        val id = bundle?.getInt("id")?: Int.MIN_VALUE
        val titleData = bundle?.getString("title")
        val imageBackgroundImageUrl = bundle?.getString("imageBackground")
        detailsViewModel.getMovieAndSeriesDetails(id,type)

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
               }

               is DetailsViewModel.MovieDetailsState.Success -> {
                   binding.progressBar.isVisible = false
                   binding.imageFromTheMovieOrSeriesForScreenBackground.isVisible = true
                   binding.scrollableScreenDetails.isVisible = true
                   val details = (movieOrSeriesDetail).details
                   findViewById<TextView>(binding.premiereDate.id).apply { this.text = details._releaseDate }
                   findViewById<TextView>(binding.durationMovieOrSeries.id).apply { this.text = details._runtime.toString().plus(" minutes") }
                   if (details._type == "movie"){
                       findViewById<TextView>(binding.categoriesDescription.id).apply { this.text = details._genre[0].name }
                   }
                   else{
                       findViewById<TextView>(binding.categoriesDescription.id).apply { this.text = details._genres[0].name }
                   }
                   findViewById<TextView>(binding.tagLineDescription.id).apply {
                       this.text = details._tagline
                   }
                   findViewById<TextView>(binding.punctuation.id).apply {
                       this.text = details._voteAverage.toString().take(3)
                   }
                   findViewById<TextView>(binding.storyLineDescription.id).apply { this.text = details._overview }
               }
           }
        })


    }



}