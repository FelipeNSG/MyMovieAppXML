package com.example.mymovieappxml.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieappxml.R
import com.example.mymovieappxml.movies.Series

class SeriesAdapter(private val seriesList: List<Series>) : RecyclerView.Adapter<SeriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SeriesViewHolder(layoutInflater.inflate(R.layout.item_of_movie_or_series, parent, false))
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val item = seriesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = seriesList.size
}