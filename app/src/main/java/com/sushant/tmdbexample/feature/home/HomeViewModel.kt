package com.sushant.tmdbexample.feature.home

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sushant.tmdbexample.application.MovieApplication
import com.sushant.tmdbexample.database.entity.MovieEntity
import com.sushant.tmdbexample.model.MoviesList
import com.sushant.tmdbexample.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieApplication: MovieApplication,
    private val homeRepository: HomeRepository
) : AndroidViewModel(movieApplication) {

    val movieList = MutableLiveData<List<Results>>()
    val errorMessage = MutableLiveData<String>()
    // poster path https://image.tmdb.org/t/p/original/

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

    fun getSavedMovies(): LiveData<List<MovieEntity>> {
        return homeRepository.getSavedMovies()
    }

    fun saveMovie(movieResult: Results) {
        CoroutineScope(Dispatchers.IO).launch {
            val movieEntity = MovieEntity().apply {
                this.id = movieResult.id
                this.adult = movieResult.adult
                this.title=movieResult.title
                this.overview = movieResult.overview
                this.posterPath = movieResult.posterPath
                this.releaseDate = movieResult.releaseDate
                this.voteAverage = movieResult.voteAverage
                this.voteCount = movieResult.voteCount
            }
            homeRepository.insertMovie(movieEntity)
        }
    }
}