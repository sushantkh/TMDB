package com.sushant.tmdbexample.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movie_entity")
class MovieEntity : Serializable {
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
    var isSelected: Boolean = false

    override fun equals(other: Any?): Boolean {
        if (other==null||other !is MovieEntity)
            return false

        return this.id==other.id
    }

}