package com.arsh.lastfmclient.data.model.search


import com.google.gson.annotations.SerializedName

/**
 * The data Class represents the single instance
 */
data class Artistmatches(
    @SerializedName("artist")
    val artist: List<Artists>
)