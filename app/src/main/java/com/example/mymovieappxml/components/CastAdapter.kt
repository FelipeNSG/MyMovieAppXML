package com.example.mymovieappxml.components

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieappxml.R
import com.example.mymovieappxml.movies.MovieCast

class CastAdapter(private val castList: List<MovieCast>) : RecyclerView.Adapter<CastViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        return CastViewHolder(
            layoutInflater.inflate(
                R.layout.item_cast_movie_or_series,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = castList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = castList.size

}