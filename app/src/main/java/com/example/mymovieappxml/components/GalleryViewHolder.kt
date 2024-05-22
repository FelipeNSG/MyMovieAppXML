package com.example.mymovieappxml.components

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieappxml.R
import com.example.mymovieappxml.movies.MovieAndSeriesImagePoster
import com.example.mymovieappxml.movies.imageMovieUrl

class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.movieOrSeriesImage)
    private val imageGallery = view.findViewById<ImageView>(R.id.imagePoster)

    fun render(imageModel: MovieAndSeriesImagePoster) {
        Glide.with(image.context).load(imageMovieUrl(imageModel.filePath)).into(image)
    }

}