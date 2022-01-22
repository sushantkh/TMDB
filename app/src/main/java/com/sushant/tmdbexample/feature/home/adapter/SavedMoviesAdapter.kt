package com.sushant.tmdbexample.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sushant.tmdbexample.database.entity.MovieEntity
import com.sushant.tmdbexample.databinding.GridRowBinding
import com.sushant.tmdbexample.listener.MovieClickListener
import com.sushant.tmdbexample.listener.SavedMovieClickListener

private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342/"

class SavedMoviesAdapter(private val clickListener: SavedMovieClickListener) : RecyclerView.Adapter<SavedMoviesAdapter.MovieHolder>() {
    private var savedMovieList: ArrayList<MovieEntity> = ArrayList()

    inner class MovieHolder(val binding: GridRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = GridRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val result = savedMovieList[position]!!
        holder.binding.apply {
            ivGridPoster.load(POSTER_BASE_URL + result.posterPath)
            tvGridTitle.text = result.title
            ivStar.setOnClickListener {
                if (result.isSelected) {
                    result.isSelected = false
                    ivStar.setImageResource(android.R.drawable.btn_star_big_off)
                    clickListener.onStarButtonClick(result, false)
                } else {
                    result.isSelected = true
                    ivStar.setImageResource(android.R.drawable.btn_star_big_on)
                    clickListener.onStarButtonClick(result, true)
                }
            }
            rowContainer.setOnClickListener {
                clickListener.onItemClick(result)
            }
        }
    }

    public fun setList(movieList: List<MovieEntity>) {
        savedMovieList.clear()
        savedMovieList.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return savedMovieList.size
    }
}