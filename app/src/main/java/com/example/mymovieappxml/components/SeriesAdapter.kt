package com.example.mymovieappxml.components

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieappxml.R
import com.example.mymovieappxml.movies.Series
import com.example.mymovieappxml.movies.imageMovieUrl
import com.example.mymovieappxml.view.MainActivity2

class SeriesAdapter(private val seriesList: List<Series>) : RecyclerView.Adapter<SeriesViewHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        return SeriesViewHolder(layoutInflater.inflate(R.layout.item_of_movie_or_series, parent, false))
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val item = seriesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MainActivity2::class.java)
            intent.putExtra("title", item.title )
            intent.putExtra("imageBackground", imageMovieUrl(item.url))
            intent.putExtra("type", item.type)
            intent.putExtra("id", item.id.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = seriesList.size
}