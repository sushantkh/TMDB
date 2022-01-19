package com.sushant.tmdbexample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sushant.tmdbexample.database.dao.MovieDAO
import com.sushant.tmdbexample.database.entity.MovieEntity

@Database(entities = [MovieEntity::class],version = 1,exportSchema = false)
abstract class MovieDB :RoomDatabase() {
    abstract fun movieDao():MovieDAO
}