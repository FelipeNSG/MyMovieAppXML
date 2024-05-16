package com.example.mymovieappxml.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.mymovieappxml.R
import com.example.mymovieappxml.databinding.ActivityMain2Binding
import com.example.mymovieappxml.viewmodel.DetailsViewModel
import com.example.mymovieappxml.viewmodel.HomeViewModel

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private val homeViewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        this.resources.getString(R.string.movie)
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        val titleData = bundle?.getString("title")
        val imageBackgroundImageUrl = bundle?.getString("imageBackground")
        val type = bundle?.getString("type")
        val id = bundle?.getInt("id")

        val title = findViewById<TextView>(binding.movieTitle.id).apply { this.text = homeViewModel.titleTransform(titleData?:"Unknown") }
        val imageBackground = findViewById<ImageView>(binding.imageFromTheMovieOrSeriesForScreenBackground.id).apply {
            Glide.with(this.context)
                .load(imageBackgroundImageUrl)
                .centerCrop()
                .into(this)
        }
        val mainImage = findViewById<ImageView>(binding.mainImage.id).apply {
            Glide.with(this.context)
                .load(imageBackgroundImageUrl)
                .centerCrop()
                .into(this)
        }
        val tagLine = findViewById<TextView>(binding.tagLineDescription.id)

    }
}