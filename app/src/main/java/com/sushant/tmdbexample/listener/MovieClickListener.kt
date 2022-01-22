package com.sushant.tmdbexample.listener

import com.sushant.tmdbexample.model.Results

interface MovieClickListener {
    fun onItemClick(result: Results)
    fun onStarButtonClick(result: Results,isSelected:Boolean)
}