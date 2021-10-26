package com.arsh.lastfmclient.data.model


import com.google.gson.annotations.SerializedName

data class Topalbums(
    @SerializedName("album")
    val album: List<Album>,

)