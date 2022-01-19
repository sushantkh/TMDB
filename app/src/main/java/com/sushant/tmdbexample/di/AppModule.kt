package com.sushant.tmdbexample.di

import android.content.Context
import androidx.room.Room
import com.sushant.tmdbexample.application.MovieApplication
import com.sushant.tmdbexample.database.MovieDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext app: Context): MovieApplication {
        return app as MovieApplication
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDB {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDB::class.java,
            "movie_database"
        )

            .build()
    }

}