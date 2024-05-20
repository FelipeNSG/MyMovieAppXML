package com.example.mymovieappxml.components

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieappxml.R
import com.example.mymovieappxml.movies.MovieCast
import com.example.mymovieappxml.movies.imageMovieUrl

class CastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.castMovieOrSeriesImage)
    private val actorName = view.findViewById<TextView>(R.id.actorImage)
    private val character = view.findViewById<TextView>(R.id.character)

    fun render(castModel: MovieCast) {
        Glide.with(image.context).load(imageMovieUrl(castModel.profilePath)).into(image)
        actorName.apply { this.text = castModel.originalName }
        character.apply { this.text = castModel.character }
    }

}