package com.example.mymovieappxml.components

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieappxml.R
import com.example.mymovieappxml.movies.Movie
import com.example.mymovieappxml.movies.imageMovieUrl

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val image = view.findViewById<ImageView>(R.id.movieOrSeriesImage)
    fun render(movieModel: Movie) {
        Glide.with(image.context).load(imageMovieUrl(movieModel.url)).into(image)
    }

}