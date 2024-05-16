package com.example.mymovieappxml.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieappxml.R
import com.example.mymovieappxml.view.MainActivity

class SliderAdapter(var list: List<SliderModel>, private var context: Context) :
    RecyclerView.Adapter<SliderAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val sliderImage: ImageView = itemView.findViewById(R.id.slider_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        Glide.with(context.applicationContext)
            .load(currentItem.image)
            .into(holder.sliderImage)
        holder.itemView.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}