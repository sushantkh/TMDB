package com.sushant.tmdbexample.network

import com.sushant.tmdbexample.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/top_rated")
  suspend  fun getTopRatedMovies(
        @Query("api_key") api_key: String = "3944c968efed3963ff9229a6eb46057e",
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): MoviesList
}

