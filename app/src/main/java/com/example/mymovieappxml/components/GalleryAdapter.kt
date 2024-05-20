package com.example.mymovieappxml.components

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieappxml.R
import com.example.mymovieappxml.movies.MovieAndSeriesImagePoster
import com.example.mymovieappxml.movies.imageMovieUrl

class GalleryAdapter(private val movieAndSeriesImagePosterList: List<MovieAndSeriesImagePoster>) :
    RecyclerView.Adapter<GalleryViewHolder>() {
    private lateinit var context: Context
    private val movieAndSeriesImagePosterListGallery =  if (movieAndSeriesImagePosterList.size >= 10)
    { movieAndSeriesImagePosterList.filter { it.iso6391 == "en" }.take(10) }
    else {
        movieAndSeriesImagePosterList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        return GalleryViewHolder(
            layoutInflater.inflate(
                R.layout.item_of_movie_or_series,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val item = movieAndSeriesImagePosterListGallery[position]
        holder.render(item)
        val imagePopup = LayoutInflater.from(context).inflate(R.layout.popup, null, false)
        Glide.with(imagePopup.context).load(imageMovieUrl(item.filePath)).into(imagePopup.findViewById(R.id.imagePoster))
        val mDialog = Dialog(context)
        mDialog.setContentView(imagePopup)
        mDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        holder.itemView.setOnClickListener{
            mDialog.show()
        }

    }

    override fun getItemCount(): Int = movieAndSeriesImagePosterListGallery.size
}