package com.example.mymovieappxml.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.core.view.isInvisible
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.mymovieappxml.R
import com.example.mymovieappxml.components.MovieAdapter
import com.example.mymovieappxml.components.SeriesAdapter
import com.example.mymovieappxml.components.SliderAdapter
import com.example.mymovieappxml.components.SliderModel
import com.example.mymovieappxml.databinding.ActivityMainBinding
import com.example.mymovieappxml.movies.Movie
import com.example.mymovieappxml.movies.Series
import com.example.mymovieappxml.viewmodel.HomeViewModel
import kotlin.math.abs
import kotlinx.coroutines.Runnable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        supportFragmentManager.commit {
            add<MainFragment>(R.id.container_activity)
            setReorderingAllowed(true)
        }

    }

}