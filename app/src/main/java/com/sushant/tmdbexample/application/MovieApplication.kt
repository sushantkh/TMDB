package com.sushant.tmdbexample.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MovieApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}