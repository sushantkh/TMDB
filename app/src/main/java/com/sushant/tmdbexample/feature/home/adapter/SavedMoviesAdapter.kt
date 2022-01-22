package com.sushant.tmdbexample.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sushant.tmdbexample.database.entity.MovieEntity
import com.sushant.tmdbexample.databinding.GridRowBinding

private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342/"

class SavedMoviesAdapter : RecyclerView.Adapter<SavedMoviesAdapter.MovieHolder>() {
    private var savedMovieList: ArrayList<MovieEntity> = ArrayList()

    inner class MovieHolder(val binding: GridRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = GridRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        with(holder) {
            with(savedMovieList[position]) {
                binding.apply {
                    tvGridTitle.text = title
                    ivGridPoster.load(POSTER_BASE_URL + posterPath)
                }
            }
        }
    }

    public fun setList(movieList: List<MovieEntity>) {
        savedMovieList.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return savedMovieList.size
    }
}