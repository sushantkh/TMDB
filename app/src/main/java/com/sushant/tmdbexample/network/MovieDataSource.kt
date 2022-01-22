package com.sushant.tmdbexample.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sushant.tmdbexample.model.Results
import javax.inject.Inject
private const val TMDB_STARTING_PAGE_INDEX = 1
class MovieDataSource @Inject constructor(private val apiService: MovieApiService) :
    PagingSource<Int, Results>() {


    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val nextPageNumber = params.key ?: TMDB_STARTING_PAGE_INDEX
            val response = apiService.getTopRatedMovies(
                "3944c968efed3963ff9229a6eb46057e", "en-US", nextPageNumber
            )

            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages!!) nextPageNumber + 1 else null
            )

           /* val movies = response.results
            val nextKey =
                if (movies.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    nextPageNumber + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = movies,
                prevKey = if (nextPageNumber == TMDB_STARTING_PAGE_INDEX) null else nextPageNumber,
                nextKey = nextKey
            )*/

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
