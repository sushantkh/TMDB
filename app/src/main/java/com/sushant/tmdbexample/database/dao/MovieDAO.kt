package com.sushant.tmdbexample.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sushant.tmdbexample.database.entity.MovieEntity
@Dao
interface MovieDAO {
    @Insert(onConflict = REPLACE)
    fun addMovie(movie: MovieEntity)

    @Query("select * from movie_entity")
    fun getAllSavedMovies(): List<MovieEntity>
}