package com.sushant.tmdbexample.feature.home

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sushant.tmdbexample.application.MovieApplication
import com.sushant.tmdbexample.database.entity.MovieEntity
import com.sushant.tmdbexample.model.Results
import com.sushant.tmdbexample.network.MovieApiService
import com.sushant.tmdbexample.network.MovieDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieApplication: MovieApplication,
    private val homeRepository: HomeRepository,
    private val movieApiService: MovieApiService
) : AndroidViewModel(movieApplication) {


    val savedMovieList = MutableLiveData<List<MovieEntity>>()
    val navigateToDetail = MutableLiveData<Results>()
    val navigateToDetailFromSavedMovies = MutableLiveData<MovieEntity>()

    val movies =
        Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            MovieDataSource(movieApiService)
        }).flow.cachedIn(viewModelScope)

    fun getSavedMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            savedMovieList.postValue(homeRepository.getSavedMovies())
        }
    }

    fun saveMovie(movieResult: Results) {
        CoroutineScope(Dispatchers.IO).launch {
            val movieEntity = MovieEntity().apply {
                this.id = movieResult.id
                this.adult = movieResult.adult
                this.title = movieResult.title
                this.overview = movieResult.overview
                this.posterPath = movieResult.posterPath
                this.releaseDate = movieResult.releaseDate
                this.voteAverage = movieResult.voteAverage
                this.voteCount = movieResult.voteCount
            }
            homeRepository.insertMovie(movieEntity)
        }
    }

    fun deleteMovie(id: Int) {
        homeRepository.deleteMovie(id)
    }

    fun navigateToDetail(results: Results) {
        navigateToDetail.postValue(results)
    }

    fun navigateToDetail(results: MovieEntity) {
        navigateToDetailFromSavedMovies.postValue(results)
    }
}