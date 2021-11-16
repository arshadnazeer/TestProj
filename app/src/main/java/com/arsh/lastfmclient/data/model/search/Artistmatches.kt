package com.arsh.lastfmclient.data.model.search


import com.google.gson.annotations.SerializedName

data class Artistmatches(
    @SerializedName("artist")
    val artist: List<Artists>
)