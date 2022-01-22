package com.sushant.tmdbexample.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sushant.tmdbexample.databinding.GridRowBinding
import com.sushant.tmdbexample.listener.MovieClickListener
import com.sushant.tmdbexample.model.Results


private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342/"

class MovieGridAdapter(private val clickListener: MovieClickListener) :
    PagingDataAdapter<Results, MovieGridAdapter.MovieHolder>(MovieDiffCallBack()) {

    inner class MovieHolder(val binding: GridRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = GridRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val result = getItem(position)!!
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
            rowContainer.setOnClickListener { clickListener.onItemClick(result) }
        }
    }

    class MovieDiffCallBack : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }

    }
}