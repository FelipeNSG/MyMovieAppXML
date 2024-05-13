package com.example.mymovieappxml.components

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieappxml.R
import com.example.mymovieappxml.movies.Series
import com.example.mymovieappxml.movies.imageMovieUrl

class SeriesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.movieOrSeriesImage)
    fun render(seriesModel: Series) {
        Glide.with(image.context).load(imageMovieUrl(seriesModel.url)).into(image)
    }
}