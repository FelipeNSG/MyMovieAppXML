package com.example.mymovieappxml.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
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

        fragmentReplace(homeFragment)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {

                    addFragment(homeFragment,"home")


                    return@setOnItemSelectedListener true
                }

                R.id.search -> {
                    addFragment(searchFragment,"search")
                    return@setOnItemSelectedListener true
                }

                R.id.download -> {
                    addFragment(downloadFragment,"download")
                    return@setOnItemSelectedListener true
                }

                R.id.favorites -> {
                    addFragment(favoritesFragment,"favorites")
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
        fragmentTransaction.add(binding.containerFrameLayout.id, fragment, args)
       fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("principal")
        fragmentTransaction.commit()

   }

    fun fragmentReplace(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.containerFrameLayout.id, fragment)
        transaction.addToBackStack("principal")
        transaction.commit()
    }

    fun addFragment(fragment: Fragment, tag:String) {
        val transaction = supportFragmentManager.beginTransaction()
        val currentFragment = supportFragmentManager.fragments.last() //take care. If before you dont have any fragment stack, it can cause empty exception! // The Correct way is: supportFragmentManager.fragments.last()?.getChildFragmentManager()?.getFragments()?.get(0)

        if (fragment.isAdded) {
            transaction
                .hide(currentFragment)
                .show(fragment)
        } else {
            transaction
                .hide(currentFragment)
                .add(binding.containerFrameLayout.id, fragment, tag)
        }

        transaction.commit()
    }

}