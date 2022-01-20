package com.sushant.tmdbexample

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sushant.tmdbexample.database.MovieDB
import com.sushant.tmdbexample.database.dao.MovieDAO
import com.sushant.tmdbexample.database.entity.MovieEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieTest : TestCase() {

    private lateinit var db: MovieDB
    private lateinit var dao: MovieDAO

    @Before
   public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MovieDB::class.java).build()
        dao = db.movieDao()
    }

    @After
   fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertOperation() = runBlocking {

        val movie=MovieEntity().apply {
            this.id=1
            this.title="Movie-Title"
            this.posterPath="poster path"
            this.adult=false
            this.overview="This is a test object"
            this.voteAverage=9.0
            this.voteCount=100
        }

        dao.addMovie(movie)

        val movies = dao.getAllSavedMovies()
        assert(movies.contains(movie))

    }
}