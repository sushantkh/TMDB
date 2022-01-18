package com.sushant.tmdbexample.model

import com.google.gson.annotations.SerializedName
import com.sushant.tmdbexample.model.Results


data class  MoviesList (

  @SerializedName("page"          ) var page         : Int?               = null,
  @SerializedName("results"       ) var results      : ArrayList<Results> = arrayListOf(),
  @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
  @SerializedName("total_results" ) var totalResults : Int?               = null

)