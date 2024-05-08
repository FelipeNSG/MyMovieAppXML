package com.example.mymovieappxml.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.mymovieappxml.R
import com.example.mymovieappxml.components.SliderAdapter
import com.example.mymovieappxml.components.SliderModel
import com.example.mymovieappxml.viewmodel.HomeViewModel
import kotlin.math.abs
import kotlinx.coroutines.Runnable

class MainActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    lateinit var isViewImage: ViewPager2
    var isList: List<SliderModel> = emptyList()
    lateinit var adapter: SliderAdapter
    val sliderHandler = Handler(Looper.myLooper()!!)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isViewImage = findViewById(R.id.is_view_image)
        isList = mutableListOf()
        adapter = SliderAdapter(isList, this)
        isViewImage.adapter = adapter
        /*isViewImage.clipChildren = false
        isViewImage.clipToPadding = false
        isViewImage.offscreenPageLimit = 3*/
      /*  isViewImage.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        isViewImage.setPageTransformer(compositePageTransformer)
        isViewImage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 2000)
                if (position == isList.size - 2) {
                    isViewImage.post(runnable)
                }

            }
        })
        */
        homeViewModel.listUpcoming.observe(this, Observer { listOfUpComingMovies ->
            if (listOfUpComingMovies.isNotEmpty()) {
                isList = listOfUpComingMovies.toMutableList()
                println(isList.size)
                println(isList[0].image)
            }
            adapter.notifyDataSetChanged()
        })
        homeViewModel.getListUpcomingSliderModel()
    }

    /*val sliderRunnable = Runnable { isViewImage.currentItem = isViewImage.currentItem + 1 }
    val runnable = Runnable {
        isList.addAll(isList)
        adapter.notifyDataSetChanged()
    }*/

    override fun onPause() {
        super.onPause()
        /*sliderHandler.postDelayed(sliderRunnable, 500)*/
    }

}