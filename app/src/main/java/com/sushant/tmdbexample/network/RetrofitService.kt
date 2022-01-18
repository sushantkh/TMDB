/*
 * Created by BS18270 on 28/09/21, 5:53 PM
 *
 * Copyright (c) 2021 Deere & Company as an unpublished work.
 * All Worldwide Rights Reserved.
 * THIS MATERIAL IS THE PROPERTY OF DEERE & COMPANY.
 * ALL USE, ALTERATIONS, DISCLOSURE, DISSEMINATION
 * AND/OR REPRODUCTION NOT SPECIFICALLY AUTHORIZED
 * BY DEERE & COMPANY IS PROHIBITED.
 *
 */

package com.sushant.tmdbexample.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    //https://api.themoviedb.org/3/movie/top_rated?api_key=<<api_key>>&language=en-US&page=1
    companion object {
        private val BASE_URL = "https://api.themoviedb.org/3/"


        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        fun <T> buildService(service: Class<T>): T {
            return retrofit.create(service)
        }
    }
}
