package com.sushant.tmdbexample.feature.home

import com.sushant.tmdbexample.network.MovieApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val movieApiService: MovieApiService) {

    fun getTopRatedMovies() = movieApiService.getTopRatedMovies()

}


