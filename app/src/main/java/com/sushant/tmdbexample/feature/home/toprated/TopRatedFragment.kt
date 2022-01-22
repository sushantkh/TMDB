package com.sushant.tmdbexample.feature.home.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.sushant.tmdbexample.databinding.FragmentTopRatedBinding
import com.sushant.tmdbexample.feature.home.HomeViewModel
import com.sushant.tmdbexample.feature.home.adapter.MovieGridAdapter
import com.sushant.tmdbexample.listener.MovieClickListener
import com.sushant.tmdbexample.model.Results
import com.sushant.tmdbexample.network.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider




@AndroidEntryPoint
class TopRatedFragment : Fragment(), MovieClickListener {

    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentTopRatedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var movieGridAdapter: MovieGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /* homeViewModel.movieList.observe(viewLifecycleOwner, Observer {
             Log.e("TopRated", "result : ${it.size}")
             homeViewModel.saveMovie(it[0])
         })*/


        if (!NetworkUtil.isNetworkAvailable(requireContext())) {
            Toast.makeText(requireContext(), "No Internet Available", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       homeViewModel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        initView()
        collectUiState()
    }

    private fun initView() {
        movieGridAdapter = MovieGridAdapter(this)
        binding.rvTopRated.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieGridAdapter
        }
    }

    private fun collectUiState() {
        CoroutineScope(Dispatchers.Main).launch {
            homeViewModel.movies.collectLatest { pagedData ->
                movieGridAdapter.submitData(pagedData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(result: Results) {
        homeViewModel.navigateToDetail(result)
    }

    override fun onStarButtonClick(result: Results, isSelected: Boolean) {
        if (isSelected)
            homeViewModel.saveMovie(result)
        else
            result.id?.let { homeViewModel.deleteMovie(it) }
    }
}