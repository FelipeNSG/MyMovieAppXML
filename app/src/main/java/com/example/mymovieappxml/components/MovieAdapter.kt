package com.example.mymovieappxml.components

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieappxml.R
import com.example.mymovieappxml.movies.Movie
import com.example.mymovieappxml.movies.imageMovieUrl
import com.example.mymovieappxml.view.MainActivity2

class MovieAdapter(private val moviesList: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {
    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        return MovieViewHolder(layoutInflater.inflate(R.layout.item_of_movie_or_series, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = moviesList[position]
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

    override fun getItemCount(): Int = moviesList.size

}