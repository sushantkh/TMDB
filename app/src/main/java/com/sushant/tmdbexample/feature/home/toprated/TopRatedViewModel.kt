package com.sushant.tmdbexample.feature.home.toprated

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sushant.tmdbexample.application.MovieApplication
import com.sushant.tmdbexample.feature.home.HomeRepository
import com.sushant.tmdbexample.model.MoviesList
import com.sushant.tmdbexample.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val movieApplication: MovieApplication,
    private val homeRepository: HomeRepository
) : AndroidViewModel(movieApplication) {



    val movieList = MutableLiveData<List<Results>>()
    val errorMessage = MutableLiveData<String>()


    fun getTopRatedMovies() {
        val response = homeRepository.getTopRatedMovies()
        response.enqueue(object : Callback<MoviesList> {
            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                movieList.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

}