package com.sushant.tmdbexample.feature.home.toprated

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sushant.tmdbexample.databinding.FragmentTopRatedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedFragment : Fragment() {

    private val topRatedViewModel: TopRatedViewModel by viewModels()

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


        topRatedViewModel.movieList.observe(viewLifecycleOwner, Observer {
            Log.e("TopRated", "result : ${it.size}")
        })

        topRatedViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.e("TopRated", "error : $it")
        })
        topRatedViewModel.getTopRatedMovies()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}