package com.example.mymovieappxml.view

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
    private var lastFragment: Fragment = homeFragment
    private var setOnclickListenerFragment: Boolean = true
    private var isAddFragmentAlready = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = binding.bottomNavigation
        fragmentReplace(homeFragment, "home")

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    if (setOnclickListenerFragment) {
                        addFragment(homeFragment, "home")
                        lastFragment = homeFragment
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.search -> {
                    if (setOnclickListenerFragment) {
                        addFragment(searchFragment, "search")
                        lastFragment = searchFragment
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.download -> {
                    if (setOnclickListenerFragment) {
                        addFragment(downloadFragment, "download")
                        lastFragment = downloadFragment
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.favorites -> {
                    if (setOnclickListenerFragment) {
                        addFragment(favoritesFragment, "favorites")
                        lastFragment = favoritesFragment
                    }
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }

        supportFragmentManager.addOnBackStackChangedListener {

            val containerFragments =
                supportFragmentManager.findFragmentById(binding.containerFrameLayout.id)

            if (containerFragments is HomeFragment || containerFragments is SearchFragment ||
                containerFragments is DownloadFragment || containerFragments is FavoritesFragment
            ) {
                bottomNavigationView.isGone = false
            } else {
                bottomNavigationView.isGone = true
            }
        }

    }

    fun replaceFragment(fragment: Class<out Fragment>, args: Bundle? = null) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(binding.containerFrameLayout.id, fragment, args)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

    fun fragmentReplace(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.containerFrameLayout.id, fragment, tag)
        transaction.commit()
    }

    fun addFragment(fragment: Fragment, tag: String) {
        if (fragment.isAdded && fragment.isVisible){
            return
        }
        val transaction = supportFragmentManager.beginTransaction()
        if (fragment.isAdded ) {
            transaction
                .hide(lastFragment)
                .show(fragment)
            isAddFragmentAlready = true

        } else {
            transaction.hide(lastFragment)
                .add(binding.containerFrameLayout.id, fragment, tag)
                .show(fragment)
                .addToBackStack(null)

           /* println("El fragmento nuevo ha sido agregado")
            println("este es el fragmento ${fragment.tag}")
            println("este es el ultimo fragmento ${lastFragment.tag}")*/
        }
        transaction.commit()
    }

    fun showFragment(fragment: Fragment) {
        if (fragment.isAdded ) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction
                .hide(lastFragment)
                .show(fragment)
            transaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()
        val callback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.fragments.size == 1) {
                    finish()
                }

                if (!isAddFragmentAlready) {
                    supportFragmentManager.popBackStackImmediate()
                }
                when (supportFragmentManager.fragments.last()) {
                    homeFragment -> {
                        binding.bottomNavigation.selectedItemId = R.id.home

                        showFragment(homeFragment)
                    }

                    searchFragment -> {
                        binding.bottomNavigation.selectedItemId = R.id.search
                        showFragment(searchFragment)
                    }

                    downloadFragment -> {
                        binding.bottomNavigation.selectedItemId = R.id.download
                        showFragment(downloadFragment)
                    }

                    favoritesFragment -> {
                        binding.bottomNavigation.selectedItemId = R.id.favorites
                        showFragment(favoritesFragment)
                    }
                }
                setOnclickListenerFragment = true
                isAddFragmentAlready = false
            }
        }
        this.onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )
    }
}