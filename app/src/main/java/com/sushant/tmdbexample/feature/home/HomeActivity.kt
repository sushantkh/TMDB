package com.sushant.tmdbexample.feature.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sushant.tmdbexample.R
import com.sushant.tmdbexample.databinding.ActivityHomeBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_top, R.id.navigation_favourite
            )
        )
        homeViewModel= ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.navigateToDetail.observe(this, Observer {
            val bundle = bundleOf("movie" to it)
            navController.navigate(R.id.action_navigation_top_to_nvaigation_detail, bundle)
        })

        homeViewModel.navigateToDetailFromSavedMovies.observe(this, Observer {
            val bundle = bundleOf("saved_movie" to it)
            navController.navigate(R.id.action_navigation_favourite_to_nvaigation_detail, bundle)
        })

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}