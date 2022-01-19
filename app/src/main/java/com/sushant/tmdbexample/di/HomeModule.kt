package com.sushant.tmdbexample.di

import android.content.Context
import com.sushant.tmdbexample.database.MovieDB
import com.sushant.tmdbexample.database.dao.MovieDAO
import com.sushant.tmdbexample.feature.home.HomeRepository
import com.sushant.tmdbexample.network.MovieApiService
import com.sushant.tmdbexample.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
class HomeModule {

    @Provides
    fun provideMovieService(): MovieApiService =
        RetrofitService.retrofit.create(MovieApiService::class.java)

    @Provides
    fun provideHomeRepository(movieApiService: MovieApiService,movieDAO: MovieDAO): HomeRepository =
        HomeRepository(movieApiService,movieDAO)


    @Provides
    fun provideMovieDao(movieDB: MovieDB): MovieDAO {
       return movieDB.movieDao()
    }
}