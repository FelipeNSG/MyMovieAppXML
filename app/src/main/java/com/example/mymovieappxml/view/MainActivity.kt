package com.example.mymovieappxml.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.mymovieappxml.R
import com.example.mymovieappxml.databinding.ActivityContainerBinding
import com.example.mymovieappxml.view.main.DownloadFragment
import com.example.mymovieappxml.view.main.FavoritesFragment
import com.example.mymovieappxml.view.main.HomeFragment
import com.example.mymovieappxml.view.main.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContainerBinding

    private val homeFragment = HomeFragment()

    private val searchFragment = SearchFragment()
    private val downloadFragment = DownloadFragment()
    private val favoritesFragment = FavoritesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = binding.bottomNavigation
        replaceFragment(HomeFragment::class.java)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {

                    replaceFragment(homeFragment::class.java)

                    return@setOnItemSelectedListener true
                }

                R.id.search -> {
                    replaceFragment(searchFragment::class.java)
                    return@setOnItemSelectedListener true
                }

                R.id.download -> {
                    replaceFragment(downloadFragment::class.java)
                    return@setOnItemSelectedListener true
                }

                R.id.favorites -> {
                    replaceFragment(favoritesFragment::class.java)
                    return@setOnItemSelectedListener true
                }

            }
            return@setOnItemSelectedListener false
        }

        supportFragmentManager.addOnBackStackChangedListener {

            val containerFragments =
                supportFragmentManager.findFragmentById(binding.containerFrameLayout.id)

            if (containerFragments is HomeFragment|| containerFragments is SearchFragment ||
                containerFragments is DownloadFragment || containerFragments is FavoritesFragment
            ) {
                bottomNavigationView.isGone = false
            }
            else {
                bottomNavigationView.isGone = true
            }
        }

    }

   fun replaceFragment(fragment: Class<out Fragment>, args: Bundle? = null) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_FrameLayout, fragment, args)
        fragmentTransaction.addToBackStack("principal")
        fragmentTransaction.commit()
    }

}