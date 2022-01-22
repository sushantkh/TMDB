package com.sushant.tmdbexample.feature.home.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.sushant.tmdbexample.database.entity.MovieEntity
import com.sushant.tmdbexample.databinding.FragmentMovieDetailBinding
import com.sushant.tmdbexample.model.Results
import dagger.hilt.android.AndroidEntryPoint

private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w300/"

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var binding: FragmentMovieDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val result = requireArguments().getSerializable("movie")
        if (result != null) setUpUI(result as Results)
        val savedMovie = requireArguments().getSerializable("saved_movie")
        if (savedMovie != null) setUpUI(savedMovie as MovieEntity)
        return root
    }

    private fun setUpUI(results: Results) {
        binding!!.apply {
            imageView.load(POSTER_BASE_URL + results.posterPath)
            tvTitle.text = results.title
            tvReleaseDate.text = results.releaseDate
            tvRating.text = "Rating " + results.voteAverage.toString()
            tvVoteCount.text = "Votes (${results.voteCount})"
            tvOverview.text = results.overview
            if (results.adult == true) tvAdult.text = "A"
        }
    }

    private fun setUpUI(results: MovieEntity) {
        binding!!.apply {
            imageView.load(POSTER_BASE_URL + results.posterPath)
            tvTitle.text = results.title
            tvReleaseDate.text = results.releaseDate
            tvRating.text = "Rating " + results.voteAverage.toString()
            tvVoteCount.text = "Votes (${results.voteCount})"
            tvOverview.text = results.overview
            if (results.adult == true) tvAdult.text = "A"
        }
    }
}