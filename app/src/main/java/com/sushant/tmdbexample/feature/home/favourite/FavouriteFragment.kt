package com.sushant.tmdbexample.feature.home.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sushant.tmdbexample.database.entity.MovieEntity
import com.sushant.tmdbexample.databinding.FragmentFavouriteBinding
import com.sushant.tmdbexample.feature.home.HomeViewModel
import com.sushant.tmdbexample.feature.home.adapter.SavedMoviesAdapter
import com.sushant.tmdbexample.listener.SavedMovieClickListener
import com.sushant.tmdbexample.network.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment(), SavedMovieClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentFavouriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var savedMoviesAdapter: SavedMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initView()
        if (!NetworkUtil.isNetworkAvailable(requireContext())) {
            Toast.makeText(requireContext(), "No Internet Available", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        collectUiState()
        homeViewModel.getSavedMovies()
    }

    private fun initView() {
        savedMoviesAdapter = SavedMoviesAdapter(this)
        binding.rvSavedMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = savedMoviesAdapter
        }
    }

    private fun collectUiState() {
        homeViewModel.savedMovieList.observe(viewLifecycleOwner, {
            savedMoviesAdapter.setList(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(result: MovieEntity) {
        homeViewModel.navigateToDetail(result)
    }

    override fun onStarButtonClick(result: MovieEntity, isSelected: Boolean) {
        result.id?.let { homeViewModel.deleteMovie(it) }
    }
}