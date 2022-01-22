package com.sushant.tmdbexample.feature.home

import com.sushant.tmdbexample.database.dao.MovieDAO
import com.sushant.tmdbexample.database.entity.MovieEntity
import com.sushant.tmdbexample.network.MovieApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val movieApiService: MovieApiService,
    private val dao: MovieDAO
) {

    // fun getTopRatedMovies() = movieApiService.getTopRatedMovies()

    fun insertMovie(movie: MovieEntity) {
        dao.addMovie(movie)
    }

    fun getSavedMovies(): List<MovieEntity> {
        return dao.getAllSavedMovies()
    }

    fun deleteMovie(id:Int) {
        dao.deleteMovie(id)
    }
}


