package com.arsh.lastfmclient.data.model.search


import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("results")
    val results: Results
)