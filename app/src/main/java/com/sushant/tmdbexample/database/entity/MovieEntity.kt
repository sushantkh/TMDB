package com.sushant.tmdbexample.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entity")
class MovieEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null
    @ColumnInfo(name = "title")
    var title: String? = null
    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null
    @ColumnInfo(name = "adult")
    var adult: Boolean? = null
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = null
    @ColumnInfo(name = "overview")
    var overview: String? = null
    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double? = null
    @ColumnInfo(name = "voteCount")
    var voteCount: Int? = null
}