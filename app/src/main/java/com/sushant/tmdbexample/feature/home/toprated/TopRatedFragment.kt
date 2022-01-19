package com.sushant.tmdbexample.feature.home.toprated

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sushant.tmdbexample.databinding.FragmentTopRatedBinding
import com.sushant.tmdbexample.feature.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentTopRatedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.movieList.observe(viewLifecycleOwner, Observer {
            Log.e("TopRated", "result : ${it.size}")
            homeViewModel.saveMovie(it[0])
        })

        homeViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.e("TopRated", "error : $it")
        })
        homeViewModel.getTopRatedMovies()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}