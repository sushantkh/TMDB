package com.sushant.tmdbexample.feature.home.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sushant.tmdbexample.databinding.FragmentFavouriteBinding
import com.sushant.tmdbexample.feature.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentFavouriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root


        homeViewModel.getSavedMovies().observe(viewLifecycleOwner,{
            Log.e("Favourite", "results : ${it}")
        })
        homeViewModel.getSavedMovies()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}