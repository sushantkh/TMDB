package com.sushant.tmdbexample.listener

import com.sushant.tmdbexample.database.entity.MovieEntity

interface SavedMovieClickListener {
    fun onItemClick(result: MovieEntity)
    fun onStarButtonClick(result: MovieEntity,isSelected:Boolean)
}