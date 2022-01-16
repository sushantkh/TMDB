package com.sushant.tmdbexample.di

import android.content.Context
import com.sushant.tmdbexample.application.MovieApplication
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
}